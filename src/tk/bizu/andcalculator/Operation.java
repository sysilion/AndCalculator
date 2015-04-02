package tk.bizu.andcalculator;

import java.util.Stack;
import java.util.StringTokenizer;

import android.util.Log;

public class Operation {
	protected String Calc(String str){
		if(str.indexOf('(') != -1){
			int count = 0;
			int fs = 0, ls = 0;
			for (int i = 0; i < str.length(); i++){
				if (str.charAt(i) == '(') {
					if (count == 0){
						fs = i;
					}
					count++;
				} else if (str.charAt(i) == ')'){
					count--;
					if (count == 0){
						ls = i;
						String s = Calc(str.substring(fs + 1, ls));
						str = str.substring(0, fs) + s + str.substring(ls+1, str.length());
					}
				}
			}
		}
		
		double cnt = 0;
		Stack <Double> Stk_Num = new Stack <Double>();
		StringTokenizer ST_Num = new StringTokenizer(str,"+-/* ");
		StringTokenizer ST_Oper = new StringTokenizer(str,"1234567890. ");
		
		Stk_Num.push(Double.parseDouble(ST_Num.nextToken()));

		while(ST_Num.hasMoreTokens() || ST_Oper.hasMoreTokens()){
			
			char oper = ST_Oper.nextToken().charAt(0);
			String num = "";
			if (ST_Num.hasMoreTokens()){
				num = ST_Num.nextToken();
			} else {
				num = ""+Stk_Num.pop();
			}
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
