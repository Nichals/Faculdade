package com.example.nicha.tarefasassincronas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inciarContador(View v){
        Intent intent = new Intent(MainActivity.this, CountActivity.class);
        startActivity(intent);
    }
    public void iniciarImagem(View v){
        Intent intent = new Intent(MainActivity.this, imageActivity.class);
        startActivity(intent);
    }
}
