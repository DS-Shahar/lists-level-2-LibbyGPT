package list2;

public class Main {
    public static void main(String[] args) {
        int[] array1 = {5, 8, 10, 8, 2, 9,7,2,5,3,10};
        int[] array2 = {1, 2, 3, 4};
        int[] array3 = {1, 3, 5, 4};

        Node<Integer> list1 = makeListFromArray(array1);
        Node<Integer> list2 = makeListFromArray(array2);
        Node<Integer> list3 = makeListFromArray(array3);

        Node<Integer> mergedList = combineLists(list1, list3);
        System.out.println(mergedList);

        System.out.println(sortMyList(makeListFromArray(array2)));

        System.out.println(findDistance(list1, 8));
        
        System.out.println(checkAllUnique(makeListFromArray(array2)));
        
        System.out.println(shortenList(makeListFromArray(array1)));
        
        System.out.println(slowRaise(makeListFromArray(array1)));
    }

    public static Node<Integer> makeListFromArray(int[] array) {
        if (array.length == 0) return null;
        Node<Integer> head = new Node<>(array[0]);
        Node<Integer> current = head;
        for (int i = 1; i < array.length; i++) {
            current.setNext(new Node<>(array[i]));
            current = current.getNext();
        }
        return head;
    }

    public static Node<Integer> combineLists(Node<Integer> L1, Node<Integer> L2) {
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

    public static Node<Integer> sortMyList(Node<Integer> head) {
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

    public static int findDistance(Node<Integer> head, int value) {
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

    public static boolean checkAllUnique(Node<Integer> head) {
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
    
    public static Node<Integer> shortenList(Node<Integer> head) {
        Node<Integer> newList = new Node<Integer>(-1);  
        Node<Integer> currentNewList = newList;  

        while (head != null) {
            Node<Integer> current = head;
            while (current != null) {
                if (!checkIfExists(newList, current.getValue())) {
                    currentNewList.setNext(new Node<Integer>(current.getValue()));
                    currentNewList = currentNewList.getNext();
                }
                current = current.getNext();
            }
            head = head.getNext();
        }

        return newList.getNext();  
    }

    
    public static boolean checkIfExists(Node<Integer> head, int value) {
        while (head != null) {
            if (head.getValue() == value) {
                return true;
            }
            head = head.getNext();
        }
        return false;
    }
    
    public static int slowRaise(Node<Integer> head) {
    	Node<Integer> current = head.getNext();
    	int count = 0;

    	Node<Integer> countA = new Node<Integer>(-1);
    	while(head != null && current != null) {
    		
	    		if(head.getValue() <= current.getValue()) {
	    			count++;
	    			current = current.getNext();
	    			head = head.getNext();
    			}
	    		else {
	    			break;
	    		}
    		head = head.getNext();
    		current = current.getNext();
    		countA.setNext(new Node<Integer> (count));
    	}
    	
    	return findMax(countA);
    }
    
    public static int findMax(Node<Integer> l1) {
    	if(l1 == null) {
    		return Integer.MIN_VALUE;
    	}
    	int maxValue = findMax(l1.getNext());
    	return Math.max(l1.getValue(), maxValue);
    }
    
    
}
