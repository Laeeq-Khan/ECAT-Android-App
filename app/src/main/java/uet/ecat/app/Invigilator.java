package uet.ecat.app;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.budiyev.android.codescanner.CodeScanner;
import uet.ecat.app.fragments.QRcodeScannerFragemtn;
import uet.ecat.app.models.Student;
import uet.ecat.app.services.StudentService;

public class Invigilator extends AppCompatActivity {

    private ImageView barcodescannerimage;
    private  View preview_view;
    private StudentService studentService;
    private CodeScanner mCodeScanner;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private Button startButton;
    private EditText pinEditButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invigilator);
        registerControls();
        preview_view.setVisibility(View.GONE);
        events();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }

    }

    private void registerControls(){
        barcodescannerimage = findViewById(R.id.barcodescannerimage);
        preview_view = findViewById(R.id.preview_view);
        startButton = findViewById(R.id.startButton);
        pinEditButton = findViewById(R.id.pinEditButton);
    }
    private void events(){
        barcodescannerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview_view.setVisibility(View.VISIBLE);
                barcodescannerimage.setVisibility(View.GONE);

                Fragment mFragment = null;
                mFragment = new QRcodeScannerFragemtn();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.preview_view, mFragment).commit();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify(pinEditButton.getText().toString());
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void verify(String roll){
        try{
            studentService = new StudentService();
            Student student = studentService.findByRoll(roll);
            if(student != null){
                Intent intent =  new Intent(getApplicationContext(), StudentProfile.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Wrong Pin", Toast.LENGTH_SHORT).show();
            }
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }

}