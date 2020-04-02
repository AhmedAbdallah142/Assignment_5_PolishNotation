package eg.edu.alexu.csd.datastructure.stack;

public class SingleLinkedList {

	class Node {
		Object element;
		Node next;
	};
	private Node head,tail;
	private int size;
	public SingleLinkedList(){
		head=tail=null;
		size = 0;
	};

	public void add(int index, Object element) {
		Node Nnew =new Node();
		Nnew.element=element;
		Node position;
		position = head;
		if (index==size) { // if the user want to add an element in the last of a list 
			add(element);
		}else {
			for (int i=0;i<index-1;i++) {
				position=position.next;
			}
			Nnew.next=position.next;
			position.next=Nnew;
			size++;
		}
	}

	public void add(Object element) {
		Node Nnew = new Node();
		Nnew.element=element;
		if (isEmpty()) {
			head=tail=Nnew;
			Nnew.next=null;
		}else {
			
			tail.next=Nnew;
			tail=Nnew;
			Nnew.next=null;
		}
		size++;
	}

	public Object get(int index) {
            if(index>=size) //check if the user entered a not found index
                return null;
            Node Temp;
            Temp=head;
            for (int i=0;i<index;i++) {
                    Temp=Temp.next;
            }
            return Temp.element; 
	}

	public void set(int index, Object element) {
		Node Temp;
		Temp=head;
		for (int i=0;i<index;i++) {
			Temp=Temp.next;
		}
		Temp.element=element;
	}

	public void clear() {
		head=tail=null;
		size=0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	//any throw Exception can be deleted and that doesn't affect in our application but we suppose using our classes in a general way
	
	public void remove(int index) {
		if (isEmpty()) {
			throw new RuntimeException("the list is empty can't remove from it");
		}else {
			if (index==0) {
				head=head.next;
			}else if (index==size-1) {
				Node N1=head;
				while(true) {
					if (N1.next==tail)break;
				}
				tail=N1;
				tail.next=null;
			}else {
				Node N1=head,N2=head.next;
				for (int i=0;i<index-1;i++) {
					N1=N1.next;
					N2=N2.next;
				}
				N2=N2.next;
				N1.next=N2;
			}
			size--;
		}
		
	}

	public int size() {
		return size;
	}

	public SingleLinkedList sublist(int fromIndex, int toIndex) {
		if (isEmpty()) {
			throw new RuntimeException("the list is empty");
		}else {
			if (toIndex>size-1) {
				throw new RuntimeException("the border of the list you need is bigger than the base list");
			}else {
				SingleLinkedList sublist = new SingleLinkedList();
				sublist.size=toIndex-fromIndex+1;
				Node temp=head;
				for (int i=0;i<size;i++) {
					if (i==fromIndex) {
						sublist.head=temp;
					}else if(i==toIndex) {
						sublist.tail=temp;
					}
					temp=temp.next;
				}
				return sublist;
			}
		}
	}

	public boolean contains(Object o) {
		if (isEmpty()) {
			return false;
		}else {
			boolean test=false;
			Node temp = head;
			for (int i=0;i<size;i++) {
				if (temp.element==o) {
					test=true;
					break;
				}
				temp=temp.next;
			}
			return test;
		}
	}
	
	/*public void print() { //this function for testing the code
		Node temp=head;
		while (temp!=null) {
			System.out.println(temp.element);
			temp=temp.next;
		}
	}*/
  
	public Object[] listToArr(boolean IntArr2D) {
		if(IntArr2D){
                    int[][] array = new int[size][];
                    for (int i=0;i<size;i++)
                        array[i] = (int[])get(i);
                    return array;
                } else {
                }
                Object[] arr = new Object [size];
		for (int i=0;i<size;i++) {
			arr[i] = get(i);
		}
                return arr;
	}
  
	public int[][] SpecialListToArr (SingleLinkedList list){
		int[][] arr =new int[list.size()/2][2];
		for (int i =0 ;i<list.size()/2;i++) {
			arr[i][0]= (int) list.get(2*i);
			arr[i][1]=(int) list.get(2*i+1);
                        
		}
		return arr;
	}
	
	public SingleLinkedList arrToList (int[][] arr) {
		SingleLinkedList list = new SingleLinkedList();
            for (int[] arr1 : arr) {
                list.add(arr1);
            }
		return list;
	}
}
