public class StackLL<T> implements StackADT<T>, Named {
	private LinearNode<T> top;
	private int count;
	private String name;

	public StackLL(String name) {
		this.name = name;
		top = null;
		count = 0;
	}
	
	public StackLL() {
		this("Stack");
	}

	public String getName() {
		return name;
	}
	
	public void push(T dataItem) {
		LinearNode<T> temp = new LinearNode<T>((dataItem));
		temp.setNext(top);
		top = temp;
		count++;
	}

	public T pop() {
		if (isEmpty())
			throw new EmptyCollectionException("Stack");
		T result = top.getDataItem();
		top = top.getNext();
		count--;
		return result;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int size() {
		return count;
	}

	public T peek() {
		if (isEmpty())
			throw new EmptyCollectionException("Stack");
		return top.getDataItem();
	}

	public String toString() {
		String out = name +" |top>";
		LinearNode<T> curr = top;
		while (curr != null) {
			out += curr.getDataItem().toString()+" ";
			curr = curr.getNext();
		}
		return out+"<|";
	}
	
	public boolean contains(T dataItem) {
		LinearNode<T> curr = top;
		while (curr != null) {
			if (curr.getDataItem().equals(dataItem)) return true;
			curr = curr.getNext();
		}
		return false;
	}	
	public String showNamedContent() {
		String out = "";
		LinearNode<T> curr = top;
		while (curr != null) {
			T t = curr.getDataItem();
			if (t instanceof Named) out += ((Named) t).getName()+" ";
			else out += curr.getDataItem().toString();
			curr = curr.getNext();
		}
		return out+"|";
	}
	
	public static void main(String[] args) {
		int[] list = { 4, 5, 1, 1 };

		StackADT<Integer> weird = new StackLL<Integer>("w");
		
		for(int i=0;i<list.length;i++) weird.push(list[i]);
		System.out.println(((Named)weird).showNamedContent());
		
	}
}