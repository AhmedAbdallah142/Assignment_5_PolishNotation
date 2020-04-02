package eg.edu.alexu.csd.datastructure.stack;

public class ExpressionEvaluator implements IExpressionEvaluator {
	
	/** 
	 * Takes a symbolic/numeric infix expression as input and converts it to 
	 * postfix notation. There is no assumption on spaces between terms or the 
	 * length of the term (e.g., two digits symbolic or numeric term) 
	 * Note : the program doesn't depend on any spaces 
	 * @param expression 
	 * infix expression 
	 * @return postfix expression 
	 */
	@Override
	public String infixToPostfix(String expression) {
		StringBuffer str = new StringBuffer(expression);
		str = inhanceInput(str);
		String newS = "";
		Stack s = new Stack();
		for (int i=0;i<str.length();i++) {
			if (Character.isDigit(str.charAt(i))||checkSymbols(str.charAt(i))||str.charAt(i)=='.'||str.charAt(i)==',') {
				newS+=str.charAt(i);
			}
			else if (str.charAt(i)=='(') {
				int temp = i+1;
				i = checkParenthesis(i,str);
				newS += infixToPostfix(str.substring(temp, --i));
			}
			else if (checkOperants(str.charAt(i))) {
				newS += ' ';
				while (!s.isEmpty()&&!precedence(str.charAt(i),(char)s.peek())) {
					newS += s.pop();
					newS += ' ';
				}
				s.push(str.charAt(i));
			}
			else if (str.charAt(i)!=' ')
				throw new RuntimeException("the input have invalid parameters");
		}
		while (!s.isEmpty()) newS+=" " +s.pop();
		return newS;
	}
	
	/** 
	 * Evaluate a postfix numeric(Integer or Float (greater or smaller than 9)) expression, with a space separator 
	 * 
	 * @param expression 
	 * postfix expression 
	 * @return the expression evaluated value casted to integer 
	 */
	@Override
	public int evaluate(String expression) {
		Stack nums =new Stack();
		String num = "";
		for (int i=0;i<expression.length();i++) {
			num = "";
			if (Character.isDigit(expression.charAt(i))) {
				num += expression.charAt(i);
				while ((Character.isDigit(expression.charAt(i))||expression.charAt(i)=='.'||expression.charAt(i+1)==',')
						&&(Character.isDigit(expression.charAt(i+1))||expression.charAt(i+1)=='.'||expression.charAt(i+1)==',')) {
					i++;
					num+=expression.charAt(i);
				}
				nums.push(num);
			}
			else if (checkOperants(expression.charAt(i))) {
				float Op2 = Float.parseFloat(nums.pop().toString());//(float) (nums.pop()-'0');
				float Op1 = Float.parseFloat(nums.pop().toString());//(float) nums.pop();
				nums.push(result (Op1,Op2,expression.charAt(i)));
			}
			else if (expression.charAt(i)!=' ')
				throw new RuntimeException ("the input have invalid parameters");
		}
		if (nums.size()!=1) throw new RuntimeException ("the input Postfix is invalid to be evaluated");
		return (int)((float)nums.pop());
	}
	
	/**
	 * check if the character is a valid operant(+,-,*,/) or not
	 * @param c
	 * @return true if char c is a valid operant
	 */
	
	private boolean checkOperants (char c){
		return (c=='+'||c=='-'||c=='*'||c=='/')? true:false;
	}
	
	
	/**
	 * check the precedence of two operator
	 * @param first
	 * @param last
	 * @return true if the first operator have a higher precedence than the second operator
	 */	
	private boolean  precedence (char first, char last) {
		return (first =='*'||first == '/')&&(last == '+'||last =='-');
	}
	
	
	/**
	 * calculate the result of two numbers with a specific operation
	 * @param op1 : the first number
	 * @param op2 : the second number
	 * @param c : the required operation
	 * @return the result of the operation
	 */
	private float result(float op1,float op2,char c) {
		switch (c) {
		case '+': return op1+op2;
		case '-': return op1-op2;
		case '*': return op1*op2;
		default :if (op2==0) throw new RuntimeException("can't divide by zero");
			return op1/op2;
		}		
	}
	
	/**
	 * check if the character is a valid symbol to convert from infix to postfix
	 * @param c : the character we want to check
	 * @return : true if the character is a valid symbol
	 */
	public boolean checkSymbols(char c) {
		return (c>= 'a' && c<='z')||(c>= 'A' && c<='Z');
	}
	
	/**
	 * modify the input String to add a dummy zero and to adapt with the multiply method if the input was ab then convert it to
	 * a*b or the input was (...)(...) covert to (...)*(...) or a(...)to a*(...)
	 * @param input : the original string that we want to modify
	 * @return the String with the modified operations
	 */
	private StringBuffer inhanceInput(StringBuffer input) {
		for (int i=0;i<input.length();i++) {
			if (input.charAt(i)=='-') {
				int j=i-1;
				while (j>=0&&input.charAt(j)==' ')j--;
				if ((j>=0&&!Character.isDigit(input.charAt(j))&&!checkSymbols(input.charAt(j))&&input.charAt(j)!=')')||i==0) {
					input.insert(i,"(0");
					i+=3;
					while (input.charAt(i)==' ')i++;
					while (i<input.length()&&(Character.isDigit(input.charAt(i))||checkSymbols(input.charAt(i))))i++;
					if (i<input.length()&&input.charAt(i)=='(') {
						i = checkParenthesis(i,input);
						input.insert(i,')');
						i++;
					}
					else {
						input.insert(i,')');
						i++;
					}
				}
			}
			if (i<input.length()&&(input.charAt(i)==')'||checkSymbols(input.charAt(i))||Character.isDigit(input.charAt(i)))) {
				while ((i+1)<input.length()&&((Character.isDigit(input.charAt(i))||input.charAt(i)=='.'||input.charAt(i+1)==',')
						&&(Character.isDigit(input.charAt(i+1))||input.charAt(i+1)=='.'||input.charAt(i+1)==',')))i++;
				int j=i+1;
				while (j<input.length()&&input.charAt(j)==' ')j++;
				if (j<input.length()&&(input.charAt(j)=='('||checkSymbols(input.charAt(j)))) {
					input.insert(j,'*');
				}
			}
		}
		return input;
	}
	/**
	 * this method check if any of the parenthesis opened and didn't close  
	 * @param index :the index of the character ['(']
	 * @param input : the string we want to check
	 * @return the next index to the character [')']
	 */
	private int checkParenthesis (int index , StringBuffer input) {
		Stack parenthesis = new Stack();
		parenthesis.push('(');
		index++;
		while (index<input.length()&&!parenthesis.isEmpty()) {
			if (input.charAt(index)=='(') 
				parenthesis.push('(');
			else if (input.charAt(index)==')') 
				parenthesis.pop();
			index++;
		}
		if (!parenthesis.isEmpty()) {
			throw new RuntimeException ("the parenthesis must be closed");
		}
		return index;	
	}
}