package uet.ecat.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamInstructionsScreen extends AppCompatActivity {

    Button startbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_instructions_screen);

        registerControll();
        events();
    }

    private void registerControll(){
        startbutton = findViewById(R.id.startbutton);
    }
    private void events(){
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayConfirmPopup();
            }
        });
    }

    private void displayConfirmPopup(){
        AlertDialog.Builder alert = new AlertDialog.Builder(ExamInstructionsScreen.this);
        alert.setTitle("Confirm");
        alert.setMessage("Do you want to Start Exam?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ExamInstructionsScreen.this, Exam.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }
}