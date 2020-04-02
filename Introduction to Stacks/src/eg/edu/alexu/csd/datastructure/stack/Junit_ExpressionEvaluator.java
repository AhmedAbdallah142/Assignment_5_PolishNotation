package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Junit_ExpressionEvaluator {

	ExpressionEvaluator e = new ExpressionEvaluator();
	@Test
	void infixToPostfixTest() {
		
		assertEquals("12.5 2 3 - 4 + / 5 1 - * 3 *",e.infixToPostfix("(12.5/(2-3+4))*(5-1)*3"));
		
		assertEquals("20 0 2 7 - 0 5 4 + 8 1 - - - + - /",e.infixToPostfix("20/-(2-7+-(5+4-(8-1)))"));
		
		assertEquals("2 40 8 + + 9 +",e.infixToPostfix("((2+(40+8))+9)"));
		
		
		//the next two methods to check the multiply without "*" between parenthesis
		assertEquals("a b c - d + / e a - * c *",e.infixToPostfix("(a/(b-c+d))*(e-a)*c"));
		assertEquals("a b c - d + / e a - * c *",e.infixToPostfix("(a/(b-c+d))(e-a)*c"));
		
		
		//the next two methods to check the multiply without "*" between Symbols
		assertEquals("a b *",e.infixToPostfix("a*b"));
		assertEquals("a b *",e.infixToPostfix("ab"));
		
		
		//the next two methods to check the multiply without "*" between digits and symbols or digits and parenthesis
		assertEquals("25 5 4 + *",e.infixToPostfix("25(5+4)"));
		assertEquals("5 x *",e.infixToPostfix("5x"));
				
		
		assertEquals("a 0 b - +",e.infixToPostfix("a+-b"));
		
		assertEquals("0 2 7 - 0 5 - + -",e.infixToPostfix("0-(2-7+-5)"));
		
		assertEquals("5 0 2 7 - 0 5 - + - /",e.infixToPostfix("5/-(2-7+-5)"));
		
		//check if the user entered a negative number at the first
		assertEquals("0 3 - 4 *",e.infixToPostfix("-3*4"));
		
		assertEquals("2 0 3 - 4 * +",e.infixToPostfix("2+-3*4"));
		
		assertEquals("1 2 + 7 *",e.infixToPostfix("(1+2)*7"));
		
		assertEquals("12 16 8 * 6 * - 5 + 3 1 / -",e.infixToPostfix("12-16*8*6+5-3/1"));
		
		assertEquals("3 2 + 2 * 4 + 5 - 10 +",e.infixToPostfix("(((3+2)*2+4)-5)+10"));
		
		// check if there are error in the parenthesis
		assertThrows(RuntimeException.class,()-> {e.infixToPostfix( "14+(2+5+5" );});
		
		//check the invalid Symbols
		assertThrows(RuntimeException.class,()-> {e.infixToPostfix( "32&132" );});
		assertThrows(RuntimeException.class,()-> {e.infixToPostfix( "15+2$8" );});
		assertThrows(RuntimeException.class,()-> {e.infixToPostfix( "15+28!" );});
		
	}
	
	
	@Test
	void evaluatePostfixTest() {
		
		assertEquals(50,e.evaluate("12.5 2 3 - 4 + / 5 1 - * 3 *"));
		
		assertEquals(2,e.evaluate("20 0 2 7 - 0 5 4 + 8 1 - - - + - /"));
		
		assertEquals(59,e.evaluate("2 40 8 + + 9 +"));
		
		//the three next methods to check evaluate with symbols
		assertThrows(RuntimeException.class,()-> {e.evaluate("a b c - d + / e a - * c *");});
		
		assertThrows(RuntimeException.class,()-> {e.evaluate("a b c - d + / e a - * c *");});
		
		assertThrows(RuntimeException.class,()-> {e.evaluate("a 0 b - +");});
		
		//check divide by zero
		assertThrows(RuntimeException.class,()-> {e.evaluate("10 20 15 - 5 - /");});
		
		//check invalid postfix
		assertThrows(RuntimeException.class,()-> {e.evaluate("1 2 + -");});
		assertThrows(RuntimeException.class,()-> {e.evaluate("1*3");});
		assertThrows(RuntimeException.class,()-> {e.evaluate("-3");});
		
		assertEquals(10,e.evaluate("0 2 7 - 0 5 - + -"));
		
		assertEquals(0,e.evaluate("5 0 2 7 - 0 5 - + - /"));
		
		assertEquals(-12,e.evaluate("0 3 - 4 *"));
		
		assertEquals(-10,e.evaluate("2 0 3 - 4 * +"));
		
		assertEquals(21,e.evaluate("1 2 + 7 *"));
		
		assertEquals(-754,e.evaluate("12 16 8 * 6 * - 5 + 3 1 / -"));
		
		assertEquals(19,e.evaluate("3 2 + 2 * 4 + 5 - 10 +"));
	}
	

}
