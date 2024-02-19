package com.nguyenthithao.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView txtSolution, txtResult;
    Button btnClear, btnClearAll, btnNgoacMo, btnNgoacDong, btnDivide, btnMultiply, btnPlus, btnMinus, btnEquals, btnDot,
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    String solution, result;
    private boolean dot_inserted, operator_inserted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "0";
                displaySolution();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "1";
                displaySolution();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "2";
                displaySolution();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "3";
                displaySolution();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "4";
                displaySolution();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "5";
                displaySolution();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "6";
                displaySolution();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "7";
                displaySolution();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "8";
                displaySolution();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "9";
                displaySolution();
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "+";
                displaySolution();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "-";
                displaySolution();
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "*";
                displaySolution();
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "/";
                displaySolution();
            }
        });

        btnNgoacMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + "(";
                displaySolution();
            }
        });

        btnNgoacDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution + ")";
                displaySolution();
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (solution.isEmpty())
                {
                    solution = "0.";
                    dot_inserted = true;
                }

                if (dot_inserted == false)
                {
                    solution = solution + ".";
                    dot_inserted = true;
                }
                displaySolution();
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
                displaySolution();
                displayResult();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solution = solution.substring(0,solution.length()-1);
                displaySolution();
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    public void displaySolution(){
        txtSolution.setText(solution);
    }

    public void displayResult(){
        txtResult.setText(result);
    }
    public void clearAll(){
        solution = "";
        result ="0";
        operator_inserted = false;
        dot_inserted = false;
    }

    public void calculate(){
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        String expression = txtSolution.getText().toString();
        try {
            Object result = engine.eval(expression).toString();
            txtResult.setText(result.toString());
        }
        catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
    }

    private void addViews() {
        txtSolution = findViewById(R.id.txtSolution);
        txtResult = findViewById(R.id.txtResult);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnClear = findViewById(R.id.btnClear);
        btnClearAll = findViewById(R.id.btnClearAll);
        btnNgoacMo = findViewById(R.id.btnNgoacMo);
        btnNgoacDong = findViewById(R.id.btnNgoacDong);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnEquals = findViewById(R.id.btnEquals);
        btnDot = findViewById(R.id.btnDot);
        solution = "";
        result = "";
        dot_inserted = false;
        operator_inserted = false;
    }

}