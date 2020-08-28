package com.example.goldenratiopro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.os.EnvironmentCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.icu.text.CaseMap;
import android.icu.text.UnicodeSetSpanner;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceContour;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static androidx.camera.core.ImageCapture.*;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private Preview preview;
    private ImageAnalysis imageAnalysis;
    private Button captureBtn;
    private Camera camera;
    private ImageCapture imageCapture;
    private CameraSelector cameraSelector;
    private FaceDetectorOptions realTimeOpts;
    PreviewView previewView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();

        previewView = findViewById(R.id.previewView);
        captureBtn= findViewById(R.id.captureBtn);

        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

         realTimeOpts =
                new FaceDetectorOptions.Builder()
                        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                        .build();



        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PERMISSION_GRANTED
                ) {


            ListenableFuture cameraProviderFuture = ProcessCameraProvider.getInstance(this);
            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = (ProcessCameraProvider) cameraProviderFuture.get();

                    preview = new Preview.Builder().build();

                    cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build();
                    cameraProvider.unbindAll();
                    imageCapture= new ImageCapture.Builder().build();
                    imageAnalysis =
                            new ImageAnalysis.Builder()
                                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                    .build();
                    camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis ,preview, imageCapture);
                    preview.setSurfaceProvider(previewView.createSurfaceProvider());
                } catch (InterruptedException | ExecutionException e) {

                }
            }, ContextCompat.getMainExecutor(this));

        }

        else{

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PERMISSION_GRANTED
        ){

            Log.i("CameraPerResult", "onRequestPermissionsResult: Permission Granted");
            startActivity(new Intent(MainActivity.this, MainActivity.class));

        }
        else{

            Toast.makeText(getApplicationContext(), "Please Accept the permission", Toast.LENGTH_LONG).show();


        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void takePicture(){


        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(@NonNull ImageProxy imageProxy) {
                @SuppressLint("UnsafeExperimentalUsageError") Image mediaImage = imageProxy.getImage();
                if (mediaImage != null) {
                    InputImage image =
                            InputImage.fromMediaImage(mediaImage, imageProxy.getImageInfo().getRotationDegrees());
                    FaceDetector detector = FaceDetection.getClient(realTimeOpts);
                    Task<List<Face>> result =
                            detector.process(image)
                                    .addOnSuccessListener(
                                            new OnSuccessListener<List<Face>>() {
                                                @Override
                                                public void onSuccess(List<Face> faces) {
                                                    for (Face face : faces) {
                                                        Rect bounds = face.getBoundingBox();
                                                        float rotY = face.getHeadEulerAngleY();
                                                        float rotZ = face.getHeadEulerAngleZ();
                                                        //getting face contours
                                                        List<PointF> ovalface= face.getContour(FaceContour.FACE).getPoints();
                                                        List<PointF> nose=face.getContour(FaceContour.NOSE_BRIDGE).getPoints();
                                                        List<PointF> upperliptop=face.getContour(FaceContour.UPPER_LIP_TOP).getPoints();
                                                        List<PointF> upperlipbtm=face.getContour(FaceContour.UPPER_LIP_BOTTOM).getPoints();
                                                        List<PointF> lowerliptop=face.getContour(FaceContour.LOWER_LIP_TOP).getPoints();
                                                        List<PointF> lowerlipbtm=face.getContour(FaceContour.LOWER_LIP_BOTTOM).getPoints();
                                                        CalulatefaceGR(ovalface, nose, upperliptop,upperlipbtm,lowerliptop,lowerlipbtm);
                                                    }
                                                }
                                            })
                                    .addOnFailureListener(
                                            new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Task failed with an exception
                                                    Log.e("FaceDetect fail", "onFailure: ",e );

                                                }
                                            });

            }
        }});



    }

    private void CalulatefaceGR(List<PointF> ovalface, List<PointF> nose, List<PointF> upperliptop, List<PointF> upperlipbtm
                                ,List<PointF> lowerliptop, List<PointF> lowerlipbtm) {

        if(nose!=null){

            //Get Nose points
            float nosebtmx=nose.get(1).x, nosebtmy=nose.get(1).y;

            //Get Face points of Length
            float facebtmx=ovalface.get(18).x, facebtmy=ovalface.get(18).y;
            float facetopx=ovalface.get(0).x, facetopy=ovalface.get(0).y;

            //Get Face points of Width
            float facergtx=ovalface.get(9).x, facergty=ovalface.get(9).y;
            float facelftx=ovalface.get(27).x, facelfty=ovalface.get(27).y;

            //Get Upper lip points

            float upperliptopx=upperliptop.get(5).x, upperliptopy=upperliptop.get(5).y;
            float upperlipbtmx=upperlipbtm.get(4).x, upperlipbtmy=upperlipbtm.get(4).y;

            //Get Lower lip points
            float lowerliptopx=lowerliptop.get(4).x, lowerliptopy=lowerliptop.get(4).y;
            float lowerlipbtmx=lowerlipbtm.get(4).x, lowerlipbtmy=lowerlipbtm.get(4).y;

            // Calculating Ratio between length of face and Nose to top of face
            double facelength= Math.pow(Math.pow((facebtmx-facetopx), 2)+Math.pow((facebtmy-facetopy),2),1);
            double noseface= Math.pow(Math.pow((nosebtmx-facetopx), 2)+Math.pow((nosebtmy-facetopy),2),1);
            double Lengthratio=Math.pow((facelength/noseface),0.5);

            // Calculating ratio between length and breadth of face
            double facewidth= Math.pow(Math.pow((facergtx-facelftx), 2)+Math.pow((facergty-facelfty),2),1);
            double wlratio=Math.pow((facelength/facewidth),0.5);

            //Calculating Ratio between upper lip and Lower lip
            double UpperLip= Math.pow(Math.pow((upperliptopx-upperlipbtmx), 2)+Math.pow((upperliptopy-upperlipbtmy),2),1);
            double LowerLip= Math.pow(Math.pow((lowerliptopx-lowerlipbtmx), 2)+Math.pow((lowerliptopy-lowerlipbtmy),2),1);
            double Lipratio=Math.pow((UpperLip /LowerLip),0.5);


            Intent i= new Intent(MainActivity.this, FaceRate.class);
            i.putExtra("LNratio", Lengthratio);
            i.putExtra("LWratio", wlratio);
            i.putExtra("LipRatio", Lipratio);
            startActivity(i);


            Log.i("W-L ratio", "CalulatefaceGR: "+facewidth+ " "+ facelength+" "+wlratio+" "+ Lengthratio+ " "+ Lipratio);




        }
        else {
            Toast.makeText(getApplicationContext(),"Couldn't Calculate",Toast.LENGTH_LONG).show();
        }

    }
}