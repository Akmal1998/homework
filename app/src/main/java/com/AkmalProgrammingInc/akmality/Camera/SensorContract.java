package com.AkmalProgrammingInc.akmality.Camera;

import java.io.File;
import java.io.IOException;

public interface SensorContract {

    public interface ISensorCameraView{
        void checkPermission();
        void openCamera(File file);
        void showToast(String message);
        File createImageFile() throws IOException;
    }

    public interface ISensorCameraPresenter{
        void attachView(ISensorCameraView view);
        void detachView();
        void photoClick();
        void permissionGranted();
    }
}
