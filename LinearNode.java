public class LinearNode<T> {
	private LinearNode<T> next;
	private T dataItem;

	public LinearNode(T dataItem) {
		this.next = null;
		this.dataItem = dataItem;
	}

	public LinearNode<T> getNext() {
		return next;
	}

	public void setNext(LinearNode<T> node) {
		next = node;
	}

	public T getDataItem() {
		return dataItem;
	}

	public void setDataItem(T value) {
		dataItem = value;
	}
}