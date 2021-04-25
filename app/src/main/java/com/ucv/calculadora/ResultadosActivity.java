package com.ucv.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultadosActivity extends AppCompatActivity {

    private TextView tv_myName;
    private  TextView t_myResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        tv_myName = (TextView) findViewById(R.id.id_name);
        t_myResult = (TextView)findViewById(R.id.id_result);

        Bundle bundle = getIntent().getExtras();
        tv_myName.setText(bundle.getString("nickName"));
        t_myResult.setText(bundle.getString("result"));

    }
}