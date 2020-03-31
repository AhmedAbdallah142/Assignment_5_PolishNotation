package eg.edu.alexu.csd.datastructure.stack;

public class ExpressionEvaluator implements IExpressionEvaluator {

	@Override
	public String infixToPostfix(String expression) {
		StringBuffer str = new StringBuffer(expression);
		for (int i=0;i<str.length();i++) {
			if (str.charAt(i)=='-') {
				int j=i-1;
				while (j>=0&&str.charAt(j)==' ')j--;
				if ((j>=0&&!Character.isDigit(str.charAt(j))&&!checkSymbols(str.charAt(j))&&str.charAt(j)!=')')||i==0) {
					str.insert(i,"(0");
					i+=3;
					while (str.charAt(i)==' ')i++;
					while (i<str.length()&&(Character.isDigit(str.charAt(i))||checkSymbols(str.charAt(i))))i++;
					str.insert(i,')');
					i++;
				}
			}
			if (i<str.length()&&(str.charAt(i)==')'||checkSymbols(str.charAt(i)))) {
				int j=i+1;
				while (j<str.length()&&str.charAt(j)==' ')j++;
				if (j<str.length()&&(str.charAt(j)=='('||checkSymbols(str.charAt(j)))) {
					str.insert(j,'*');
				}
			}
		}
		String newS = "";
		Stack s = new Stack();
		Stack parenthesis = new Stack();
		for (int i=0;i<str.length();i++) {
			if (Character.isDigit(str.charAt(i))||checkSymbols(str.charAt(i))|| str.charAt(i)==' ' || str.charAt(i)=='.') {
				newS+=str.charAt(i);
			}
			else if (str.charAt(i)=='(') {
				parenthesis.push('(');
				int temp=++i;
				while (i<str.length()&&!parenthesis.isEmpty()) {
					if (str.charAt(i)=='(') 
						parenthesis.push('(');
					else if (str.charAt(i)==')') 
						parenthesis.pop();
					i++;
				}
				if (parenthesis.isEmpty())
					newS += infixToPostfix(str.substring(temp, --i));
				else
					throw new RuntimeException ("the parenthesis must be closed");
			}
			else if (checkOperants(str.charAt(i))) {
				newS += ' ';
				while (!s.isEmpty()&&!precedence(str.charAt(i),(char)s.peek())) {
					newS += s.pop();
					newS += ' ';
				}
				s.push(str.charAt(i));
			}
			else
				throw new RuntimeException("the input have invalid parameters");
		}
		while (!s.isEmpty()) newS+=" " +s.pop();
		return newS;
	}

	@Override
	public int evaluate(String expression) {
		Stack nums =new Stack();
		float num;
		for (int i=0;i<expression.length();i++) {
			if (Character.isDigit(expression.charAt(i))) {
				num = expression.charAt(i)-'0';
				while (Character.isDigit(expression.charAt(i))&&Character.isDigit(expression.charAt(i+1))) {
					num *= 10;
					i++;
					num+=expression.charAt(i)-'0';
				}
				if (expression.charAt(i+1)=='.'||expression.charAt(i+1)==',') {
					i+=2;
					int temp = 10;
					while (Character.isDigit(expression.charAt(i))){
						num+=(float)(expression.charAt(i)-'0')/temp;
						i++;
						temp*=10;
					}
				}
				nums.push(num);
			}
			else if (checkOperants(expression.charAt(i))) {
				float Op2 = (float) nums.pop();
				float Op1 = (float) nums.pop();
				nums.push(result (Op1,Op2,expression.charAt(i)));
			}
			else if (expression.charAt(i)!=' ')
				throw new RuntimeException ("the input have invalid parameters");
		}
		if (nums.size()!=1) throw new RuntimeException ("the input Postfix is invalid to be evaluated");
		return (int)((float)nums.pop());
	}
	
	private boolean checkOperants (char c){
		return (c=='+'||c=='-'||c=='*'||c=='/')? true:false;
	}
	private boolean  precedence (char first, char last) {
		return (first =='*'||first == '/')&&(last == '+'||last =='-');
	}
	
	private float result(float op1,float op2,char c) {
		switch (c) {
		case '+': return op1+op2;
		case '-': return op1-op2;
		case '*': return op1*op2;
		default :if (op2==0) throw new RuntimeException("can't divide by zero");
			return op1/op2;
		}		
	}
	public boolean checkSymbols(char c) {
		return (c>= 'a' && c<='z')||(c>= 'A' && c<='Z');
	}

}