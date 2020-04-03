package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;

public class UI_Apllication {
	public static void main (String [] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		ExpressionEvaluator e = new ExpressionEvaluator();
		String expression;
		System.out.println("\t\t\t\t\twelcome to infix to postfix program");
		while(true) {
			System.out.print("\t\t\t\t\t-----------------------------------\nEnter the infix notation (digits or symbols): ");
			try {
				expression =e.infixToPostfix(s.next()); 
				System.out.println("the postfix notation is : "+expression);
				for (int i=0;i<expression.length();i++) {
					if (e.checkSymbols(expression.charAt(i))) {
						System.out.print("Enter the value of "+expression.charAt(i)+" : ");
						expression=expression.replace(expression.substring(i,i+1),s.next());
					}
				}
				System.out.println("the value of the postfix notation is : "+e.evaluate(expression));
			}
			catch (Exception x) {
				System.out.println(x.getMessage());
			}
		}
	}
}
