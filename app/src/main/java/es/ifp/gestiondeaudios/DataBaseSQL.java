package es.ifp.gestiondeaudios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {
    public DataBaseSQL(@Nullable Context context) {
        super(context, "audio", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE media(id integer PRIMARY KEY AUTOINCREMENT NOT NULL, titulo TEXT, url TEXT)");

    }

    public boolean crearAudio(String titulo, String url){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO media (titulo, url) VALUES ('" + titulo + "' , '" + url + "')");


        return true;
    }

    public ArrayList<String> getAllAudios(){

        ArrayList<String> audios = new ArrayList<String>();

        Cursor res = null;
        SQLiteDatabase db = this.getReadableDatabase();
        res = db.rawQuery("SELECT * FROM media", null);

        res.moveToLast();
        if(res.getCount() > 0){

            res.moveToFirst();
            while(!res.isAfterLast()){
                audios.add(res.getString(0) + ". " + res.getString(1));
                res.moveToNext();
            }

        }
        return audios;
    }

    public String obtenerTitulo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT titulo FROM media WHERE id = ?", new String[]{String.valueOf(id)});
        String titulo = "";
        if (res.moveToFirst()) {
            titulo = res.getString(0);
        }
        res.close();
        db.close();
        return titulo;
    }

    public String obtenerUrl(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT url FROM media WHERE id = ?", new String[]{String.valueOf(id)});
        String url = "";
        if (res.moveToFirst()) {
            url = res.getString(0);
        }
        res.close();
        db.close();
        return url;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
