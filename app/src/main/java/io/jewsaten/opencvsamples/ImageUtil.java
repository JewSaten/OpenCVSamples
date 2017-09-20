package io.jewsaten.opencvsamples;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/9/20.
 */

public class ImageUtil {

    public static Bitmap gray(Bitmap bmp) {
        int w = bmp.getWidth(), h = bmp.getHeight();
        int[] pix = new int[w * h];
        bmp.getPixels(pix, 0, w, 0, 0, w, h);
        int[] resultPixes = OpenCVUtil.gray(pix, w, h);
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        result.setPixels(resultPixes, 0, w, 0, 0, w, h);
        return result;
    }
}
