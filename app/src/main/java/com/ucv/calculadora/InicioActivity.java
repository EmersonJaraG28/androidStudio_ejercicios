package com.ucv.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

public class InicioActivity extends AppCompatActivity {

    private Spinner SPNOptions;
    private View cuestionario;

    private EditText nombre;
    private EditText edad;
    private RadioButton hombre;
    private RadioButton mujer;
    private EditText oxigenacion;
    private CheckBox fiebre;
    private CheckBox dolor_cuerpo;

    private Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        SPNOptions = (Spinner) findViewById(R.id.mySpinner);
        cuestionario = findViewById(R.id.idCuestionario);

        cuestionario.setVisibility(cuestionario.GONE);

        //
        nombre = (EditText) findViewById(R.id.id_nombre);
        edad = (EditText) findViewById(R.id.id_edad);
        hombre = (RadioButton) findViewById(R.id.id_rb_hombre);
        mujer = (RadioButton) findViewById(R.id.id_rb_mujer);
        oxigenacion = (EditText) findViewById(R.id.id_oxigenacion);
        fiebre = (CheckBox) findViewById(R.id.id_cb_fiebre);
        dolor_cuerpo = (CheckBox) findViewById(R.id.id_cb_dolorCuerpo);

        validar = (Button) findViewById(R.id.id_btn_validar);

        SPNOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        cuestionario.setVisibility(view.GONE);
                        break;
                    case 1:
                        cuestionario.setVisibility(view.GONE);
                        Intent myIntent = new Intent(InicioActivity.this, CalculadoraActivity.class);
                        startActivity(myIntent);
                        break;
                    case 2:
                        cuestionario.setVisibility(view.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    mujer.setChecked(false);
                }
            }
        });

        mujer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Log.e("prueba","aaaaa");
                    hombre.setChecked(false);
                }
            }
        });

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myName = nombre.getText().toString();
                String myAge =  edad.getText().toString();
                boolean isMan = hombre.isChecked();
                boolean isWoman = mujer.isChecked();
                String oxigention =  oxigenacion.getText().toString();

                boolean fie = fiebre.isChecked();
                boolean dolor = dolor_cuerpo.isChecked();

                if(myName == "" || myAge == "" ||  oxigention == "" || (isMan ==false && isWoman == false)){
                    Context context = getApplicationContext();
                    CharSequence text = "Por favor complete los campos..!!!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {

                    int oxi = Integer.parseInt(oxigention);
                    String estado = "";
                    if(oxi >=95 && oxi <= 100){

                        if(fie || dolor){
                            estado = "Usted tiene sintomas leves, tome antivioticos para controlar los sintomas ";
                        }else {
                            estado = "Usted está saludable";
                        }

                    }else if (oxi >=90 && oxi <= 94){

                        if(fie || dolor){
                            estado = "Usted necesita oxigeno y tomar antivioticos. Cuidado !!!";
                        }else {
                            estado = "Usted necesita oxigeno";
                        }

                    }else if( oxi < 90){

                        if(fie || dolor){
                            estado = "cuidado..!! Posible contagio de covid, Usted Necesita ser atendido inmediatamente";
                        }else {
                            estado = "Usted Necesita ser atendido inmediatamente";
                        }

                    }

                    Intent myIntent = new Intent(InicioActivity.this, ResultadosActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("nickName",myName);
                    bundle.putString("result",estado);
                    myIntent.putExtras(bundle);

                    startActivity(myIntent);

                }
            }
        });


    }


}