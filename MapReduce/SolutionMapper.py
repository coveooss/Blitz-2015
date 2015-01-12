#!/usr/bin/env python

import sys
import re
import cProfile
import datetime

if __name__ == "__main__":

    doRun0 = True
    doRun1 = True
    doRun2 = True
    doRun3 = True
    doRun4 = True
    doRun5 = False

    # Regex for an Apache line
    # Pelletier the private - - [30/Apr/1998:22:02:37 +0000] "GET wikipedia/en/a/r/t/wikipedia~articles_for_deletion_waking_the_tiger_c0a7.html HTTP/1.0" 200 753
    dateRE = re.compile('(.*) - - \[(.*?)\] "([^ ]*) (.*) (HTTP.*)" (\d+) (\d+)')
    dayofweekStr   = ['monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday']
    
    for line in sys.stdin:
        requester = ""
        datevalue = ""
        verbvalue = ""
        requested = ""
        httpvalue = ""
        httpResponseCode = 0
        sizevalue = ""

        # find the dimensions
        m = dateRE.search(line)
        if m:
            requester = m.groups()[0]
            datetimeStr = m.groups()[1]
            verbvalue = m.groups()[2]
            requested = m.groups()[3]
            httpvalue = m.groups()[4]
            httpResponseCode = m.groups()[5]
            sizevalue = m.groups()[6]

            date, hour, minute, nothing = datetimeStr.split(":")
            dayofweek = dayofweekStr[datetime.datetime.strptime(date, "%d/%b/%Y").weekday()]
            #dateBucket = datetime[:datetime.find(':')]
            #hourbucket = datetime[:datetime.find(':', datetime.find(':')+1)]
            
            dateTime = datetime.datetime.strptime(datetimeStr.split(" ")[0],"%d/%b/%Y:%H:%M:%S")
            date = orderedDateTime = dateTime.strftime("%Y-%m-%d")

            #Run 
            if doRun0 and httpResponseCode != "":
                print 'Run:0;responsecode:%s\tcount:1' % (httpResponseCode)

            #Run 1
            if doRun1 and verbvalue != "" and httpResponseCode != "" and httpvalue != "" and date != "" :
                print 'Run:1;date:%s;httpversion:%s;responsecode:%s;verb:%s\tcount:1;size:%s' % (date, httpvalue, httpResponseCode, verbvalue, sizevalue if sizevalue != '4294967295' else '0')

            #Run 2 still a count Run
            if doRun2 and httpResponseCode == "404" and requester != "" and requested != "" and verbvalue != "":
                sizeFixed = sizevalue if sizevalue != '4294967295' else '0'
                print 'Run:2a;requester:%s;responsecode:404\tcount:1;size:%s' % (requester, sizeFixed)
                print 'Run:2b;requested:%s;responsecode:404\tcount:1;size:%s' % (requested, sizeFixed)
                print 'Run:2c;responsecode:404;verb:%s\tcount:1;size:%s' % (verbvalue, sizeFixed)

                print 'Run:2d;requested:%s;requester:%s;responsecode:404\tcount:1;size:%s' % (requested, requester, sizeFixed)
                print 'Run:2e;requester:%s;responsecode:404;verb:%s\tcount:1;size:%s' % (requester, verbvalue, sizeFixed)
                print 'Run:2f;requested:%s;responsecode:404;verb:%s\tcount:1;size:%s' % (requested, verbvalue, sizeFixed)

                print 'Run:2g;requested:%s;requester:%s;responsecode:404;verb:%s\tcount:1;size:%s' % (requested, requester, verbvalue, sizeFixed)

            # Day of week lower case full english
            if doRun3 and date != "" and dayofweek != "" and hour != "" and minute != "" and requester != "":
                print 'Run:3a;date:%s;requester:%s\tcount:1' % (date, requester)
                print 'Run:3b;dayofweek:%s;requester:%s\tcount:1' % (dayofweek, requester)
                print 'Run:3c;hour:%s;requester:%s\tcount:1' % (hour, requester)
                print 'Run:3d;minute:%s;requester:%s\tcount:1' % (minute, requester)

                print 'Run:3e;date:%s;dayofweek:%s;requester:%s\tcount:1' % (date, dayofweek, requester)
                print 'Run:3f;date:%s;hour:%s;requester:%s\tcount:1' % (date, hour, requester)
                print 'Run:3g;date:%s;minute:%s;requester:%s\tcount:1' % (date, minute, requester)
                print 'Run:3h;dayofweek:%s;hour:%s;requester:%s\tcount:1' % (dayofweek, hour, requester)
                print 'Run:3i;dayofweek:%s;minute:%s;requester:%s\tcount:1' % (dayofweek, minute, requester)
                print 'Run:3j;hour:%s;minute:%s;requester:%s\tcount:1' % (hour, minute, requester)

                print 'Run:3k;date:%s;dayofweek:%s;hour:%s;requester:%s\tcount:1' % (date, dayofweek, hour, requester)
                print 'Run:3l;date:%s;dayofweek:%s;minute:%s;requester:%s\tcount:1' % (date, dayofweek, minute, requester)
                print 'Run:3m;date:%s;hour:%s;minute:%s;requester:%s\tcount:1' % (date, hour, minute, requester)
                print 'Run:3n;dayofweek:%s;hour:%s;minute:%s;requester:%s\tcount:1' % (dayofweek, hour, minute, requester)

                print 'Run:3o;date:%s;dayofweek:%s;hour:%s;minute:%s;requester:%s\tcount:1' % (date, dayofweek, hour, minute, requester)

            if doRun4 and requester != "" and verbvalue != "":
                datetimeStr = datetimeStr.split(" ")[0]
                dateTime = datetime.datetime.strptime(datetimeStr,"%d/%b/%Y:%H:%M:%S")

                orderedDateTime = dateTime.strftime("%Y-%m-%dT%H:%M:%S")
                print 'Run:4a;verb:%s;date:%s\tcount:1' % (verbvalue, orderedDateTime)
                print 'Run:4b;requester:%s;date:%s\tcount:1' % (requester, orderedDateTime)
                print 'Run:4c;requester:%s;verb:%s;date:%s\tcount:1' % ( requester, verbvalue, orderedDateTime)

            if doRun5 and date != "" and hour != "" and requested != "" and requester != "" and httpResponseCode != "":
                if httpResponseCode.startswith("3") or httpResponseCode.startswith("5"):
                    print 'Run:5a;date:%s;requester:%s\tcount:1' % (date, requester)
                    print 'Run:5b;hour:%s;requester:%s\tcount:1' % (hour, requester)
                    print 'Run:5c;requested:%s;requester:%s\tcount:1' % (requested, requester)
                    print 'Run:5d;date:%s;hour:%s;requester:%s\tcount:1' % (date, hour, requester)
                    print 'Run:5e;date:%s;requested:%s;requester:%s\tcount:1' % (date, requested, requester)
                    print 'Run:5f;hour:%s;requested:%s;requester:%s\tcount:1' % (hour, requested, requester)
                    print 'Run:5g;date:%s;hour:%s;requested:%s;requester:%s\tcount:1' % (date, hour, requested, requester)


