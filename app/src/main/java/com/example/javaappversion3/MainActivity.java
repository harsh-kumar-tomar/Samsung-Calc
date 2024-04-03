package com.example.javaappversion3;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.javaappversion3.databinding.ActivityMainBinding;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private int currentOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;

    Button but1 , but2 , but3 , but4 , but5 , but6 , but7 , but8 , but9 , but0 , but_equal , but_add , but_sub , but_mult , but_div , but_percent , but_decimal , but_C , but_delete ;
    TextView txt_answer , txt_input ;
    String datatoCalculate = "" ;
    Logic logic;
    HashMap<Character,Integer> signHashMap = new HashMap<>();
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

//        currentOrientation = getResources().getConfiguration().orientation;

        logic = new ViewModelProvider(this)
                .get(Logic.class);

//        intialize();
        binding.textViewInput.setText(logic.getCurrentInput());
        binding.textViewAnswer.setText(logic.getResult());

        signHashMap.put('+',1);
        signHashMap.put('-',1);
        signHashMap.put('/',1);
        signHashMap.put('*',1);
        signHashMap.put('%',1);


    }

//    public void intialize() {
//
//        but0 =findViewById(R.id.but0);
//        but1 =findViewById(R.id.but1);
//        but2 =findViewById(R.id.but2);
//        but3 =findViewById(R.id.but3);
//        but4 =findViewById(R.id.but4);
//        but5 =findViewById(R.id.but5);
//        but6 =findViewById(R.id.but6);
//        but7 =findViewById(R.id.but7);
//        but8 =findViewById(R.id.but8);
//        but9 =findViewById(R.id.but9);
//        but_equal =findViewById(R.id.but_equal);
//        but_add =findViewById(R.id.but_add);
//        but_div =findViewById(R.id.but_divide);
//        but_mult =findViewById(R.id.but_multiply);
//        but_sub =findViewById(R.id.but_subtract);
//        but_percent =findViewById(R.id.but_percent);
//        but_C =findViewById(R.id.but_c);
//        but_decimal =findViewById(R.id.but_decimal);
//        but_delete =findViewById(R.id.but_delete);
//
//        txt_answer =findViewById(R.id.textView_answer);
//        txt_input =findViewById(R.id.textView_input);
//
//
//    }
//    public void rotate(View view)
//    {
//        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//            currentOrientation = Configuration.ORIENTATION_LANDSCAPE;
//        } else if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            currentOrientation = Configuration.ORIENTATION_PORTRAIT;
//        }
//
//    }


    public void buttonClicked(View view)
    {
        String currentButtonText = ((Button)view).getText().toString();

        if (currentButtonText.equals("="))
        {
            binding.textViewInput.setText(logic.getResult());
            binding.textViewAnswer.setText("");
            logic.setCurrentInput(logic.getResult());
            logic.currentInput = new StringBuilder();
            return ;
        }

        if (currentButtonText.charAt(0) == ('C'))
        {
            clearInput();
            return ;
        }

        logic.setCurrentInput(currentButtonText);
        binding.textViewInput.setText(logic.getCurrentInput());      //setting number in the input panel

        if (!signHashMap.containsKey(currentButtonText.charAt(0)))
        {
            binding.textViewAnswer.setText(logic.getResult());      //setting number in the input panel
        }

    }

    public void delete (View view)
    {
        if (!logic.getCurrentInput().equals(""))
        {
            logic.deleteSingleCharacter();
            binding.textViewInput.setText(logic.getCurrentInput());      //setting number in the input panel
            binding.textViewAnswer.setText(logic.getCurrentInput());      //setting number in the input panel
        }

    }


    public void clearInput()
    {
        logic.currentInput= new StringBuilder();
        binding.textViewInput.setText(logic.getCurrentInput());
        binding.textViewAnswer.setText(logic.getResult());
    }


    public void setting(View view) {
        ImageButton setting = (ImageButton)view;

    }
}