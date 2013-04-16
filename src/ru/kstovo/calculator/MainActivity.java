package ru.kstovo.calculator;

import ru.kstovo.calculator.engine.Calculator;
import ru.kstovo.calculator.engine.Expression;
import ru.kstovo.calculator.engine.Parser;
import ru.kstovo.calculator.engine.Transformer;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText et;
	private EditText et_calc_history;
	/** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et = (EditText)findViewById(R.id.input_field);
        et_calc_history = (EditText)findViewById(R.id.calculation_history);
        et.setFocusable(true);        
        et.setCursorVisible(true);
        et_calc_history.setFocusable(false);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        OnTouchListener otl = new OnTouchListener() {
            public boolean onTouch (View v, MotionEvent event) {
                    return true;
            }
         };
        et.setOnTouchListener(otl);
        et_calc_history.setOnTouchListener(otl);
    }

    
    public int SC_Calculate_ButtonClick(View v){
    	
    	Editable e = et.getText();
    	String input = null;
    	String[] str = null;
    	
    	if(e == null){
    		return 0;
    	}
    	
		input = e.toString();
		if( input.compareTo("") == 0 ){
			return 0;
		}
		
		input = Transformer.deleteSpaces(input);
		input = Transformer.prepareExpression(input);
		
		if(input == null){
			et.setText("Expression is wrong");
			return 0;
		}
		
		Expression exp = new Expression(input);
		Parser parser = new Parser(exp);
		
		if( parser.checkCorrectness() == false ){
			et.setText("Expression is wrong");
			return 0;
		}

		while(true){ 
			str = parser.getNextExpression();
			if(true == parser.calculated){
				str[0] = Transformer.prepareResultForOutput(str[0]);
				et.setText("");
				et.setText(str[0]);
				e = et.getEditableText();
				et.setSelection(e.length());
				et_calc_history.setText( input + '\n' + str[0] + '\n' + et_calc_history.getEditableText() );
				break;
			}
			int[] bounds = parser.getBoundsForLastExpression();
			String result = Transformer.replace(exp.getInputExpression(), bounds, Calculator.evaluate(str[0], str[1], str[2]));	
			if(result.compareTo("null") == 0){
				et.setText("Division by zero");
				break;
			}
			
			if( false == parser.passNewString(result) ){
				break;
			}
		}
		return 0;
	}
    
    public void SC_Zero_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('0');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_One_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('1');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Two_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('2');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Three_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('3');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Four_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('4');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Five_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('5');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Six_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('6');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Seven_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('7');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Eight_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('8');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Nine_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.clear();
    	}
    	e.append('9');
    	et.setText(e.toString());
    	et.setSelection(e.length());
    }
    
    public void SC_Plus_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.delete(0, 1);
    	}
    	if ( checkInput('+') == true ){
    		e.append('+');
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_Minus_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.delete(0, 1);
    	}
    	if ( checkInput('-') == true ){
    		e.append('-');
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_Multiply_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.delete(0, 1);
    	}
    	if ( checkInput('*') == true ){
    		e.append('*');
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_Divide_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.delete(0, 1);
    	}
    	if ( checkInput('/') == true ){
    		e.append('/');
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_Dot_ButtonClick(View v){
    	Editable e = et.getText();
    	if ( checkInput('.') == true ){
    		e.append('.');
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_LeftBracket_ButtonClick(View v){
    	Editable e = et.getText();
    	if( e.length() != 0 && e.charAt(0) == '=' ){
    		e.delete(0, 1);
    	}
    	if ( checkInput('(') == true ){
    		e.append('(');
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_RightBracket_ButtonClick(View v){
    	Editable e = et.getText();
    	if ( checkInput(')') == true ){
    		e.append(')');
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_PlusMinus_ButtonClick(View v){
    	return;
    }
    
    public void SC_Percent_ButtonClick(View v){
    	return;
    }
    
    public void SC_Kvadrat_ButtonClick(View v){
    	return;
    }
    
    public void SC_BackSpace_ButtonClick(View v){
    	Editable e = et.getText();
    	int len = e.length();
    	if(len != 0){
    		e.delete(len-1, len);
    		et.setText(e.toString());
    		et.setSelection(e.length());
    	}
    }
    
    public void SC_Clear_ButtonClick(View v){
    	et.setText("");
    	et.setSelection(0);
    }
    
    private boolean checkInput(char ch){
    	Editable e = et.getText();
    	int len = e.length();
    	if (len == 0)
    		return true;
    	
    	char lastChar = e.charAt(len - 1);
    	
    	switch (ch){
    	
    		case '+':
    			if ((lastChar >= '0' && lastChar <= '9') || 
    					lastChar == ')')
    				return true;
    			break;
    			
    		case '-':
    			if ((lastChar >= '0' && lastChar <= '9') || 
    					lastChar == ')' || lastChar == '(')
    				return true;
    			break;
    		
    		case '*':
    			if ((lastChar >= '0' && lastChar <= '9') || 
    					lastChar == ')')
    				return true;
    			break;
    			
    		case '/':
    			if ((lastChar >= '0' && lastChar <= '9') || 
    					lastChar == ')')
    				return true;
    			break;
    		case '(':
    			return true;
    				
    		case ')':
    			if (lastChar >= '0' && lastChar <= '9')
    				return true;
    			break;
    			
    		case '.':
    			if (lastChar >= '0' && lastChar <= '9')
    				return true;
    			break;
    	}
    	
    	return false;
    }
}