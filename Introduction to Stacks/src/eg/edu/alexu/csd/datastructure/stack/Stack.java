package eg.edu.alexu.csd.datastructure.stack;

public class Stack implements IStack {

	/**
	 * the node used to implement Stack
	 * consist of element and a pointer to the next element
	 * @author ahmed
	 *
	 */
	private class Node {
		Object element;
		Node next;
		Node (Object element){
			this.element=element;
		}
	}
	private Node top;
	private int size;
	public Stack(){
		this.top=null;
		this.size=0;
	}
	/** 
	 * Removes the element at the top of stack and returns that element. *
	 * @return top of stack element, or through exception if empty 
	 */
	@Override
	public Object pop() {
		Object temp = null;
		if (isEmpty()) {
			throw new RuntimeException("the Stack is empty can't remove from it");
		}else {
			temp = this.top.element;
			this.top=this.top.next;
			this.size--;
		}
		return temp;
	}

	/** 
	 * Get the element at the top of stack without removing it from stack. 
	 *
	 * @return top of stack element, or through exception if empty 
	 */
	@Override
	public Object peek() {
		Object temp = this.top.element;
		return temp;
	}
	/** 
	 * Pushes an item onto the top of this stack. 
	 *
	 * @param object 
	 * to insert 
	 */
	@Override
	public void push(Object element) {
		Node newNode = new Node(element);
		newNode.next=this.top;
		this.top = newNode;
		this.size++;
	}

	/** 
	 * Tests if this stack is empty 
	 * 
	 * @return true if stack empty 
	 */
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	/**
	 * Returns the number of elements in the stack. 
	 *
	 * @return number of elements in the stack 
	 */
	@Override
	public int size() {
		return this.size;
	}

}
