package eg.edu.alexu.csd.datastructure.stack;

public class UI_Stack {
	
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(100);
		s.push("ahmed");
		s.push('a');
		System.out.println(s.size());
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		s.push("ibrahim");
		s.push("khaled");
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.size());
	}
}
