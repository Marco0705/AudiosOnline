package es.ifp.gestiondeaudios;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected ListView lista1;
    protected DataBaseSQL db;
    protected ArrayList<String> listadoAudios = new ArrayList<String>();
    protected ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= new DataBaseSQL(this);

        label1 = (TextView) findViewById(R.id.label1_start);
        lista1 = (ListView) findViewById(R.id.lista1_start);

        listadoAudios = db.getAllAudios();
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listadoAudios);
        lista1.setAdapter(adaptador);

        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int posicion = i + 1;
                Intent pasarPantalla = new Intent(StartActivity.this, ReproducirActivity.class);
                pasarPantalla.putExtra("POSICION", posicion);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_crear_start) {

            Intent pasarPantalla = new Intent(StartActivity.this, CrearActivity.class);
            startActivity(pasarPantalla);
            finish();
            return true;

        } else if (item.getItemId() == R.id.item_salir_start) {

            System.exit(0);
            return true;

        } else {

            return super.onOptionsItemSelected(item);
        }
    }
}