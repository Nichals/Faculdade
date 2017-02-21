package com.example.nicha.tarefasassincronas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class imageActivity extends Activity {

    private ImageView imageDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageDownload = (ImageView) findViewById(R.id.imageDownload);
    }
    public void carregarImagem(View v){
        DownloadImageAsync downloadImageAsync = new DownloadImageAsync();
        URL url = null;
        try {
            url = new URL("https://www.google.com.br/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwj42PCZ_5_SAhUBF5AKHeVQAkQQjRwIBw&url=https%3A%2F%2Fbrasileirinhos.wordpress.com%2F2015%2F10%2F30%2F31-de-outubro-halloween-e-dia-do-saci%2F&bvm=bv.147448319,d.Y2I&psig=AFQjCNErijW-mVlmTuTENNi8d86S_dWZ9Q&ust=1487724650268213");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        downloadImageAsync.execute(url);
    }
    private class DownloadImageAsync extends AsyncTask<URL, Integer, Bitmap>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(imageActivity.this,"Baixando imagem", "Por favor, aguarde");
        }


        @Override
        protected Bitmap doInBackground(URL... params) {
            Bitmap myBitmap = null;
            try {
                myBitmap = Util.loadImage(params[0]);
            }catch (IOException e) {
                Toast.makeText(imageActivity.this, "Imagem não carregada.", Toast.LENGTH_SHORT).show();
            }
            return myBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            if (bitmap != null){
                    imageDownload.setImageBitmap(bitmap);
                    }
            progressDialog.dismiss();

        }
    }
}
