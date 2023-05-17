public interface UnorderedListADT<T> extends ListADT<T> {
	
   //  Adds the specified dataItem to the front of this list
   public void addToFront (T dataItem);

   //  Adds the specified dataItem to the rear of this list
   public void addToRear (T dataItem);

   //  Adds the specified dataItem after the specified target
   public void addAfter (T dataItem, T target);
}
