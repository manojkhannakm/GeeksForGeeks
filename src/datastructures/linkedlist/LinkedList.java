package datastructures.linkedlist;

/**
 * @author Manoj Khanna
 */

public class LinkedList<E extends Comparable<E>> {

    protected Node headNode;

    public LinkedList(E... values) {
        for (E value : values) {
            addLast(value);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>(10, 20, 30, 40, 50, 60, 70);
        System.out.println("List: " + linkedList);

        linkedList.add(1, 80);
        linkedList.addFirst(90);
        linkedList.addLast(100);
        System.out.println("List: " + linkedList);

        linkedList.remove(1);
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.remove((Integer) 40);
        System.out.println("List: " + linkedList);

        System.out.println("Value: " + linkedList.get(1));
        System.out.println("Value: " + linkedList.getFirst());
        System.out.println("Value: " + linkedList.getLast());

        System.out.println("Size: " + linkedList.size());

        linkedList.swap(0, 1);
        System.out.println("List: " + linkedList);

        linkedList.reverse();
        System.out.println("List: " + linkedList);

        linkedList.mergeSort();
        System.out.println("List: " + linkedList);

        linkedList.merge(new LinkedList<>(10, 40));
        System.out.println("List: " + linkedList);

        linkedList.reverse(3);
        System.out.println("List: " + linkedList);

        linkedList.headNode
                .getNextNode()
                .getNextNode()
                .getNextNode()
                .getNextNode()
                .getNextNode()
                .getNextNode()
                .getNextNode()
                .setNextNode(linkedList.headNode.getNextNode().getNextNode());
        linkedList.removeLoop();
        System.out.println("List: " + linkedList);

        System.out.println("List: " + linkedList.add(new LinkedList<>(7, 5, 9, 4, 6), new LinkedList<>(8, 4)));

        linkedList.rotate(3);
        System.out.println("List: " + linkedList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node = headNode; node != null; node = node.getNextNode()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("->");
            }

            stringBuilder.append(node.getValue());
        }

        return stringBuilder.toString();
    }

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
                headNode = node;
            } else {
                Node previousNode = headNode;
                for (int i = 1; i < index; i++) {
                    previousNode = previousNode.getNextNode();
                }

                Node node = new Node(value);
                node.setNextNode(previousNode.getNextNode());
                previousNode.setNextNode(node);
            }
        }
    }

    public void addFirst(E value) {
        add(0, value);
    }

    public void addLast(E value) {
        add(size(), value);
    }

    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }

        if (index == 0) {
            Node node = headNode.getNextNode();
            headNode.setNextNode(null);
            headNode = node;
        } else {
            Node previousNode = headNode;
            for (int i = 1; i < index; i++) {
                previousNode = previousNode.getNextNode();
            }

            Node node = previousNode.getNextNode();
            previousNode.setNextNode(node.getNextNode());
            node.setNextNode(null);
        }
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size() - 1);
    }

    public void remove(E e) {
        if (headNode == null) {
            return;
        }

        if (headNode.getValue().equals(e)) {
            Node node = headNode.getNextNode();
            headNode.setNextNode(null);
            headNode = node;
        } else {
            Node previousNode = headNode;
            while (previousNode != null) {
                Node node = previousNode.getNextNode();
                if (node != null && node.getValue().equals(e)) {
                    break;
                }

                previousNode = node;
            }

            if (previousNode != null) {
                Node node = previousNode.getNextNode();
                previousNode.setNextNode(node.getNextNode());
                node.setNextNode(null);
            }
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }

        Node node = headNode;
        for (int i = 1; i <= index; i++) {
            node = node.getNextNode();
        }

        return node.getValue();
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size() - 1);
    }

    public int size() {
        int size = 0;
        for (Node node = headNode; node != null; node = node.getNextNode()) {
            size++;
        }

        return size;
    }

    public void swap(int index1, int index2) {
        if (index1 < 0 || index1 >= size() || index2 < 0 || index2 >= size() || index1 == index2) {
            throw new IllegalArgumentException();
        }

        if (index1 > index2) {
            int index = index1;
            index1 = index2;
            index2 = index;
        }

        Node previousNode1 = null,
                node1;
        if (index1 == 0) {
            node1 = headNode;
        } else {
            previousNode1 = headNode;
            for (int i = 1; i < index1; i++) {
                previousNode1 = previousNode1.getNextNode();
            }

            node1 = previousNode1.getNextNode();
        }

        Node previousNode2 = headNode;
        for (int i = 1; i < index2; i++) {
            previousNode2 = previousNode2.getNextNode();
        }

        Node node2 = previousNode2.getNextNode();

        if (previousNode1 != null) {
            previousNode1.setNextNode(node2);
        }

        previousNode2.setNextNode(node1);

        Node node = node1.getNextNode();
        node1.setNextNode(node2.getNextNode());
        node2.setNextNode(node);

        if (index1 == 0) {
            headNode = node2;
        }
    }

    public void reverse() {
        Node previousNode = null,
                node = headNode;
        while (node != null) {
            Node nextNode = node.getNextNode();

            node.setNextNode(previousNode);

            previousNode = node;
            node = nextNode;
        }

        headNode = previousNode;
    }

    public void merge(LinkedList<E> linkedList) {
        if (linkedList == null) {
            throw new IllegalArgumentException();
        }

        Node headNode1 = this.headNode,
                headNode2 = linkedList.headNode;
        if (headNode2 == null) {
            return;
        }

        Node headNode,
                tailNode;
        if (headNode1 != null && headNode1.getValue().compareTo(headNode2.getValue()) < 0) {
            headNode = headNode1;
            tailNode = headNode1;
            headNode1 = headNode1.getNextNode();
        } else {
            headNode = headNode2;
            tailNode = headNode2;
            headNode2 = headNode2.getNextNode();
        }

        while (headNode1 != null || headNode2 != null) {
            if (headNode1 != null && headNode2 != null) {
                if (headNode1.getValue().compareTo(headNode2.getValue()) < 0) {
                    tailNode.setNextNode(headNode1);
                    tailNode = headNode1;
                    headNode1 = headNode1.getNextNode();
                } else {
                    tailNode.setNextNode(headNode2);
                    tailNode = headNode2;
                    headNode2 = headNode2.getNextNode();
                }
            } else if (headNode1 != null) {
                tailNode.setNextNode(headNode1);
                tailNode = headNode1;
                headNode1 = headNode1.getNextNode();
            } else {
                tailNode.setNextNode(headNode2);
                tailNode = headNode2;
                headNode2 = headNode2.getNextNode();
            }
        }

        this.headNode = headNode;
    }

    private Node mergeSort(Node headNode1) {
        int size = 0;
        for (Node node = headNode1; node != null; node = node.getNextNode()) {
            size++;
        }

        if (size == 1) {
            return headNode1;
        }

        Node tailNode1 = headNode1;
        for (int i = 1; i < size / 2; i++) {
            tailNode1 = tailNode1.getNextNode();
        }

        Node headNode2 = tailNode1.getNextNode();
        tailNode1.setNextNode(null);

        headNode1 = mergeSort(headNode1);
        headNode2 = mergeSort(headNode2);

        Node headNode,
                tailNode;
        if (headNode1.getValue().compareTo(headNode2.getValue()) < 0) {
            headNode = headNode1;
            tailNode = headNode1;
            headNode1 = headNode1.getNextNode();
        } else {
            headNode = headNode2;
            tailNode = headNode2;
            headNode2 = headNode2.getNextNode();
        }

        while (headNode1 != null || headNode2 != null) {
            if (headNode1 != null && headNode2 != null) {
                if (headNode1.getValue().compareTo(headNode2.getValue()) < 0) {
                    tailNode.setNextNode(headNode1);
                    tailNode = headNode1;
                    headNode1 = headNode1.getNextNode();
                } else {
                    tailNode.setNextNode(headNode2);
                    tailNode = headNode2;
                    headNode2 = headNode2.getNextNode();
                }
            } else if (headNode1 != null) {
                tailNode.setNextNode(headNode1);
                tailNode = headNode1;
                headNode1 = headNode1.getNextNode();
            } else {
                tailNode.setNextNode(headNode2);
                tailNode = headNode2;
                headNode2 = headNode2.getNextNode();
            }
        }

        return headNode;
    }

    public void mergeSort() {
        if (headNode != null) {
            headNode = mergeSort(headNode);
        }
    }

    public void reverse(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }

        if (headNode == null) {
            return;
        }

        Node previousNode = null,
                node = headNode,
                tailNode1 = node;
        boolean flag = false;
        while (node != null) {
            Node tailNode2 = node;
            for (int i = 0; i < size && node != null; i++) {
                Node nextNode = node.getNextNode();

                node.setNextNode(previousNode);

                previousNode = node;
                node = nextNode;
            }

            tailNode1.setNextNode(previousNode);
            tailNode1 = tailNode2;

            if (!flag) {
                headNode = previousNode;
                flag = true;
            }
        }

        tailNode1.setNextNode(null);
    }

    public void removeLoop() {
        if (headNode == null) {
            return;
        }

        Node node1 = headNode,
                node2 = headNode.getNextNode();
        while (node2 != null && !node1.equals(node2)) {
            node1 = node1.getNextNode();

            node2 = node2.getNextNode();
            if (node2 != null) {
                node2 = node2.getNextNode();
            }
        }

        if (node2 != null && node1.equals(node2)) {
            Node startNode = headNode;
            while (startNode != null) {
                Node endNode = node1;
                while (!endNode.getNextNode().equals(startNode)
                        && !endNode.getNextNode().equals(node1)) {
                    endNode = endNode.getNextNode();
                }

                if (endNode.getNextNode().equals(startNode)) {
                    endNode.setNextNode(null);
                    break;
                }

                startNode = startNode.getNextNode();
            }
        }
    }

    public LinkedList<Integer> add(LinkedList<Integer> linkedList1, LinkedList<Integer> linkedList2) {
        if (linkedList1 == null || linkedList2 == null) {
            throw new IllegalArgumentException();
        }

        int size1 = linkedList1.size(),
                size2 = linkedList2.size();
        if (size1 < size2) {
            for (int i = 0; i < size2 - size1; i++) {
                linkedList1.addLast(0);
            }
        } else if (size1 > size2) {
            for (int i = 0; i < size1 - size2; i++) {
                linkedList2.addLast(0);
            }
        }

        LinkedList<Integer> linkedList = new LinkedList<>();
        Node node1 = (Node) linkedList1.headNode,
                node2 = (Node) linkedList2.headNode;
        int carry = 0;
        while (node1 != null && node2 != null) {
            int n = (Integer) node1.getValue()
                    + (Integer) node2.getValue()
                    + carry;
            linkedList.addLast(n % 10);

            carry = n / 10;
            node1 = node1.getNextNode();
            node2 = node2.getNextNode();
        }

        if (carry > 0) {
            linkedList.addLast(carry);
        }

        return linkedList;
    }

    public void rotate(int size) {
        if (size < 0 || size > size()) {
            throw new IllegalArgumentException();
        }

        if (headNode == null) {
            return;
        }

        Node node = headNode;
        for (int i = 1; i < size; i++) {
            node = node.getNextNode();
        }

        Node tailNode = headNode;
        while (tailNode.getNextNode() != null) {
            tailNode = tailNode.getNextNode();
        }

        tailNode.setNextNode(headNode);
        headNode = node.getNextNode();
        node.setNextNode(null);
    }

    protected class Node {

        private E value;
        private Node nextNode;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

    }

}
