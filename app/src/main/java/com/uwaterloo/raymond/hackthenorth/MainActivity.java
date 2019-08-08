package com.uwaterloo.raymond.hackthenorth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.uwaterloo.raymond.hackthenorth.ast.Operation;
import com.uwaterloo.raymond.hackthenorth.tokenizer.AbstractTreeBuilder;
import com.uwaterloo.raymond.hackthenorth.tokenizer.TokenizerException;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AbstractTreeBuilder tree = new AbstractTreeBuilder("");
        Operation derivative = null;
        try {
            derivative = tree.getTree().getDerivative();
        } catch (TokenizerException e) {
            e.printStackTrace();
        }

        tv = (TextView) this.findViewById(R.id.equationTV);
        tv.setText(derivative.toString());

    }
}
