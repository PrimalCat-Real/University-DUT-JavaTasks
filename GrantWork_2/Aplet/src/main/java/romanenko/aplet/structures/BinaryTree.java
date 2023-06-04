package romanenko.aplet.structures;

import romanenko.aplet.components.NodeItem;

import java.util.*;

public class BinaryTree implements Iterable<Integer> {

    private NodeItem rootNode;

    public NodeItem getRoot() {
        return rootNode;
    }


    public void logAllValues() {
        System.out.println("binary tree start");
        inOrderTraversal(rootNode);
        System.out.println("binary tree end");
    }

    public void removeNode(int value) {
        rootNode = removeNode(rootNode, value);
    }

    private NodeItem removeNode(NodeItem node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            node.setLeft(removeNode(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(removeNode(node.getRight(), value));
        } else {
            // Node to be removed found

            // Case 1: Node has no children (leaf node)
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            // Case 2: Node has one child
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            // Case 3: Node has two children
            NodeItem successor = findMinimumNode(node.getRight());
            node.setValue(successor.getValue());
            node.setRight(removeNode(node.getRight(), successor.getValue()));
        }

        return node;
    }

    private NodeItem findMinimumNode(NodeItem node) {
        if (node.getLeft() == null) {
            return node;
        }
        return findMinimumNode(node.getLeft());
    }

    private void inOrderTraversal(NodeItem node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.println(node.getValue());
            inOrderTraversal(node.getRight());
        }
    }
    public int getHeight() {
        return calculateHeight(rootNode);
    }

    private int calculateHeight(NodeItem node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private void insertNode(NodeItem parent, NodeItem newNode, int level) {
        int newValue = newNode.getValue();
        System.out.println(parent.getValue());
        if (newValue == parent.getValue()) {
            // Reject repeated value
            return;
        }

        if (newValue < parent.getValue()) {
            if (parent.getLeft() == null) {
                newNode.setLevel(level);
                parent.setLeft(newNode);
            } else {
                insertNode(parent.getLeft(), newNode, level + 1);
            }
        } else {
            if (parent.getRight() == null) {
                newNode.setLevel(level);
                parent.setRight(newNode);
            } else {
                insertNode(parent.getRight(), newNode, level + 1);
            }
        }
    }

    public void addNode(int value) {
        NodeItem newNode = new NodeItem(value);
        if (rootNode == null) {
            newNode.setLevel(0);
            rootNode = newNode;
        } else {
            insertNode(rootNode, newNode, 1);

        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BinaryTreeIterator(rootNode);
    }

    private class BinaryTreeIterator implements Iterator<Integer> {
        private final Stack<NodeItem> stack;

        public BinaryTreeIterator(NodeItem rootNode) {
            stack = new Stack<>();
            pushLeft(rootNode);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            NodeItem node = stack.pop();
            pushLeft(node.getRight());
            return node.getValue();
        }

        private void pushLeft(NodeItem node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }
    }

}