package com.example.javaappversion3;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity {
    private int currentOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

    Button but1 , but2 , but3 , but4 , but5 , but6 , but7 , but8 , but9 , but0 , but_equal , but_add , but_sub , but_mult , but_div , but_percent , but_decimal , but_C , but_delete ;
    TextView txt_answer , txt_input ;
    String datatoCalculate = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentOrientation = getResources().getConfiguration().orientation;


        intialize();

    }

    public void intialize() {

        but0 =findViewById(R.id.but0);
        but1 =findViewById(R.id.but1);
        but2 =findViewById(R.id.but2);
        but3 =findViewById(R.id.but3);
        but4 =findViewById(R.id.but4);
        but5 =findViewById(R.id.but5);
        but6 =findViewById(R.id.but6);
        but7 =findViewById(R.id.but7);
        but8 =findViewById(R.id.but8);
        but9 =findViewById(R.id.but9);
        but_equal =findViewById(R.id.but_equal);
        but_add =findViewById(R.id.but_add);
        but_div =findViewById(R.id.but_divide);
        but_mult =findViewById(R.id.but_multiply);
        but_sub =findViewById(R.id.but_subtract);
        but_percent =findViewById(R.id.but_percent);
        but_C =findViewById(R.id.but_c);
        but_decimal =findViewById(R.id.but_decimal);
        but_delete =findViewById(R.id.but_delete);

        txt_answer =findViewById(R.id.textView_answer);
        txt_input =findViewById(R.id.textView_input);


    }

    public void rotate(View view)
    {
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            currentOrientation = Configuration.ORIENTATION_LANDSCAPE;
        } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            currentOrientation = Configuration.ORIENTATION_PORTRAIT;
        }

    }


    public void buttonClicked(View view)
    {
        Button currentButton ;
        currentButton = (Button) view; //to get info about current button
        String result  ;               //to store result
        String currentButtonText = currentButton.getText().toString();

        if (currentButtonText.equals("=") && !datatoCalculate.equals(""))
        {
            result = getResult(datatoCalculate);
            txt_input.setText(result);
            txt_answer.setText("");

            return ;
        }

        if (currentButtonText.equals("C"))
        {
            clearInput();

            return ;
        }




        datatoCalculate = datatoCalculate + currentButtonText ;

        txt_input.setText(datatoCalculate);      //setting number in the input panel
        txt_answer.setText(datatoCalculate);      //setting number in the input panel

        if (!currentButton.getText().toString().equals("+") && !currentButton.getText().toString().equals("-") && !currentButton.getText().toString().equals("*") && !currentButton.getText().toString().equals("/") && !datatoCalculate.equals(""))
        {
            result = getResult(datatoCalculate);
            printtoAnswer(result);
        }



    }

    public void delete (View view)
    {
        if (datatoCalculate.equals(""))
        {
            return;
        }
        datatoCalculate = datatoCalculate.substring(0 , datatoCalculate.length() -1 );
        txt_input.setText(datatoCalculate);      //setting number in the input panel
        txt_answer.setText(datatoCalculate);      //setting number in the input panel
    }


    public void clearInput()
    {
        txt_input.setText("");
        txt_answer.setText("");
        datatoCalculate="";
    }

    public void printtoAnswer(String result)

    {
        txt_answer.setText(result);
    }

    public String getResult(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            String finalResult = context.evaluateString(scriptable , data ,"Javascript" ,1, null).toString();

            return finalResult ;

        } catch (Exception e) {
            return "error";
        }


    }


}