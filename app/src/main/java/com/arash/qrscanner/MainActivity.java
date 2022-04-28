package com.arash.qrscanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE=50;
    String[] permission=new String[]{Manifest.permission.CAMERA};
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissions();
        scan();
    }
    private void permissions(){
        if (ActivityCompat.checkSelfPermission(this,permission[0])== PackageManager.PERMISSION_GRANTED){
        }
        else {
            ActivityCompat.requestPermissions(this,permission,REQ_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE) {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){}
            else {ActivityCompat.requestPermissions(this,permission,REQ_CODE);}
        }
    }
    private void scan(){
        codeScannerView = findViewById(R.id.scannerid);
        codeScanner = new CodeScanner(this,codeScannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(MainActivity.this,resultActivity.class);
                        intent.putExtra("resualt",result.getText());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }
}
