package com.wangliang160616.androidtest.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixActivity extends AppCompatActivity {

    @BindView(R.id.matrix_tv_shadow)
    TextView matrixTvShadow;
    @BindView(R.id.matrix_tv_translate)
    TextView matrixTvTranslate;
    @BindView(R.id.matrix_tv_rotate)
    TextView matrixTvRotate;
    @BindView(R.id.matrix_tv_skew)
    TextView matrixTvSkew;
    @BindView(R.id.matrix_tv_scale)
    TextView matrixTvScale;
    @BindView(R.id.matrix_ll)
    LinearLayout matrixLl;
    @BindView(R.id.matrix_img_up)
    ImageView matrixImgUp;
    @BindView(R.id.matrix_img_dowm)
    ImageView matrixImgDowm;

    private Matrix matrix;
    private Bitmap bitmap;
    private Paint paint;
    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        ButterKnife.bind(this);
        /*原图*/
        matrixImgUp.setImageDrawable(getResources().getDrawable(R.drawable.picture));
        bitmap = BitmapFactory.decodeResource(getResources() , R.drawable.picture).copy(Bitmap.Config.ARGB_8888 , true);
        paint = new Paint();
        matrix = new Matrix();
        canvas = new Canvas(bitmap);
        //matrixImgDowm.setImageBitmap(bitmap);
    }

    @OnClick({R.id.matrix_tv_shadow, R.id.matrix_tv_translate, R.id.matrix_tv_rotate, R.id.matrix_tv_skew, R.id.matrix_tv_scale})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.matrix_tv_shadow:
                matrix.setScale(0.5f , 0.5f);
                matrix.postTranslate(0 , bitmap.getHeight());
                canvas.drawBitmap(bitmap , matrix , paint);
                matrixImgDowm.setImageBitmap(bitmap);
                break;
            case R.id.matrix_tv_translate:
                break;
            case R.id.matrix_tv_rotate:
                break;
            case R.id.matrix_tv_skew:
                break;
            case R.id.matrix_tv_scale:
                break;
        }
    }
}
