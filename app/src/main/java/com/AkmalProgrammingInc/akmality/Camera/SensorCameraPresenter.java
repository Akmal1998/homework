package com.AkmalProgrammingInc.akmality.Camera;

import java.io.File;
import java.io.IOException;

public class SensorCameraPresenter implements SensorContract.ISensorCameraPresenter {
    private SensorContract.ISensorCameraView view;


    @Override
    public void attachView(SensorContract.ISensorCameraView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void photoClick() {
        if (view != null){
            view.checkPermission();
        }
    }

    @Override
    public void permissionGranted() {
        if (view != null){
            try {
                File file = view.createImageFile();
                if (file != null){
                    view.openCamera(file);
                }else{
                    view.showToast("Error creating file");
                }

            } catch (IOException e) {
                view.showToast("Error creating file");
            }

        }
    }


}
