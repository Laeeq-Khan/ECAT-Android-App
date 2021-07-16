package uet.ecat.app.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;

import javax.xml.transform.Result;

import uet.ecat.app.R;
import uet.ecat.app.StudentProfile;
import uet.ecat.app.models.Student;
import uet.ecat.app.services.StudentService;

public class QRcodeScannerFragemtn extends Fragment {

    private CodeScanner mCodeScanner;
    private  StudentService studentService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_q_rcode_scanner_fragemtn, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        startScanner(activity, scannerView);
        return root;
    }

    private void startScanner(Activity activity, CodeScannerView scannerView){
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull com.google.zxing.Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String barcodresult = result.getText();
                        verify(barcodresult);
                        Toast.makeText(getContext(), barcodresult, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private void verify(String roll){
          try{
              studentService = new StudentService();
              Student student = studentService.findByRoll(roll);
              if(student != null){
                  Intent intent =  new Intent(getContext(), StudentProfile.class);
                   intent.putExtra("student", student);
                  getContext().startActivity(intent);
              }else{
                  Toast.makeText(getContext(), "Wrong Pin", Toast.LENGTH_SHORT).show();
              }
          }catch (NullPointerException e){
              System.out.println(e);
          }
    }
}