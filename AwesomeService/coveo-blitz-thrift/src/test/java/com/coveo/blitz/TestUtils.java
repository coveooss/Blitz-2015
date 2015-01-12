//package com.coveo.blitz;
//
//import java.util.Arrays;
//
//import com.coveo.blitz.thrift.NodeType;
//import com.coveo.blitz.thrift.Query;
//import com.coveo.blitz.thrift.QueryTreeNode;
//
//public class TestUtils
//{
//    private TestUtils()
//    {
//    }
//
//    public static Query createPotatoAndSaladQuery()
//    {
//        Query complexQuery = new Query();
//
//        // Let's create a query object representing the query
//        // Potato AND salad
//
//        QueryTreeNode andNode = new QueryTreeNode();
//        andNode.setId(0);
//        andNode.setValue("AND");
//        andNode.setType(NodeType.OPERATOR);
//
//        QueryTreeNode potatoNode = new QueryTreeNode();
//        potatoNode.setId(1);
//        potatoNode.setType(NodeType.LITERAL);
//        potatoNode.setValue("Potato");
//        potatoNode.setLeftPart(-1);
//        potatoNode.setRightPart(-1);
//
//        QueryTreeNode saladNode = new QueryTreeNode();
//        saladNode.setId(2);
//        saladNode.setType(NodeType.LITERAL);
//        saladNode.setValue("salad");
//        saladNode.setLeftPart(-1);
//        saladNode.setRightPart(-1);
//
//        andNode.setLeftPart(1);
//        andNode.setRightPart(2);
//
//        complexQuery.setRootId(andNode.getId());
//        complexQuery.setQueryTreeNodes(Arrays.asList(andNode, saladNode, potatoNode));
//
//        return complexQuery;
//    }
//}
