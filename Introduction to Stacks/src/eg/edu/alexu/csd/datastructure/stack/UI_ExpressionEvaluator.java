package eg.edu.alexu.csd.datastructure.stack;

public class UI_ExpressionEvaluator {

	public static void main(String[] args) {
		ExpressionEvaluator e = new ExpressionEvaluator();
		System.out.println(e.infixToPostfix("(12/(2-3+4))*(5-1)*3"));
		System.out.println(e.evaluate(e.infixToPostfix("(12/(2-3+4))*(5-1)*3")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("2+3*4"));
		System.out.println(e.evaluate(e.infixToPostfix("2+3*4")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("(1+2)*7"));
		System.out.println(e.evaluate(e.infixToPostfix("(1+2)*7")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("12-16*8*6+5-3/1"));
		System.out.println(e.evaluate(e.infixToPostfix("12-16*8*6+5-3/1")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("(((3+2)*2+4)-5)+10"));
		System.out.println(e.evaluate(e.infixToPostfix("(((3+2)*2+4)-5)+10")));
	}
}