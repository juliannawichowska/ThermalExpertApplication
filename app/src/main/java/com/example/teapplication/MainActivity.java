package com.example.teapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    ThermalExpert te;



    ImageView    mImageView;
    Button        mCalibrationButton, mPlayButton, mColorButton;

    int            mColorMode = 0;
    boolean       mIsPlay = true;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image_preview);
        mCalibrationButton = (Button) findViewById(R.id.calibration_button);
        mPlayButton = (Button) findViewById(R.id.play_button);
        mColorButton = (Button) findViewById(R.id.color_button);

        final ThermalExpert te = new ThermalExpert(thermalExpertListener);
        te.Initial(getApplicationContext());

        mCalibrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalibrationButton.setEnabled(false);
                te.CalibrationImage();
            }
        });

        mColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te.SetColorMap((++mColorMode)%4);
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                te.StartReceive();
                mIsPlay = true;
            }
        });


    }

    public boolean onTouchEvent(MotionEvent event) {
        te.GetPointTemperature((int)event.getX(), (int)event.getY());
        return super.onTouchEvent(event);
    }

    private ThermalExpertListener thermalExpertListener = new ThermalExpertListener() {
        public void onUsbConnected() {

        }

        public void onUsbDisconnected() {

        }

        public void onFlashReadFinished() {
            mImageView.setScaleType(scaleType.CENTER);
            te.StartReceive();
        }

        public void onOneFrameFinished() {
            mImageView.setBackground(new BitmapDrawable(te.GetImage()));
            //te.GetData(); }

            public void onCalibrationFinised() {
                mCalibrationButton.setEnable(true);

            }
        }
    }