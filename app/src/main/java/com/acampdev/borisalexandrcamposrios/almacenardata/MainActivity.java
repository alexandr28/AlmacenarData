package com.acampdev.borisalexandrcamposrios.almacenardata;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    TextView archivo;
    Button guardar, leer;
    private  static  final  int My_PERMISSION_REQUEST_WRITE_EXTERNAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkedPermission();
        archivo= (TextView) findViewById(R.id.leer);
        guardar= (Button) findViewById(R.id.save);
        leer= (Button) findViewById(R.id.read);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile();
            }
        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }

    public  void createFile(){
        try{
            File ruta= new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            File myfile = new File(ruta,"mysdfile.txt");
            //myfile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(myfile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append("Este texto es para el archivo sd que creamos");
            fileOutputStream.close();
            outputStreamWriter.close();

        }catch (Exception e){}
    }

    public  void  readFile(){
        try{
            File ruta= new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            File myfile = new File(ruta,"mysdfile.txt");
            FileInputStream fileInputStream= new FileInputStream(myfile);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(fileInputStream));
            String aDataRow = "";
            String aBuffer = "";
            while ((aDataRow = bufferedReader.readLine())!= null){
                aBuffer+= aDataRow;
            }
            archivo.setText(aBuffer.toString());
            fileInputStream.close();
            bufferedReader.close();
        }catch (Exception e){}
    }

    public  void  checkedPermission(){
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            }else{}

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case My_PERMISSION_REQUEST_WRITE_EXTERNAL:
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    // permiso garantizado
                }
                break;
        }
    }
}
