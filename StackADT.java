public interface StackADT<T> {
   //  Adds one element to the top of this stack
   public void push (T dataItem);
   //  Removes and returns the top element of this stack
   public T pop( );
   //  Returns the top element of this stack
   public T peek( );   
   //  Returns true if this stack is empty
   public boolean isEmpty( );
   //  Returns the number of elements in this stack
   public int size( );
   //  Returns a string representation of this stack
   public String toString( );
}

