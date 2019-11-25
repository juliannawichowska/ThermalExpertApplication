package com.example.teapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;

public class ThermalExpert {
    Bitmap bitmap;

    private double[] ambientConstA = new double[2];
    private double[] ambientConstB = new double[2];

    public ThermalExpert(ThermalExpertListener thermalExpertListener) {
    }


    void Initial ( Context Context){ }

    void StartReceive() {}

    void StopReceive(){}

    Bitmap GetImage(){
        return bitmap;
    }

    short[] GetData(){
        return short[];
    }

    void SetColorMap(int mode){}

    void CalibrationImage(){}

    int[] GetARGBData(){
        return int[];
    }

    int[] GetARGBData_320(){
        return int[];
    }

    byte[] GetYUV420(){
        return byte[];
    }

    byte[] GetYUV420_320(){
        return byte[];
    }

    public double GetPointTemperature(int _x, int _y) {
        double ambientTemp = this.getAmbientTemp((double) this.shutterLess.m_dFpaTemp);
        double ambientCorrTemp = this.getAmbientCorrTemp(ambientTemp);
        this.m_pointTemp.SetPoint(this.convResolution(_x, _y));
        this.m_pointTemp.GetPoint();
        return this.m_pointTemp.GetPointTemp(ambientCorrTemp);

    }

    private double getAmbientTemp(double _temp) {
        double ambientTemp = 0.0D;
        if (_temp >= this.commData.m_pdMultiOs_FpaTemp[1]) {
            ambientTemp = this.ambientConstA[1] * _temp + this.ambientConstB[1];
        } else {
            ambientTemp = this.ambientConstA[0] * _temp + this.ambientConstB[0];
        }

        return ambientTemp;
    }

    private double getAmbientCorrTemp(double _ambientTemp) {
        return -0.2D * _ambientTemp + 5.0D;
    }

    private Point convResolution(int _dispResolutionX, int _dispResolutionY) {
        int[] location = new int[2];
        this.imageView.getLocationOnScreen(location);
        Point pt = new Point(this.commData.getWindowingWidth() - this.commData.getWindowingWidth() * (_dispResolutionY - location[1]) / (this.imageView.getBottom() - this.imageView.getTop()), this.commData.getWindowingHeight() * (_dispResolutionX - location[0]) / (this.imageView.getRight() - this.imageView.getLeft()));
        if (pt.x >= this.commData.getWindowingWidth() - 1) {
            pt.x = this.commData.getWindowingWidth() - 1 - 1;
        }

        if (pt.y >= this.commData.getWindowingHeight() - 0) {
            pt.y = this.commData.getWindowingHeight() - 0 - 1;
        }

        if (pt.x < 0) {
            pt.x = 0;
        }

        if (pt.y < 0) {
            pt.y = 0;
        }

        return pt;
    }

}
