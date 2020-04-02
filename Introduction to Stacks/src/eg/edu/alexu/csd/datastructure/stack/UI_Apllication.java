package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;
import eg.edu.alexu.csd.datastructure.linkedList.csX22.Single_Linked_list;

public class UI_Apllication {
	public static void main (String [] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		ExpressionEvaluator e = new ExpressionEvaluator();
		Single_Linked_list l = new Single_Linked_list();
		String expression;
		System.out.println("\t\t\t\t\twelcome to infix to postfix program");
		while(true) {
			System.out.print("\t\t\t\t\t-----------------------------------\nEnter the infix notation (digits or symbols): ");
			try {
				expression =e.infixToPostfix(s.next()); 
				System.out.println("the postfix notation is : "+expression);
				for (int i=0;i<expression.length();i++) {
					if (e.checkSymbols(expression.charAt(i))) {
						l.add(expression.charAt(i));
					}
				}
				if (l.isEmpty()) {
					System.out.println("the value of the postfix notation is : "+e.evaluate(expression));
					System.out.println("the value of symbols must be digits or you enterd an invalid postfix");	
				}
				else {
					for (int i=0;i<l.size();i++) {
						System.out.print("Enter the value of "+l.get(i)+" : ");
						l.set(i, s.next());
					}
					int x = 0;
					for (int i=0;i<expression.length();i++) {
						if (e.checkSymbols(expression.charAt(i))) {
							expression=expression.replace(expression.substring(i,i+1), (String)l.get(x));
							x++;
						}
					}
					System.out.println("the value of the postfix notation is : "+e.evaluate(expression));
					System.out.println("the value of symbols must be digits or you enterd an invalid postfix");
				}	
			}
			catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}
}
