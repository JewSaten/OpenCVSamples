package io.jewsaten.opencvsamples;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Administrator on 2017/9/20.
 */

public class ImageUtil {

    public static Bitmap nativeGray(Bitmap bmp) {
        int w = bmp.getWidth(), h = bmp.getHeight();
        int[] pix = new int[w * h];
        bmp.getPixels(pix, 0, w, 0, 0, w, h);
        int[] resultPixes = OpenCVUtil.gray(pix, w, h);
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        result.setPixels(resultPixes, 0, w, 0, 0, w, h);
        return result;
    }

    public static Bitmap javaGray(Bitmap bmp) {
        Mat rgbMat = new Mat();
        Mat grayMat = new Mat();
        Bitmap result = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.RGB_565);
        Utils.bitmapToMat(bmp, rgbMat);
        Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY);
        Utils.matToBitmap(grayMat, result);
        return result;
    }
}
