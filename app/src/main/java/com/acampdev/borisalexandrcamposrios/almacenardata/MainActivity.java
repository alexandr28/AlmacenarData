package com.acampdev.borisalexandrcamposrios.almacenardata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    TextView archivo;
    Button guardar, leer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        archivo= (TextView) findViewById(R.id.texto);
        guardar= (Button) findViewById(R.id.save);
        leer= (Button) findViewById(R.id.read);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = "MyFile";
                String content = "Este es mi primer archivo";
                FileOutputStream outputStream = null;
                try{
                    outputStream = openFileOutput(filename,Context.MODE_PRIVATE);
                    outputStream.write(content.getBytes());
                    outputStream.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = getApplicationContext().openFileInput("MyFile");
                    InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String linea;
                    while ((linea = bufferedReader.readLine())!= null){
                        sb.append(linea).append("\n");
                    }
                    archivo.setText(sb.toString());
                }catch (FileNotFoundException f){
                } catch (UnsupportedEncodingException u){
                }catch (IOException io){}
            }
        });
    }
}
