package eg.edu.alexu.csd.datastructure.stack;

public class UI_ExpressionEvaluator {

	public static void main(String[] args) {
		ExpressionEvaluator e = new ExpressionEvaluator();
		System.out.println(e.infixToPostfix("(12.5/(2-3+4))*(5-1)*3"));
		System.out.println(e.evaluate(e.infixToPostfix("(12.5/(2-3+4))*(5-1)*3")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("((2+(40+8))+9)"));
		System.out.println(e.evaluate(e.infixToPostfix("((2+(40+8))+9)")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("0-(2-7+-5)"));
		System.out.println(e.evaluate(e.infixToPostfix("0-(2-7+-5)")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("20/-(2-7+-(5+4-(8-1)))"));
		System.out.println(e.evaluate(e.infixToPostfix("20/-(2-7+-(5+4-(8-1)))")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("5/-(2-7+-5)"));
		System.out.println(e.evaluate(e.infixToPostfix("5/-(2-7+-5)")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("a+-b"));
		
		System.out.println();
		System.out.println(e.infixToPostfix("(a/(b-c+d))*(e-a)*c"));
		System.out.println(e.infixToPostfix("(a/(b-c+d))(e-a)*c"));

		System.out.println();
		System.out.println(e.infixToPostfix("-3*4"));
		System.out.println(e.evaluate(e.infixToPostfix("-3*4")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("2+-3*4"));
		System.out.println(e.evaluate(e.infixToPostfix("2+-3*4")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("(1+2)*7"));
		System.out.println(e.evaluate(e.infixToPostfix("(1+2)*7")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("12-16*8*6+5-3/1"));
		System.out.println(e.evaluate(e.infixToPostfix("12-16*8*6+5-3/1")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("(((3+2)*2+4)-5)+10"));
		System.out.println(e.evaluate(e.infixToPostfix("(((3+2)*2+4)-5)+10")));
		
		System.out.println();
		System.out.println(e.infixToPostfix("10/(20-15-5)"));
	}
}