package eg.edu.alexu.csd.datastructure.stack;

public class ExpressionEvaluator implements IExpressionEvaluator {

	@Override
	public String infixToPostfix(String expression) {
		String newS = "";
		Stack s = new Stack();
		Stack parenthesis = new Stack();
		for (int i=0;i<expression.length();i++) {
			if (Character.isDigit(expression.charAt(i)) || expression.charAt(i)==' ' ) {
				newS+=expression.charAt(i);
			}
			else if (expression.charAt(i)=='(') {
				parenthesis.push('(');
				int temp=++i;
				while (i<expression.length()&&!parenthesis.isEmpty()) {
					if (expression.charAt(i)=='(') 
						parenthesis.push('(');
					else if (expression.charAt(i)==')') 
						parenthesis.pop();
					i++;
				}
				if (parenthesis.isEmpty())
					newS += infixToPostfix(expression.substring(temp, --i));
				else
					throw new RuntimeException ("the parenthesis must be closed");
			}
			else if (checkOperants(expression.charAt(i))) {
				newS += ' ';
				while (!s.isEmpty()&&!precedence(expression.charAt(i),(char)s.peek())) {
					newS += s.pop();
				}
				s.push(expression.charAt(i));
			}
			else
				throw new RuntimeException("the input have invalid parameters");
		}
		while (!s.isEmpty()) newS+= s.pop();
		return newS;
	}

	@Override
	public int evaluate(String expression) {
		Stack nums =new Stack();
		int num;
		for (int i=0;i<expression.length();i++) {
			if (Character.isDigit(expression.charAt(i))) {
				num = expression.charAt(i)-'0';
				while (Character.isDigit(expression.charAt(i))&&Character.isDigit(expression.charAt(i+1))) {
					num *= 10;
					i++;
					num+=expression.charAt(i)-'0';
				}
				nums.push(num);
			}
			else if (checkOperants(expression.charAt(i))) {
				int Op2 = (int) nums.pop();
				int Op1 = (int) nums.pop();
				nums.push(result (Op1,Op2,expression.charAt(i)));
			}
			else if (expression.charAt(i)!=' ')
				throw new RuntimeException ("the input have invalid parameters");
		}
		if (nums.size()!=1) throw new RuntimeException ("the input Postfix is invalid to be evaluated");
		return (int) nums.pop();
	}
	
	private boolean checkOperants (char c){
		return (c=='+'||c=='-'||c=='*'||c=='/')? true:false;
	}
	private boolean  precedence (char first, char last) {
		return (first =='*'||first == '/')&&(last == '+'||last =='-');
	}
	
	private int result(int Op1,int Op2,char c) {
		switch (c) {
		case '+': return Op1+Op2;
		case '-': return Op1-Op2;
		case '*': return Op1*Op2;
		default : return Op1/Op2;
		}		
	}

}
