package eg.edu.alexu.csd.datastructure.stack;

public class Stack implements IStack {

	private class Node {
		Object element;
		Node next;
		Node (Object element){
			this.element=element;
		}
	}
	private Node top;
	private int size;
	Stack(){
		this.top=null;
		this.size=0;
	}
	
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

	@Override
	public Object peek() {
		Object temp = this.top.element;
		return temp;
	}

	@Override
	public void push(Object element) {
		Node newNode = new Node(element);
		newNode.next=this.top;
		this.top = newNode;
		this.size++;
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	@Override
	public int size() {
		return this.size;
	}

}
