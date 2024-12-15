package list2;

public class Main {
    public static void main(String[] args) {
        int[] array1 = {5, 8, 3, 8, 2, 9};
        int[] array2 = {1, 2, 3, 4};
        int[] array3 = {1, 3, 5, 4};

        Node<Integer> list1 = buildListFromArray(array1);
        Node<Integer> list2 = buildListFromArray(array2);
        Node<Integer> list3 = buildListFromArray(array3);

        Node<Integer> mergedList = mergeLists(list1, list3);
        System.out.println(mergedList);

        System.out.println(sortList(buildListFromArray(array2)));

        System.out.println(calculateDistance(list1, 8));

    }

    public static Node<Integer> buildListFromArray(int[] array) {
        if (array.length == 0) return null;
        Node<Integer> head = new Node<>(array[0]);
        Node<Integer> current = head;
        for (int i = 1; i < array.length; i++) {
            current.setNext(new Node<>(array[i]));
            current = current.getNext();
        }
        return head;
    }

    public static Node<Integer> mergeLists(Node<Integer> L1, Node<Integer> L2) {
        Node<Integer> dummy = new Node<>(0);
        Node<Integer> tail = dummy;

        while (L1 != null && L2 != null) {
            if (L1.getValue() <= L2.getValue()) {
                tail.setNext(L1);
                L1 = L1.getNext();
            } else {
                tail.setNext(L2);
                L2 = L2.getNext();
            }
            tail = tail.getNext();
        }

        if (L1 != null) {
            tail.setNext(L1);
        } else if (L2 != null) {
            tail.setNext(L2);
        }

        return dummy.getNext();
    }

    public static Node<Integer> sortList(Node<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node<Integer> current = head;
        Node<Integer> index = null;
        int temp;

        while (current != null) {
            index = current.getNext();
            while (index != null) {
                if (current.getValue() > index.getValue()) {
                    temp = current.getValue();
                    current.setValue(index.getValue());
                    index.setValue(temp);
                }
                index = index.getNext();
            }
            current = current.getNext();
        }
        return head;
    }

    public static int calculateDistance(Node<Integer> head, int value) {
        int distanceFromStart = -1;
        int distanceFromEnd = -1;
        int index = 0;
        int size = 0;

        Node<Integer> current = head;
        while (current != null) {
            size++;
            if (current.getValue() == value) {
                if (distanceFromStart == -1) {
                    distanceFromStart = index;
                }
                distanceFromEnd = size - index - 1;
            }
            current = current.getNext();
            index++;
        }

        if (distanceFromStart == -1) {
            return -1;
        }
        return distanceFromStart + distanceFromEnd;
    }

    public static boolean areAllElementsUnique(Node<Integer> head) {
        Node<Integer> current = head;
        while (current != null) {
            Node<Integer> index = current.getNext();
            while (index != null) {
                if (current.getValue().equals(index.getValue())) {
                    return false;
                }
                index = index.getNext();
            }
            current = current.getNext();
        }
        return true;
    }
}
