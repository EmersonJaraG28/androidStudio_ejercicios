package com.ucv.calculadora;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculadoraActivity extends AppCompatActivity {

    Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0;
    Button btnSuma,btnResta, btnMultiplicacion, btnDivision, btnIgual, btnPunto;

    TextView inputText, outputText;

    double primerNumero = 0;
    double segundoNumero = 0;
    double resultado = 0;
    String tipoOperacion = "";

    String data;

    boolean isNewOp = true;
    boolean limpiarCampoParaIngresarElSegundoNumero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //Buscamos la referencia
        btn_0 = findViewById(R.id.btn0);
        btn_1 = findViewById(R.id.btn1);
        btn_2 = findViewById(R.id.btn2);
        btn_3 = findViewById(R.id.btn3);
        btn_4 = findViewById(R.id.btn4);
        btn_5 = findViewById(R.id.btn5);
        btn_6 = findViewById(R.id.btn6);
        btn_7 = findViewById(R.id.btn7);
        btn_8 = findViewById(R.id.btn8);
        btn_9 = findViewById(R.id.btn9);

        btnSuma = findViewById(R.id.btnSuma);
        btnResta = findViewById(R.id.btnResta);
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion);
        btnDivision = findViewById(R.id.btnDivision);
        btnIgual = findViewById(R.id.btnIgual);
        btnPunto = findViewById(R.id.btnPunto);

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);

       btn_0.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               InsertarDatos("0");
           }
       });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos("9");
            }
        });

        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertarDatos(".");
            }
        });

        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setBackgroundColor(getResources().getColor(R.color.red));
                btnMultiplicacion.setBackgroundColor(getResources().getColor(R.color.orange));
                btnResta.setBackgroundColor(getResources().getColor(R.color.orange));
                btnDivision.setBackgroundColor(getResources().getColor(R.color.orange));

                InsertarTipoDeOperacion("+");
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                v.setBackgroundColor(getResources().getColor(R.color.red));
                btnSuma.setBackgroundColor(getResources().getColor(R.color.orange));
                btnMultiplicacion.setBackgroundColor(getResources().getColor(R.color.orange));
                btnDivision.setBackgroundColor(getResources().getColor(R.color.orange));

                InsertarTipoDeOperacion("-");
            }
        });

        btnMultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                v.setBackgroundColor(getResources().getColor(R.color.red));
                btnSuma.setBackgroundColor(getResources().getColor(R.color.orange));
                btnResta.setBackgroundColor(getResources().getColor(R.color.orange));
                btnDivision.setBackgroundColor(getResources().getColor(R.color.orange));

                InsertarTipoDeOperacion("x");
            }

        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setBackgroundColor(getResources().getColor(R.color.red));
                btnSuma.setBackgroundColor(getResources().getColor(R.color.orange));
                btnResta.setBackgroundColor(getResources().getColor(R.color.orange));
                btnMultiplicacion.setBackgroundColor(getResources().getColor(R.color.orange));

                InsertarTipoDeOperacion("/");
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(primerNumero != 0 ||segundoNumero != 0) {
                    calcularResultado();
                    ResetButtons();
                }else {
                    inputText.setText("0");
                    isNewOp = true;
                }
            }
        });

    }

    private  void ResetButtons(){
        btnDivision.setBackgroundColor(getResources().getColor(R.color.orange));
        btnSuma.setBackgroundColor(getResources().getColor(R.color.orange));
        btnResta.setBackgroundColor(getResources().getColor(R.color.orange));
        btnMultiplicacion.setBackgroundColor(getResources().getColor(R.color.orange));
    }

    private void InsertarDatos(String value){

        if(isNewOp){
            inputText.setText("");
            isNewOp = false;
        }
        if(resultado!= 0){
            inputText.setText("");
            resultado = 0;
        }

        if(limpiarCampoParaIngresarElSegundoNumero){
            inputText.setText("");
            limpiarCampoParaIngresarElSegundoNumero = false;
        }

        data = inputText.getText().toString();
        data += value;
        inputText.setText(data);

        ResetButtons();

    }

    private  void InsertarTipoDeOperacion(String t){

        if(primerNumero == 0){
            primerNumero = Double.parseDouble(data);
        }

        tipoOperacion = t;
        limpiarCampoParaIngresarElSegundoNumero = true;
    }

    private  void calcularResultado(){

        segundoNumero = Double.parseDouble(data);

        switch (tipoOperacion){
            case "+":
                resultado = primerNumero + segundoNumero;
                break;
            case "-":
                resultado = primerNumero - segundoNumero;
                break;
            case "/":
                resultado = primerNumero / segundoNumero;
                break;
            case "x":
                resultado = primerNumero * segundoNumero;
                break;

        }


        data = String.valueOf(resultado);
        inputText.setText(data);

        tipoOperacion = "";
        primerNumero = 0;
        segundoNumero = 0;
    }

    












    /*

    private  void Calcular(){

        datos = inputText.getText().toString();
        //Toast.makeText(MainActivity.this, ""+datos, Toast.LENGTH_SHORT).show();
        datos = datos.replaceAll("x", "*");
        //datos = datos.replaceAll("%", "/100");

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String finalResult="";
        Scriptable scriptable=rhino.initStandardObjects();
        finalResult = rhino.evaluateString(scriptable,datos,"Javsscript",1,null).toString();

        inputText.setText("0");
        outputText.setText(finalResult);

        isNewOp = true;
    }
    */

}