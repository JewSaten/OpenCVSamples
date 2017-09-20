package io.jewsaten.opencvsamples;

/**
 * Created by Administrator on 2017/9/19.
 */

public class OpenCVUtil {
    static {
        System.loadLibrary("OpenCV");
    }
    public static native int[] gray(int[] pixel, int w, int h);
}
