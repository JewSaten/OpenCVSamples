package io.jewsaten.opencvsamples;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;

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
//        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_11, this, mOpenCVCallBack);
    }

    private void initViews() {
        Button btn = (Button) findViewById(R.id.btn);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        btn.setOnClickListener(createOnClickListener(imageView));
    }

    @Override
    public void doGrayImage(ImageView target) {
        Bitmap bmp = ((BitmapDrawable) target.getDrawable()).getBitmap();
        target.setImageBitmap(ImageUtil.gray(bmp));
    }

    private View.OnClickListener createOnClickListener(final ImageView target) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doGrayImage(target);
            }
        };
    }

    private BaseLoaderCallback mOpenCVCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    Log.i(TAG, "OpenCV Manager已安装");
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };
}
