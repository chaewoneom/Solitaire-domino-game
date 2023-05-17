public class UnorderedList<T> implements UnorderedListADT<T> {
	private LinearNode<T> front;  // only a front!!!

	public UnorderedList() {
		front = null;
	}

	public void addAfter(T dataItem, T target) {
		LinearNode<T> current = find(target);

		insert(new LinearNode(dataItem), current);
	}

	public void addToRear(T dataItem) {
		LinearNode<T> prev = null;
		LinearNode<T> current = front;
		while (current != null) {
			prev = current;
			current = current.getNext();
		}
		insert(new LinearNode(dataItem), prev);
	}

	public void addToFront(T dataItem) {
		insert(new LinearNode(dataItem), null);
	}

	public T removeFirst() {
		if (front == null)
			return null;
		T item = front.getDataItem();
		front = front.getNext();
		return item;
	}

	public T removeLast() {
		LinearNode<T> prev = null;
		LinearNode<T> current = front;
		if (current == null)
			return null;
		if (current.getNext() == null)
			return removeFirst();

		while (current.getNext() != null) {
			prev = current;
			current = current.getNext();
		}
		T item = current.getDataItem();
		prev = null;
		return item;
	}

	public T remove(T dataItem) {
		return (delete(find(dataItem)) ? dataItem : null);
	}

public T last() {
		LinearNode<T> prev = null;
		LinearNode<T> current = front;
		while (current != null) {
			prev = current;
			current = current.getNext();
		}
		return prev.getDataItem();
	}

	public boolean contains(T target) {
		return find(target) != null;
	}

	public T first() {
		return (front != null ? front.getDataItem() : null);
	}

	public int size() {
		int count = 0;
		LinearNode<T> current = front;
		while (current != null) {
			count++;
			current = current.getNext();
		}
		return count;
	}

	public boolean isEmpty() {
		return front == null;
	}

	private void insert(LinearNode<T> newNode, LinearNode<T> predecessor) {
		if (predecessor == null) {
			newNode.setNext(front);
			front = newNode;
		} else {
			LinearNode<T> succ = predecessor.getNext();
			newNode.setNext(succ);
			predecessor.setNext(newNode);
		}
	}

	private LinearNode<T> find(T goal) {
		LinearNode<T> current = front;
		while ((current != null) && (!current.getDataItem().equals(goal))) {
			current = current.getNext();
		}

		return current;
	}

	private boolean delete(LinearNode<T> nodeToDelete) {
		LinearNode<T> current, predecessor;
		current = front;
		predecessor = null;
		while ((current != null) && (current != nodeToDelete)) {
			predecessor = current;
			current = current.getNext();
		}
		if (current == null)
			return false;

		if (predecessor != null)
			predecessor.setNext(current.getNext());
		else
			front = front.getNext();
		return true;
	}

	public String toString() {
		String out = "List [";

		LinearNode<T> current = front;
		while (current != null) {
			out += current.getDataItem() + " ";
			current = current.getNext();
		}

		return out + "]";
	}

}
