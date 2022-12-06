package com.example.calculatorpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultView, solutionView;
    MaterialButton buttonAC,buttonSqrt, buttonPer, buttonDiv;
    MaterialButton buttonMulti, buttonAdd, buttonSub, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView = findViewById(R.id.result_textview); //small top
        solutionView = findViewById(R.id.result_solutionview); // the actual answer

        assignId(buttonAC, R.id.buttons_all_clear);
        assignId(buttonSqrt, R.id.buttons_square_root);
        assignId(buttonPer, R.id.buttons_percent);
        assignId(buttonDiv, R.id.buttons_divide);
        assignId(buttonMulti, R.id.buttons_multiply);
        assignId(buttonEquals, R.id.buttons_equals);
        assignId(buttonAdd, R.id.buttons_addition);
        assignId(buttonSub, R.id.buttons_subtract);
        assignId(button0, R.id.buttons_0);
        assignId(button1, R.id.buttons_1);
        assignId(button2, R.id.buttons_2);
        assignId(button3, R.id.buttons_3);
        assignId(button4, R.id.buttons_4);
        assignId(button5, R.id.buttons_5);
        assignId(button6, R.id.buttons_6);
        assignId(button7, R.id.buttons_7);
        assignId(button8, R.id.buttons_8);
        assignId(button9, R.id.buttons_9);
        assignId(buttonDot, R.id.buttons_dot);

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String text = button.getText().toString();
        String dataToCalc = solutionView.getText().toString();

        if(text.equals("AC")){
            solutionView.setText(""); // small top
            resultView.setText("0");    // actual answer
            return;
        } else{
            dataToCalc+= text;
        }
        if(text.equals("=")){
            solutionView.setText(resultView.getText());
            return;
        }
        solutionView.setText(dataToCalc);

        String finalResult = getResult(dataToCalc);

        if (!finalResult.equals("Err")){
            resultView.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}