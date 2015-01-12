#!/usr/bin/env python
from __future__ import division
from operator import itemgetter
import datetime
import sys

#  VERB(1),
#  DATE(2),
#  HTTPPROTOCOL(3),
#  HTTPRESPONSECODE(4),
#  REQUESTED(5),
#  REQUESTER(6);

def StdInYielder(stdin):
    for line in sys.stdin:
        yield line
    yield None

class CsvPrinter:
    lastKeys = ""
    hasHadFirstValue = False
    def PrintStart(self):
        pass
    def PrintEnd(self):
        pass
    
    def PrintLine(self, key, metric):
        #get rid of run number
        key = key[key.find(";")+1:]
        print "%s\t%s" % (key, metric)

class JsonPrinter:
    lastKeys = ""
    hasHadFirstValue = False
    def PrintStart(self):
        print "{ \"values\" : {"
    def PrintEnd(self):
        print "}}}"
    def PrintRangeStart(self, keys):
        if self.hasHadFirstValue:
            print "},"
        self.hasHadFirstValue = True
        print " \"%s\" : {" % ( keys)
    
    def PrintLine(self, key, metric):
        #get rid of run number
        key = key[key.find(";")+1:]
        
        keyValueSplit = [x.split(":") for x in key.split(";")]
        keys = "|".join([s[0] for s in keyValueSplit])

        if keys != self.lastKeys:
            self.PrintRangeStart(keys)
        else:
            print ","

        self.lastKeys = keys
        print "\"%s\" : {" % ("|".join([s[1] for s in keyValueSplit]))
        
        str = ''
        for l in [x.split(":") for x in metric.split(";")]:
            if str != '':
                str = str + ","
            str = str + "\"%s\" : %s" % (l[0], l[1])
        print str
        print "}"



class Run0Solver:
    totalCount = 0
    previousKey = ''
    dataPrinter = None
    def __init__(self, dataPrinterArg):
        self.dataPrinter = dataPrinterArg

    def ProcessLine(self, currentLine):
        key, value = currentLine.split("\t")
        if self.previousKey != '' and self.previousKey != key:
            self.Print()
            self.totalCount = 0
        self.totalCount = self.totalCount + 1
        self.previousKey = key

    def PrintPending(self):
        if self.previousKey != '':
            self.Print()

    def Print(self):
        self.dataPrinter.PrintLine(self.previousKey, 'count:%d' % (self.totalCount))
        #print '%s\tcount:%d' % (self.previousKey, self.totalCount)


class Run1Solver:
    totalCount = 0
    previousKey = ''
    totalSize = 0
    dataPrinter = None
    def __init__(self, dataPrinterArg):
        self.dataPrinter = dataPrinterArg

    def ProcessLine(self, currentLine):
        key, value = currentLine.split("\t")
        if self.previousKey != '' and self.previousKey != key:
            self.Print()
            self.totalCount = 0
            self.totalSize = 0
        self.totalCount = self.totalCount + 1
        size = int(value.split("size:")[1])
        self.totalSize = self.totalSize + size
        self.previousKey = key

    def PrintPending(self):
        if self.previousKey != '':
            self.Print()

    def Print(self):
        self.dataPrinter.PrintLine(self.previousKey, 'count:%d;size:%d' % (self.totalCount, self.totalSize / self.totalCount))
        #print '%s\tcount:%d;size:%.2f' % (self.previousKey, self.totalCount, self.totalSize / self.totalCount)


class Run3Solver:
    previousRequester = ''
    uniqueCount = 0
    previousPartialKey = ''
    dataPrinter = None
    def __init__(self, dataPrinterArg):
        self.dataPrinter = dataPrinterArg

    def ProcessLine(self, currentLine):
        #print currentLine
        key, value = currentLine.split("\t")
        partialKey, requester = key.rsplit(";", 1)
        requester = requester.split(":")[1]

        if self.previousPartialKey != '' and self.previousPartialKey != partialKey:
            self.Print()
            self.previousRequester = ''
            self.uniqueCount = 0
            
        if self.previousRequester != requester:
            self.uniqueCount = self.uniqueCount + 1

        self.previousRequester = requester
        self.previousPartialKey = partialKey

    def PrintPending(self):
        if self.previousPartialKey != '':
            self.Print()

    def Print(self):
        self.dataPrinter.PrintLine(self.previousPartialKey, 'uniqueusercount:%s' % (self.uniqueCount))
        #print '%s\tcount:%d;size:%.2f' % (self.previousKey, self.totalCount, self.totalSize / self.totalCount)


class Run4Solver:
    previousDate = ''
    previousPartialKey = ''
    dataPrinter = None
    def __init__(self, dataPrinterArg):
        self.dataPrinter = dataPrinterArg

    def ProcessLine(self, currentLine):
        #print currentLine
        key, value = currentLine.split("\t")
        partialKey, date = key.rsplit(";", 1)
        date = date.split(":", 1)[1]

        if self.previousPartialKey != '' and self.previousPartialKey != partialKey:
            self.Print()
            self.previousDate = ''

        self.previousDate = date
        self.previousPartialKey = partialKey

    def PrintPending(self):
        if self.previousPartialKey != '':
            self.Print()

    def Print(self):
        timeDelta = datetime.datetime.strptime(self.previousDate,"%Y-%m-%dT%H:%M:%S") - datetime.datetime(1970,1,1)
        epoch = float((timeDelta.microseconds + (timeDelta.seconds + timeDelta.days * 24 * 3600) * 10**6)) / 10**6
        self.dataPrinter.PrintLine(self.previousPartialKey, 'lastrequesttimestamp:%d' % (epoch))
        #print '%s\tcount:%d;size:%.2f' % (self.previousKey, self.totalCount, self.totalSize / self.totalCount)



class Run5Solver:
    previousRequesterCount = 1 # since we only increment on equality, it does not happen naturally first time
    previousRequester = ''
    maxCount = 0
    maxRequesterList = []
    previousPartialKey = ''
    dataPrinter = None
    def __init__(self, dataPrinterArg):
        self.dataPrinter = dataPrinterArg

    def ProcessLine(self, currentLine):
        #print currentLine
        key, value = currentLine.split("\t")
        partialKey, requester = key.rsplit(";", 1)
        requester = requester.split(":")[1]

        if self.previousPartialKey != '' and self.previousPartialKey != partialKey:
            self.Print()
            self.previousRequesterCount = 1 # since we only increment on equality, it does not happen naturally first time
            self.previousRequester = ''
            self.maxCount = 0
            self.maxRequesterList = []
            
        if self.previousRequester != '' and self.previousRequester == requester:
           # print "added"
            self.previousRequesterCount = self.previousRequesterCount + 1
        else:
            self.previousRequesterCount = 1 # since we only increment on equality, it does not happen naturally first time

        self.previousRequester = requester

        if self.previousRequesterCount == self.maxCount:
        #    print "equal"
        #    print ""
        #    print self.maxRequesterList
            self.maxRequesterList.append(self.previousRequester)
       #     print self.maxRequesterList

        if self.previousRequesterCount > self.maxCount:
        #    print "newrecord"
            self.maxCount = self.previousRequesterCount
            self.maxRequesterList = [self.previousRequester]

        self.previousPartialKey = partialKey

    def PrintPending(self):
        if self.previousPartialKey != '':
            self.Print()

    def Print(self):
        for requester in self.maxRequesterList:
            self.dataPrinter.PrintLine(self.previousPartialKey, 'maxuser:"%s";count:%d' % (requester, self.maxCount))
        #print '%s\tcount:%d;size:%.2f' % (self.previousKey, self.totalCount, self.totalSize / self.totalCount)

def DoRun(solverClass, idString, printer, currentLine):
        solver = solverClass(printer)
        while currentLine != None and currentLine.startswith(idString):
            solver.ProcessLine(currentLine)
            currentLine = generator.next()

        solver.PrintPending()

        return currentLine


if __name__ == "__main__":

    run0Str = "Run:0"
    run1Str = "Run:1"
    run2Str = "Run:2"
    run3Str = "Run:3"
    run4Str = "Run:4"
    run5Str = "Run:5"
    
    generator = StdInYielder(sys.stdin)
    #printer = CsvPrinter()
    printer = JsonPrinter()

    printer.PrintStart()

    currentLine = generator.next()
 #   run0Solver = Run0Solver(printer)
  #  while currentLine != None and currentLine.startswith(run0Str):
   #     run0Solver.ProcessLine(currentLine)
    #    currentLine = generator.next()

    #run0Solver.PrintPending()

    currentLine = DoRun(Run0Solver, run0Str, printer, currentLine)

    currentLine = DoRun(Run1Solver, run1Str, printer, currentLine)

    # not a mistake. for now run 1 and 2 are calculated the same way
    currentLine = DoRun(Run1Solver, run2Str, printer, currentLine)

    currentLine = DoRun(Run3Solver, run3Str, printer, currentLine)
    currentLine = DoRun(Run4Solver, run4Str, printer, currentLine)

    currentLine = DoRun(Run5Solver, run5Str, printer, currentLine)

    # not a mistake. for now run 1 and 2 are calculated the same way
#    run1Solver = Run1Solver(printer)
 #   while currentLine != None and currentLine.startswith(run2Str):
  #      run1Solver.ProcessLine(currentLine)
   #     currentLine = generator.next()

    #run1Solver.PrintPending()


    printer.PrintEnd()



