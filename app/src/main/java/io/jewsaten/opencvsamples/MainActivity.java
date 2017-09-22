package io.jewsaten.opencvsamples;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity implements OnGrayImageListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11, this, mOpenCVCallBack);
    }

    private void initViews() {
        Button btnJava = (Button) findViewById(R.id.btnJava);
        Button btnNative = (Button) findViewById(R.id.btnNative);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        btnJava.setOnClickListener(createOnClickListener(imageView));
        btnNative.setOnClickListener(createOnClickListener(imageView));
    }

    @Override
    public void doGrayWithNative(ImageView target) {
        Bitmap bmp = ((BitmapDrawable) target.getDrawable()).getBitmap();
        target.setImageBitmap(ImageUtil.nativeGray(bmp));
    }

    @Override
    public void doGrayWithJava(ImageView target) {
        Bitmap bmp = ((BitmapDrawable) target.getDrawable()).getBitmap();
        target.setImageBitmap(ImageUtil.javaGray(bmp));
    }

    private View.OnClickListener createOnClickListener(final ImageView target) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String) view.getTag();
                if (TextUtils.equals(WITH_JAVA, tag)) {
                    doGrayWithJava(target);
                } else if (TextUtils.equals(WITH_NATIVE, tag)) {
                    doGrayWithNative(target);
                }
            }
        };
    }

    private BaseLoaderCallback mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    Log.i(TAG, "OpenCV Loaded.");
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };
}
