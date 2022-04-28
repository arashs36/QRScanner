package com.arash.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView2);
        Intent intent= getIntent();
        res =intent.getExtras().get("resualt").toString();
        textView.setText(res);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(res));
                startActivity(intent1);
            }
        });
    }
}