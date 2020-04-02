package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Junit_Stack {

	@Test
	void stackTest() {
		Stack s  = new Stack();
		s.push(100);
		s.push("ahmed");
		s.push('a');
		
		assertEquals(3,s.size());
		
		assertEquals('a',s.peek());
		
		assertEquals(3,s.size());
		
		assertEquals('a',s.pop());
		
		assertEquals(2,s.size());
		
		assertEquals("ahmed",s.pop());
		
		assertEquals(1,s.size());
		
		s.push("ibrahim");
		s.push("khaled");
		
		assertEquals("khaled",s.peek());
		
		assertEquals("khaled",s.pop());
		
		assertEquals(2,s.size());
		
		assertEquals("ibrahim",s.pop());
		
		assertEquals(100,s.pop());
		
		assertEquals(0,s.size());
		
		assertThrows(RuntimeException.class,()->{s.pop();});
		
	}

}
