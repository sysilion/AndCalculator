package tk.bizu.andcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView expression1, expression2;
	boolean calc_complete = true, operation = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		expression1 = (TextView) findViewById(R.id.expression1);
		expression2 = (TextView) findViewById(R.id.expression2);
	}

	public void calcListener(View v) {
		if (calc_complete || expression2.getText() == "0") {
			expression2.setText("");
			calc_complete = false;
		}
		try {
			char temp = expression2.getText().charAt(expression2.getText().length() - 1);
			if (temp == '+' || temp == '-' || temp == '*' || temp == '/')
				operation = true;
			else
				operation = false;
		} catch (StringIndexOutOfBoundsException e) {
			// Error
		}
		switch (v.getId()) {
		case R.id.num0:
			expression2.setText(expression2.getText() + "0");
			break;
		case R.id.num1:
			expression2.setText(expression2.getText() + "1");
			break;
		case R.id.num2:
			expression2.setText(expression2.getText() + "2");
			break;
		case R.id.num3:
			expression2.setText(expression2.getText() + "3");
			break;
		case R.id.num4:
			expression2.setText(expression2.getText() + "4");
			break;
		case R.id.num5:
			expression2.setText(expression2.getText() + "5");
			break;
		case R.id.num6:
			expression2.setText(expression2.getText() + "6");
			break;
		case R.id.num7:
			expression2.setText(expression2.getText() + "7");
			break;
		case R.id.num8:
			expression2.setText(expression2.getText() + "8");
			break;
		case R.id.num9:
			expression2.setText(expression2.getText() + "9");
			break;
		case R.id.dot:
			if (expression2.getText() == "") expression2.setText("0");
			expression2.setText(expression2.getText() + ".");
			break;
		case R.id.leftBrace:
			expression2.setText(expression2.getText() + "(");
			break;
		case R.id.rightBrace:
			expression2.setText(expression2.getText() + ")");
			break;
		case R.id.plus:
			if (!operation)
				expression2.setText(expression2.getText() + "+");
			break;
		case R.id.minus:
			if (!operation)
				expression2.setText(expression2.getText() + "-");
			break;
		case R.id.divide:
			if (!operation)
				expression2.setText(expression2.getText() + "/");
			break;
		case R.id.multi:
			if (!operation)
				expression2.setText(expression2.getText() + "*");
			break;
		case R.id.clear:
			expression1.setText("");
			expression2.setText("0");
			break;
		case R.id.enter:
			expression1.setText(expression2.getText() + "=");
			try {
				Operation op = new Operation();
				expression2.setText(op.Calc("" + expression2.getText()));
			} catch (Exception ex) {
				expression2.setText("Incorrect format of expression: " + ex.getMessage());
			}
			calc_complete = true;
			// Toast.makeText(getApplicationContext(), calc_complete+"",
			// Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
