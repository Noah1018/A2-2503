import java.util.Comparator;

public class SLL <T extends Comparable <T>> {
	private Node<T> head, tail;
	private int size;
	private Comparator<T> comparator;
	
	
	public SLL () {
		head = null;
		size = 0;
		comparator = null; 
		
	}
	
	public SLL (Comparator<T> externalComp) {
		head = null;
		size = 0;
		comparator = externalComp;
		
	}
	
	public int size () {
	    int count = 0;
	    Node<T> current = head;
	    while (current != null) {
	        count++;
	        current = current.getNext();
	    }
	    return count;
	}
	
	
	private int compare (T o1, T o2) {
		if (comparator == null)
			return o1.compareTo(o2);
		else 
			return comparator.compare(o1, o2);
	}
	
	private void addHead(Node<T> n) {
		if (head == null) {
			head = n;
			tail = n;
		} else {
			n.setNext(head);
			head = n;
		}
	}

	private void addTail(Node<T> n) {
		if (tail == null) { // list is empty
			head = n;
			tail = n;
		} else {
			tail.setNext(n);
			tail = n;
		}
	}
	
	private void addInOrder(Node<T> n) {
		if (head == null) {
			head = n;
			tail = n;
		} else {
			if (n.getData().compareTo(head.getData()) <= 0) // less than first item, insert here.
				addHead(n);
			else {
				Node<T> currentNode = head;
				while (currentNode.getNext() != null && n.getData().compareTo(currentNode.getNext().getData()) > 0) {
					currentNode = currentNode.getNext();
				}
				if (currentNode.getNext() == null) { // did not find a place
					addTail(n);
				} else {
					n.setNext(currentNode.getNext());
					currentNode.setNext(n);
				}
			}
		}
	}
	
	//add
	
	private Node<T> delete(T key) {
		Node<T> currentNode = head;
		Node<T> previousNode = head;
		while (currentNode != null) {
			// Visit the node. In this case,
			// if the data in the currentNode is equal to the key,
			// remove the current node from the list by updating the reference
			// of the previous node to point to the node after the currentNode.
			if (currentNode.getData().equals(key)) {
				if (head == tail) {
					head = null;
					tail = null;
					return currentNode;
				}

				if (currentNode == head)
					head = currentNode.getNext();
				else {
					previousNode.setNext(currentNode.getNext());
				}
				if (currentNode == tail) {
					tail = previousNode;
				}

				return currentNode;
			}
			// else, move to the next node
			else {
				previousNode = currentNode;
				currentNode = currentNode.getNext();
			}
		}
		// Return null if it didn't find the node.
		return null;
	}
	
	private Node<T> find(T key) {
		Node<T> currentNode = head;
		while (currentNode != null) {
			// Visit the node. In this case,
			// if the data in the currentNode is equal to the key,
			// return the reference to the node.
			if (currentNode.getData().equals(key))
				return currentNode;
			// else, move to the next node
			else
				currentNode = currentNode.getNext();
		}
		// Return null if it didn't find the node.
		return null;
	}
	
	private void printList() {
		System.out.println();
		Node<T> currentNode = head;
		while (currentNode != null) {
			// Visit the node. In this case, print it out.
			System.out.println(currentNode.toString());
			currentNode = currentNode.getNext();
		}
	}

	

}
