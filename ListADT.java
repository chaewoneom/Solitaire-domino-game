public interface ListADT<T> {
   public T removeFirst ( ); //  Removes and returns first data item
   public T removeLast ( ); //  Removes and returns last data item
   public T remove (T dataItem); //  Removes and returns dataItem
   public T first ( );  //  Returns the first data item
   public T last ( );  //  Returns the last data item
   public boolean isEmpty( ); //  Returns true if this list is empty
   //  Returns true if this list contains target
   public boolean contains (T target);
   public int size( ); //  Returns the number of data items in this list
   public String toString( ); //  String representation of this list
}