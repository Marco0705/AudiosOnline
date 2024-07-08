package es.ifp.gestiondeaudios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CrearActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected EditText label3;
    protected TextView label4;
    protected EditText label5;
    protected Button boton1;
    protected Button boton2;
    protected DataBaseSQL db;
    protected String contenidoCaja1;
    protected String contenidoCaja2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        db = new DataBaseSQL(this);

        label1 = (TextView) findViewById(R.id.label1_crear);
        label2 = (TextView) findViewById(R.id.label2_crear);
        label3 = (EditText) findViewById(R.id.label3_crear);
        label4 = (TextView) findViewById(R.id.label4_crear);
        label5 = (EditText) findViewById(R.id.label5_crear);
        boton1 = (Button) findViewById(R.id.boton1_crear);
        boton2 = (Button) findViewById(R.id.boton2_crear);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contenidoCaja1 = label3.getText().toString();
                contenidoCaja2 = label5.getText().toString();
                
                if(contenidoCaja1.equals("") || contenidoCaja2.equals("")){

                    Toast.makeText(CrearActivity.this, R.string.Toast1_crear, Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(CrearActivity.this, R.string.Toast2_crear, Toast.LENGTH_SHORT).show();
                    db.crearAudio(contenidoCaja1, contenidoCaja2);
                    Intent pasarPantalla = new Intent(CrearActivity.this, StartActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pasarPantalla = new Intent(CrearActivity.this, StartActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

    }
}