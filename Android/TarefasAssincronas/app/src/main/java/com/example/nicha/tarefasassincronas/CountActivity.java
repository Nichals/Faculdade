package com.example.nicha.tarefasassincronas;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CountActivity extends Activity {

    private TextView textContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        textContador = (TextView) findViewById(R.id.textContador);
    }

    public void contar(View v){
        CountAsync process = new CountAsync();
        process.execute(10);
    }

    private class CountAsync extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            int numero = params[0];
            for (int i = 0; i< numero; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Toast.makeText(CountActivity.this, "A Thread encerrou inesperadamente", Toast.LENGTH_SHORT).show();
                }
                publishProgress(i);
            }
            return null;
        }


        @Override
        protected void onProgressUpdate (Integer... values){
            textContador.setText(String.valueOf(values[0]));
        }
    }
}
