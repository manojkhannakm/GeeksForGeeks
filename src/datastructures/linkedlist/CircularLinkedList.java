package datastructures.linkedlist;

/**
 * @author Manoj Khanna
 */

public class CircularLinkedList<E extends Comparable<E>> extends LinkedList<E> {

    public CircularLinkedList(E... values) {
        super(values);
    }

    public static void main(String[] args) {
        CircularLinkedList<Integer> linkedList = new CircularLinkedList<>(10, 20, 30, 40, 50);
        System.out.println("List: " + linkedList);

        CircularLinkedList<Integer> splitLinkedList = linkedList.splitHalf();
        System.out.println("List: " + linkedList);
        System.out.println("List: " + splitLinkedList);

        linkedList.addSorted(5);
        linkedList.addSorted(25);
        linkedList.addSorted(60);
        System.out.println("List: " + linkedList);
    }

    @Override
    public String toString() {
        if (headNode == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("->")
                .append(headNode.getValue());

        for (Node node = headNode.getNextNode(); !node.equals(headNode); node = node.getNextNode()) {
            stringBuilder.append("->")
                    .append(node.getValue());
        }

        stringBuilder.append("->");

        return stringBuilder.toString();
    }

    @Override
    public void add(int index, E value) {
        super.add(index, value);

        if (index == size() - 1) {
            Node tailNode = headNode;
            for (int i = 1; i <= index; i++) {
                tailNode = tailNode.getNextNode();
            }

            tailNode.setNextNode(headNode);
        }
    }

    @Override
    public void remove(int index) {
        super.remove(index);

        if (index == size() + 1) {
            Node tailNode = headNode;
            for (int i = 1; i <= index; i++) {
                tailNode = tailNode.getNextNode();
            }

            tailNode.setNextNode(headNode);
        }
    }

    @Override
    public int size() {
        if (headNode == null) {
            return 0;
        }

        int size = 1;
        for (Node node = headNode.getNextNode(); node != null && !node.equals(headNode); node = node.getNextNode()) {
            size++;
        }

        return size;
    }

    public CircularLinkedList<E> splitHalf() {
        if (headNode == null || headNode.getNextNode().equals(headNode)) {
            return new CircularLinkedList<>();
        }

        Node tailNode1 = headNode,
                tailNode2 = headNode.getNextNode();
        while (!tailNode2.getNextNode().equals(headNode)) {
            tailNode1 = tailNode1.getNextNode();

            tailNode2 = tailNode2.getNextNode();
            if (!tailNode2.getNextNode().equals(headNode)) {
                tailNode2 = tailNode2.getNextNode();
            }
        }

        Node headNode2 = tailNode1.getNextNode();

        tailNode1.setNextNode(headNode);

        CircularLinkedList<E> linkedList = new CircularLinkedList<>();
        linkedList.headNode = headNode2;
        tailNode2.setNextNode(headNode2);

        return linkedList;
    }

    public void addSorted(E value) {
        if (headNode == null) {
            headNode = new Node(value);
        } else if (value.compareTo(headNode.getValue()) <= 0) {
            Node tailNode = headNode;
            while (!tailNode.getNextNode().equals(headNode)) {
                tailNode = tailNode.getNextNode();
            }

            Node node = new Node(value);
            node.setNextNode(headNode);
            headNode = node;
            tailNode.setNextNode(node);
        } else {
            Node previousNode = headNode;
            while (previousNode.getNextNode().getValue().compareTo(value) < 0 && !previousNode.getNextNode().equals(headNode)) {
                previousNode = previousNode.getNextNode();
            }

            Node node = new Node(value);
            node.setNextNode(previousNode.getNextNode());
            previousNode.setNextNode(node);
        }
    }

}
