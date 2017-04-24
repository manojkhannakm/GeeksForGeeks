package datastructures;

/**
 * @author Manoj Khanna
 */

public class BinarySearchTree<E extends Comparable<E>> {

    private Node rootNode;

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(40);
        binarySearchTree.add(20);
        binarySearchTree.add(10);
        binarySearchTree.add(30);
        binarySearchTree.add(50);
    }

    public void add(E value) {
        if (rootNode == null) {
            rootNode = new Node(value);
        } else {
            Node node = rootNode;
            while (true) {
                E nodeValue = node.getValue();
                if (value.compareTo(nodeValue) < 0) {
                    Node leftNode = node.getLeftNode();
                    if (leftNode == null) {
                        node.setLeftNode(new Node(value));
                        break;
                    } else {
                        node = leftNode;
                    }
                } else if (value.compareTo(nodeValue) > 0) {
                    Node rightNode = node.getRightNode();
                    if (rightNode == null) {
                        node.setRightNode(new Node(value));
                        break;
                    } else {
                        node = rightNode;
                    }
                } else {
                    break;
                }
            }
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public class Node {

        private E value;
        private Node leftNode, rightNode;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

    }

}
