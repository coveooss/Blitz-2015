//package com.coveo.blitz;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import com.coveo.blitz.thrift.Query;
//import com.coveo.blitz.thrift.QueryTreeNode;
//
//public class QueryTest
//{
//
//    private class TreeNode
//    {
//        private TreeNode leftPart;
//        private TreeNode rightPart;
//
//        private String value;
//
//        public String getValue()
//        {
//            return value;
//        }
//
//        public void setValue(String value)
//        {
//            this.value = value;
//        }
//
//        public TreeNode getRightPart()
//        {
//            return rightPart;
//        }
//
//        public void setRightPart(TreeNode rightPart)
//        {
//            this.rightPart = rightPart;
//        }
//
//        public TreeNode getLeftPart()
//        {
//            return leftPart;
//        }
//
//        public void setLeftPart(TreeNode leftPart)
//        {
//            this.leftPart = leftPart;
//        }
//    }
//
//    @Test
//    public void testEnsureThatWeCanCreateATreeFromAQuery() throws Exception
//    {
//        Query query = TestUtils.createPotatoAndSaladQuery();
//
//        TreeNode rootNode = recursiveHandleNode(query.getQueryTreeNodes(), query.getRootId());
//
//        assertEquals("AND", rootNode.getValue());
//        assertEquals("Potato", rootNode.getLeftPart().getValue());
//        assertEquals("salad", rootNode.getRightPart().getValue());
//        assertNull(rootNode.getLeftPart().getLeftPart());
//        assertNull(rootNode.getLeftPart().getRightPart());
//        assertNull(rootNode.getRightPart().getLeftPart());
//        assertNull(rootNode.getRightPart().getRightPart());
//    }
//
//    private TreeNode recursiveHandleNode(List<QueryTreeNode> nodes,
//                                         int nodeId)
//    {
//        if (nodeId == -1) {
//            return null;
//        }
//
//        QueryTreeNode queryTreeNode = getNodeById(nodes, nodeId);
//
//        TreeNode treeNode = new TreeNode();
//        treeNode.setValue(queryTreeNode.getValue());
//
//        treeNode.setLeftPart(recursiveHandleNode(nodes, queryTreeNode.getLeftPart()));
//        treeNode.setRightPart(recursiveHandleNode(nodes, queryTreeNode.getRightPart()));
//
//        return treeNode;
//    }
//
//    private QueryTreeNode getNodeById(List<QueryTreeNode> nodes,
//                                      int id)
//    {
//        for (QueryTreeNode node : nodes) {
//            if (node.getId() == id) {
//                return node;
//            }
//        }
//
//        throw new IllegalArgumentException("Invalid id '" + id + "', not found!!");
//    }
//}
