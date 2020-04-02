package eg.edu.alexu.csd.datastructure.stack;

import java.util.Scanner;
import eg.edu.alexu.csd.datastructure.linkedList.csX22.Single_Linked_list;

public class UI_Apllication {
	public static void main (String [] args) {
		Scanner s = new Scanner(System.in);
		ExpressionEvaluator e = new ExpressionEvaluator();
		char choose ;
		String expression;
		System.out.println("Please choose an action\n-------------------------------\n1)convert from infix to postfix Using Symbols or Digits "
				+ "\n2)calculate a postfix notation(Digits only)\n3)convert and calculate in one step(Digits or Symbols){recommended}\n4)exit");
		choose = s.next().charAt(0);
		switch (choose) {
		case '1':System.out.println("Enter the infix notation : ");
				s.nextLine();
				expression = s.nextLine();
				System.out.println("the postfix notation is : "+e.infixToPostfix(expression));
				break;
		case '2': System.out.println("Enter the postfix notation : ");
				s.nextLine();
				expression = s.nextLine();
				evaluateUI(expression);
				break;
		case '3':System.out.println("Enter the infix notation : ");
				s.nextLine();
				expression = s.nextLine();
				String out = e.infixToPostfix(expression);
				System.out.println("the postfix notation is : "+out);
				evaluateUI(out);
				break;
		case '4':System.exit(0);
		default :System.out.println("Enter a correct number");	
		}
		s.close();
	}
	/**
	 * check if the expression have symbols and ask the user for the values of each symbol 
	 * then evaluate the result 
	 * if the expression doesn't have symbols then this method evaluate the expression
	 * @param expression  : the expression to evaluate (alphanumeric)
	 */
	private static void evaluateUI (String expression) {
		Scanner s = new Scanner(System.in);
		ExpressionEvaluator e = new ExpressionEvaluator();
		Single_Linked_list l = new Single_Linked_list();
		for (int i=0;i<expression.length();i++) {
			if (e.checkSymbols(expression.charAt(i))) {
				l.add(expression.charAt(i));
			}
		}
		if (l.isEmpty()) {
			try {
				System.out.println("the value of the postfix notation is : "+e.evaluate(expression));
			}catch (Exception e1) {
				System.out.println("the value of symbols must be digits or you enterd an invalid postfix");
			}	
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
			try {
				System.out.println("the value of the postfix notation is : "+e.evaluate(expression));
			}catch (Exception e1) {
				System.out.println("the value of symbols must be digits or you enterd an invalid postfix");
			}	
		}
		s.close();
	}
}
