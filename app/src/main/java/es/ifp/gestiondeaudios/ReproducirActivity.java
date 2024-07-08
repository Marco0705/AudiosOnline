package es.ifp.gestiondeaudios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ReproducirActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected TextView label3;
    protected ImageButton boton1;
    protected ImageButton boton2;
    protected ImageButton boton3;
    protected Button boton4;
    protected DataBaseSQL db;
    protected Bundle extras;
    protected String titulo;
    protected String url;
    protected MediaPlayer mp;
    protected int posicionMp = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproducir);

        db= new DataBaseSQL(this);

        label1 = (TextView) findViewById(R.id.label1_reproducir);
        label2 = (TextView) findViewById(R.id.label2_reproducir);
        label3 = (TextView) findViewById(R.id.label3_reproducir);
        boton1 = (ImageButton) findViewById(R.id.boton1_reproducir);
        boton2 = (ImageButton) findViewById(R.id.boton2_reproducir);
        boton3 = (ImageButton) findViewById(R.id.boton3_reproducir);
        boton4 = (Button) findViewById(R.id.boton4_reproducir);

        extras = getIntent().getExtras();
        if(extras != null){
            int posicion = extras.getInt("POSICION");
            titulo = db.obtenerTitulo(posicion);
            url = db.obtenerUrl(posicion);
            label2.setText(titulo);
            label3.setText(url);

        }
        else{
            Toast.makeText(this, R.string.Toast1_reproducir, Toast.LENGTH_SHORT).show();
        }

        boton1.setEnabled(false);
        boton3.setEnabled(false);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                posicionMp = mp.getCurrentPosition();
                mp.pause();
                boton1.setEnabled(false);
                boton2.setEnabled(true);
                boton3.setEnabled(true);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(posicionMp > 0){

                    mp.seekTo(posicionMp);
                    mp.start();
                    boton1.setEnabled(true);
                    boton2.setEnabled(false);
                    boton3.setEnabled(true);
                }
                else if(posicionMp < 0){

                    mp.prepareAsync();
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {

                            mp.start();
                            boton1.setEnabled(true);
                            boton2.setEnabled(false);
                            boton3.setEnabled(true);

                        }
                    });
                }
                else{

                    try {
                        mp = new MediaPlayer();
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mp = new MediaPlayer();
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mp.setDataSource(url);
                        mp.prepareAsync();

                        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {

                                mp.start();
                                boton1.setEnabled(true);
                                boton2.setEnabled(false);
                                boton3.setEnabled(true);
                            }
                        });
                    } catch (IOException e) {

                        Toast.makeText(ReproducirActivity.this, R.string.Toast2_reproducir, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                posicionMp = -1;
                mp.stop();
                boton1.setEnabled(false);
                boton2.setEnabled(true);
                boton3.setEnabled(true);
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent pasarPantalla = new Intent(ReproducirActivity.this, StartActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
}
