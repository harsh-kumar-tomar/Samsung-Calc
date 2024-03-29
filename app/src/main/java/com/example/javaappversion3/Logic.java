package com.example.javaappversion3;

import android.view.View;

import androidx.lifecycle.ViewModel;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.Objects;

public class Logic extends ViewModel {

     StringBuilder currentInput = new StringBuilder();
     String currentResult = "";



    public  String getResult()
    {
        if (currentInput.toString() != "") {
            try {
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initStandardObjects();
                currentResult = context.evaluateString(scriptable, currentInput.toString(), "Javascript", 1, null).toString();
            } catch (Exception e) {
                currentResult = "error";
            }
        }else{
            currentResult = "";
        }

        return currentResult ;
    }

    public void deleteSingleCharacter ()
    {
        if (currentInput.equals(""))
        {
            return;
        }
        currentInput = new StringBuilder(currentInput.substring(0, currentInput.length() - 1));

    }


    public  String getCurrentInput() {
        return currentInput.toString();
    }

    public void setCurrentInput(String input) {
        currentInput.append(input);
    }

}
