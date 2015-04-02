package tk.bizu.andcalculator;

import java.util.Stack;
import java.util.StringTokenizer;

import android.util.Log;

public class Operation {
	protected String Calc(String str){
		if(str.indexOf('(') != -1){
			int fs = str.indexOf('(');
			int ls = str.lastIndexOf(')');
			String s = Calc(str.substring(fs + 1, ls));
			str = str.substring(0, fs) + s + str.substring(ls+1, str.length());
		}
		
		double cnt = 0;
		Stack <Double> Stk_Num = new Stack <Double>();
		StringTokenizer ST_Num = new StringTokenizer(str,"+-/* ");
		StringTokenizer ST_Oper = new StringTokenizer(str,"1234567890. ");
		
		Stk_Num.push(Double.parseDouble(ST_Num.nextToken()));

		while(ST_Num.hasMoreTokens()){
			
			char oper = ST_Oper.nextToken().charAt(0);
			String num = ST_Num.nextToken();
			double a;
			
			if(oper == '*'){
				a = Stk_Num.pop();
				a *= Double.parseDouble(num);
				Stk_Num.push(a);
			}
			else if(oper == '/'){
				a = Stk_Num.pop();
				a /= Double.parseDouble(num);
				Stk_Num.push(a);
			}
			else if(oper == '+'){
				Stk_Num.push(Double.parseDouble(num));
			}
			else if(oper == '-'){
				Stk_Num.push(-1 * (Double.parseDouble(num)));
			}
		}
		
		while(!Stk_Num.isEmpty()){
			cnt += Stk_Num.pop();
		}
		return String.format("%.2f", cnt);
	}
}
