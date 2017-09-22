package io.jewsaten.opencvsamples;

import android.widget.ImageView;

/**
 * Created by Administrator on 2017/9/20.
 */

public interface OnGrayImageListener {
    String WITH_JAVA = "Java";
    String WITH_NATIVE = "Native";

    void doGrayWithJava(ImageView target);

    void doGrayWithNative(ImageView target);
}
