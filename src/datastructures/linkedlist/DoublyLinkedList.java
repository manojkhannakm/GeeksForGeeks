package datastructures.linkedlist;

import datastructures.BinarySearchTree;

/**
 * @author Manoj Khanna
 */

public class DoublyLinkedList<E extends Comparable<E>> extends LinkedList<E> {

    public DoublyLinkedList(E... values) {
        super(values);
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>(10, 20, 30, 40, 50);
        System.out.println("List: " + linkedList);

        linkedList.reverse();
        System.out.println("List: " + linkedList);

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(40);
        binarySearchTree.add(20);
        binarySearchTree.add(10);
        binarySearchTree.add(30);
        binarySearchTree.add(50);
        System.out.println("List: " + linkedList.from(binarySearchTree));

        linkedList.quickSort();
        System.out.println("List: " + linkedList);
    }

    @Override
    public String toString() {
        return super.toString().replace("->", "<->");
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException();
        }

        if (headNode == null) {
            headNode = new Node(value);
        } else {
            if (index == 0) {
                Node node = new Node(value);
                node.setNextNode(headNode);

                ((Node) headNode).setPreviousNode(node);

                headNode = node;
            } else {
                Node previousNode = (Node) headNode;
                for (int i = 1; i < index; i++) {
                    previousNode = (Node) previousNode.getNextNode();
                }

                Node nextNode = (Node) previousNode.getNextNode();

                Node node = new Node(value);
                node.setPreviousNode(previousNode);
                node.setNextNode(nextNode);

                previousNode.setNextNode(node);

                if (nextNode != null) {
                    nextNode.setPreviousNode(node);
                }
            }
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            Node node = (Node) headNode.getNextNode();
            node.setPreviousNode(null);
            headNode.setNextNode(null);
            headNode = node;
        } else {
            Node node = (Node) headNode;
            for (int i = 1; i <= index; i++) {
                node = (Node) node.getNextNode();
            }

            Node previousNode = node.getPreviousNode(),
                    nextNode = (Node) node.getNextNode();
            previousNode.setNextNode(nextNode);

            node.setPreviousNode(null);
            node.setNextNode(null);

            if (nextNode != null) {
                nextNode.setPreviousNode(previousNode);
            }
        }
    }

    public void reverse() {
        Node previousNode = null,
                node = (Node) headNode;
        while (node != null) {
            previousNode = node.getPreviousNode();
            Node nextNode = (Node) node.getNextNode();

            node.setPreviousNode(nextNode);
            node.setNextNode(previousNode);

            previousNode = node;
            node = nextNode;
        }

        headNode = previousNode;
    }

    private BinarySearchTree<E>.Node from(BinarySearchTree<E>.Node node, BinarySearchTree<E>.Node previousNode) {
        BinarySearchTree<E>.Node leftNode = node.getLeftNode();
        if (leftNode != null) {
            previousNode = from(leftNode, previousNode);
        }

        if (previousNode != null) {
            previousNode.setRightNode(node);
            node.setLeftNode(previousNode);
        }

        previousNode = node;

        BinarySearchTree<E>.Node rightNode = node.getRightNode();
        if (rightNode != null) {
            previousNode = from(rightNode, previousNode);
        }

        return previousNode;
    }

    public DoublyLinkedList<E> from(BinarySearchTree<E> binarySearchTree) {
        BinarySearchTree<E>.Node rootNode = binarySearchTree.getRootNode();
        from(rootNode, null);

        BinarySearchTree<E>.Node node = rootNode;
        while (node.getLeftNode() != null) {
            node = node.getLeftNode();
        }

        DoublyLinkedList<E> linkedList = new DoublyLinkedList<>();
        while (node != null) {
            linkedList.addLast(node.getValue());
            node = node.getRightNode();
        }

        return linkedList;
    }

    private void swap(Node node1, Node node2) {
        E value = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(value);
    }

    private Node pivotNode(Node startNode, Node endNode) {
        Node node1 = startNode;
        for (Node node2 = startNode; node2 != endNode; node2 = (Node) node2.getNextNode()) {
            if (node2.getValue().compareTo(endNode.getValue()) < 0) {
                swap(node2, node1);

                node1 = (Node) node1.getNextNode();
            }
        }

        swap(endNode, node1);

        return node1;
    }

    private void quickSort(Node startNode, Node endNode) {
        Node pivotNode = pivotNode(startNode, endNode);
        if (startNode != pivotNode) {
            quickSort(startNode, pivotNode.getPreviousNode());
        }

        if (endNode != pivotNode) {
            quickSort((Node) pivotNode.getNextNode(), endNode);
        }
    }

    public void quickSort() {
        if (headNode == null) {
            return;
        }

        Node tailNode = (Node) headNode;
        while (tailNode.getNextNode() != null) {
            tailNode = (Node) tailNode.getNextNode();
        }

        quickSort((DoublyLinkedList<E>.Node) headNode, tailNode);
    }

    private class Node extends LinkedList<E>.Node {

        private Node previousNode;

        public Node(E value) {
            super(value);
        }

        public Node getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(Node previousNode) {
            this.previousNode = previousNode;
        }

    }

}
