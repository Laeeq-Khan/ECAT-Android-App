package uet.ecat.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;


import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.mlkit.vision.common.InputImage;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    EditText pinEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerControl();
        events();

    }

    private void events(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithPin();
            }
        });
    }
    private void registerControl(){
        startButton = findViewById(R.id.startButton);
        pinEditText = findViewById(R.id.pinEditText);
    }

    private void loginWithPin(){
        String pin = pinEditText.getText().toString();
        if(pin.equalsIgnoreCase("12345")){
            Intent intent = new Intent(MainActivity.this, Invigilator.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Wrong Pin", Toast.LENGTH_LONG).show();
        }
    }


}

