package com.example.nicha.galeria;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImagemActivity extends Activity {

    private ImageView imagem;
    private Button botaoAvancar;
    private Button botaoVoltar;
    private Integer posicaoImagem = 0;
    public List<Bitmap> listaBitmap = new ArrayList<Bitmap>();
    public List<URL> listaUrl = new ArrayList<URL>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem_activity);
        imagem = (ImageView) findViewById(R.id.imagem);
        botaoAvancar = (Button) findViewById(R.id.avancar);
        botaoVoltar = (Button) findViewById(R.id.voltar);
        carregaGaleria();

    }

    public Bitmap carregaImagem(URL url) throws IOException {

        InputStream inputStream;
        Bitmap myBitmap;
        inputStream = url.openStream();
        myBitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();

        return myBitmap;
    }

    public void avancar( View view) {
    if (posicaoImagem < 4) {
        posicaoImagem += 1;
        imagem.setImageBitmap(listaBitmap.get(posicaoImagem));

    }else{
        posicaoImagem = 0;
        imagem.setImageBitmap(listaBitmap.get(posicaoImagem));
    }

    }

    public void voltar( View view) {
        if (posicaoImagem <= 4 && posicaoImagem > 0){
            posicaoImagem = posicaoImagem - 1;
            imagem.setImageBitmap(listaBitmap.get(posicaoImagem));
        }else if( posicaoImagem == 0 ){
            posicaoImagem = 4;
            imagem.setImageBitmap(listaBitmap.get(posicaoImagem));
        }
    }



    public void carregaUrl() throws MalformedURLException {
        listaUrl = new ArrayList<URL>();
        URL url = new URL("https://s-media-cache-ak0.pinimg.com/736x/f3/74/a6/f374a650237421e1726b64b68e4642fc.jpg");
        listaUrl.add(url);
        URL url2 = new URL("http://www.mensagens10.com.br/wp-content/uploads/2014/01/bom-dia.jpg");
        listaUrl.add(url2);
        URL url3 = new URL("http://geradormemes.com/media/created/fj65kn.jpg");
        listaUrl.add(url3);
        URL url4 = new URL("http://img1.recadosonline.com/105/243.jpg");
        listaUrl.add(url4);
        URL url5 = new URL("http://www.whatstube.com.br/wp-content/uploads/2015/10/bom-dia-bebe.jpg");
        listaUrl.add(url5);


    }


    public void carregaGaleria() {

        try {
            carregaUrl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        for (URL a : listaUrl) {
            DownloadImagemAsync downloadImagemAsync = new DownloadImagemAsync();
            downloadImagemAsync.execute(a);
        }

        if(listaBitmap.size() != 0 ){
        imagem.setImageBitmap(listaBitmap.get(0));
        }

    }
    public class DownloadImagemAsync extends AsyncTask<URL, Integer, Bitmap> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute(){
            progress = ProgressDialog.show(ImagemActivity.this,"Baixando imagem...", "Porfavor aguarde");
        }
        @Override
        protected Bitmap doInBackground(URL... params) {
            Bitmap bitmap = null;
            try {
                bitmap = carregaImagem(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            listaBitmap.add(bitmap);
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){


            progress.dismiss();

        }


    }
}



