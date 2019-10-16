/**
 * List.java 
 * 
 * 	
 */
public class List implements MyCollectionInterfaceProject04<Object> {

	private Node<Object> head;
	private int size;
	
	/**
	 * Default constructor. Instantiates the head node and 
	 * initializes the list size to zero. Takes no parameters. 
	 */
	public List() {
		this.size = 0;
		this.head = new Node<Object>(null);
	} // End constructor
	
	/**
	 * Adds an element to the end of the list. 
	 * 
	 * @param newItem : data to be added to the end of the list.
	 * @return true if the data was added, false if not 
	 */
	public boolean add(Object newItem) {
		boolean success = false;
		Node<Object> temp = new Node(newItem);
		Node<Object> current = this.head;
		
		while(current.getNextNode() != null) {
			current = current.getNextNode();
		} // End while
		
		current.setNextNode(temp);
		this.size++;
		success = true;
		
		return success;
	} // End add

	/**
	 * Adds an element to the list at a specified index location.
	 * 
	 * @param newItem : data to be added
	 * @param position : index of where the data should be added
	 * @return true if the data was added, false if not 
	 */
	public boolean add(Object newItem, int position) {
		boolean success = false;
		Node<Object> temp = new Node(newItem);
		Node<Object> current = this.head;
		
		for (int i = 0; i < position && current.getNextNode() != null; i++) {
			current = current.getNextNode();
		} // End for
		
		current.setNextNode(current.getNextNode());
		current.setNextNode(temp);
		this.size++;
		success = true;
		
		return success;
	} // End add

	/**
	 * Removes all entries of a specified entry from the list
	 * 
	 * @param anEntry : Entry to be removed from the list 
	 * @return true if the data was removed, false if not
	 */
	public boolean remove(Object anEntry) {
		boolean success = false;
		
		Node<Object> previous = this.head;
		Node<Object> current = this.head.getNextNode();
		
		for (int i = 0; i < this.size; i++) {
			if (current.getData() == anEntry) {
				previous.setNextNode(current.getNextNode());
				success = true;
				this.size--;
			} // End if
			
			previous = current;
			current = current.getNextNode();
			
		} // End for
		
		return success;
	} // End remove

	/**
	 * Removes all data from the list.
	 */
	public void clear() {
		// Overrites chain by instantiating a new Node
		this.head = new Node<Object>(null);
	} // End clear

	/**
	 * Returns the current size of the list. 
	 */
	public int getCurrentSize() {
		return this.size;
	} // End getCurrentSize

	/**
	 * Checks to see if there is any data in the list
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		// If the list contains data, the head must point to the first item.
		// if the list is empty, the head points to null
		return this.head.getNextNode() == null;
	} // End isEmpty

	/**
	 * Counts the occurance of a specified entry in the list.
	 * 
	 * @param anEntry : data to check against the list
	 * @return occurance of data in the list. 
	 */
	public int getFrequencyOf(Object anEntry) {
		int frequency = 0;
		Node<Object> current = this.head;
		
		while(current.getNextNode() != null) {
			current = current.getNextNode();
			if(current.getData() == anEntry) {
				frequency++;
			} // End if
		} // End while
		
		return frequency;
	} // End getFrequencyOf

	/**
	 * Checks the list to see if a specified entry is in the list. 
	 * 
	 * @return true if the entry exists, false if not
	 */
	public boolean contains(Object anEntry) {
		boolean contains = false;
		Node<Object> current = this.head;
		
		while(current.getNextNode() != null) {
			current = current.getNextNode();
			if (current.getData() == anEntry) {
				contains = true;
			} // End if
		} // End while
		
		return contains;
	} // End contains

	/**
	 * Converts the List into a standard array.
	 * 
	 * @return the list converted into a standard array
	 */
	public Object[] toArray() {
		Object[] returnArray = new Object[100];
		int arrayIndex = 0;
		
		Node<Object> current = this.head;
		
		while(current.getNextNode() != null) {
			current = current.getNextNode();
			returnArray[arrayIndex] = current.getData();
			arrayIndex++;
		} // End while
			
		return returnArray;
	} // End toArray
} // End class
