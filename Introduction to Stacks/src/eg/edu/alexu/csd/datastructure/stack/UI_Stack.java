package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;

public class UI_Stack {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Stack s = new Stack();
		char choose;
		String input;
		while (true) {
			System.out.println("Please choose an action\n---------------------------\n1)push an element to stack\n"
					+ "2)pop an element from the stack\n3)the top element of the stack\n4)the size of the stack\n5)exit");
			choose = scan.next().charAt(0);
			switch(choose) {
			case '1': System.out.println("enter your element");
					input = scan.next();
					s.push(input);
					System.out.println("Done....!");
					break;
			case '2':try {
						input = (String) s.pop();
						System.out.println("the deleted element is : "+input);
						break;
					}catch (Exception e) {
						System.out.println("the stack is empty...can't delete");
						break;
					}
			case '3':System.out.println("the element is : "+s.peek());
					break;
			case '4':System.out.println("the size equals : "+s.size());
					break;
			case '5':System.exit(0); 
			
			default:System.out.println("please choose an invalid integer");
			
			}
		}
	}
}
