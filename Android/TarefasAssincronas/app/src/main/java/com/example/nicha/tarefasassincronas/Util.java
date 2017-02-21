package com.example.nicha.tarefasassincronas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by nicha on 20/02/2017.
 */

public class Util {

    public static Bitmap loadImage(URL url) throws IOException {
        InputStream inputStream;
        Bitmap myBitmap;

        inputStream = url.openStream();
        myBitmap = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return myBitmap;
    }

    ;
}
