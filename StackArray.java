public class StackArray<T> implements StackADT<T> {
	private T[] stack; // Array for the data
	private int top; // Top of stack
	final int DEFAULT_CAPACITY = 100;

	public StackArray() {
		top = 0;
		stack = (T[]) (new Object[DEFAULT_CAPACITY]);
	}

	public StackArray(int initialCapacity) {
		top = 0;
		stack = (T[]) (new Object[initialCapacity]);
	}

// -----------------------------------------------------------------
// Adds the specified element to the top of the stack,
// expanding the capacity of the stack array if necessary
//-----------------------------------------------------------------
	public void push(T dataItem) {
		if (top == stack.length)
			expandCapacity();

		stack[top] = dataItem;
		top++;
	}

//Helper method to create a new array to store the
//contents of the stack, with twice the capacity 
	private void expandCapacity() {
		T[] larger = (T[]) (new Object[stack.length * 2]);

		for (int index = 0; index < stack.length; index++)
			larger[index] = stack[index];

		stack = larger;
	}

//Removes the element at the top of the stack and returns a
//reference to it. Throws an EmptyCollectionException if the 
//stack is empty.
	public T pop() throws EmptyCollectionException {
		if (top == 0)
			throw new EmptyCollectionException("Empty stack");
		top--;
		T topItem = stack[top];
		stack[top] = null;
		return topItem;
	}

//Returns the element at the top of the stack. Throws an
//EmptyCollectionException if the stack is empty.
	public T peek() throws EmptyCollectionException {
		if (top == 0)
			throw new EmptyCollectionException("Empty stack");
		return stack[top - 1];
	}

//Returns the number of elements in the stack
	public int size() {
		return top;
	}

//  Returns true if the stack is empty and false otherwise 
	public boolean isEmpty() {
		return (top == 0);
	}

//-----------------------------------------------------------------
//Returns a string representation of this stack. 
//-----------------------------------------------------------------
	public String toString() {
		String result = "AStack:";

		for (int index = 0; index < top; index++)
			result = result+" "+ stack[index].toString() + ".";
		return result;
	}
}
