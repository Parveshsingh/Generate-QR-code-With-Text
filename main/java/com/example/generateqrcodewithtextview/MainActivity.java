package com.example.generateqrcodewithtextview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
EditText editText;
ImageView imageView;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edittext);
        button=findViewById(R.id.generate);
        imageView=findViewById(R.id.image_v);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=editText.getText().toString().trim();

                MultiFormatWriter writer=new MultiFormatWriter();
                try {
                    BitMatrix matrix=writer.encode(text, BarcodeFormat.QR_CODE,350,350);

                    BarcodeEncoder encoder=new BarcodeEncoder();

                    Bitmap bitmap=encoder.createBitmap(matrix);

                    imageView.setImageBitmap(bitmap);

                    InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputMethodManager.hideSoftInputFromWindow(editText.getApplicationWindowToken(),0);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}