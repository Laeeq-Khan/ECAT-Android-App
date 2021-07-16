package uet.ecat.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import uet.ecat.app.models.Question;
import uet.ecat.app.services.QuestionsService;

public class Exam extends AppCompatActivity {

    TextView examtime, questionLabel;
    RadioButton option1, option2, option3, option4;
    RadioGroup radioGroup;
    Button nextButton;
    QuestionsService questionsService;
    List<Question> questionList;
    int questionNumber = 0;
    int totalQuestions = 0;
    TextView attempts;
    private GeofencingClient geofencingClient;

    private FusedLocationProviderClient fusedLocationClient;
    private static final int MY_GPS_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        questionsService = new QuestionsService();
        questionList =  questionsService.getQuestionList();
        totalQuestions = questionList.size();
        registerControll();
        events();
        timer();
        loadQuestion();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_GPS_REQUEST_CODE);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_GPS_REQUEST_CODE);
            }
        }


    }

    private void registerControll(){
        examtime  = findViewById(R.id.examtime);
        questionLabel = findViewById(R.id.question);
        nextButton = findViewById(R.id.nextButton);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        radioGroup = findViewById(R.id.radiogroup);
        attempts = findViewById(R.id.attempts);

    }
    private void events(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuestion();
            }
        });
    }

    private void loadQuestion(){
        option1.setChecked(false);
        option2.setChecked(false);
        option3.setChecked(false);
        option4.setChecked(false);
        if(questionNumber == questionList.size()-1){
            nextButton.setText("Submit");
        }
        if(questionNumber < questionList.size()){
            Question question = questionList.get(questionNumber);
            questionLabel.setText(question.getQuestion());
            option1.setText(question.getOption1());
            option2.setText(question.getOption2());
            option3.setText(question.getOption3());
            option4.setText(question.getOption4());
            attempts.setText((questionNumber+1)+" / "+totalQuestions);
            questionNumber++;
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(Exam.this);
            alert.setTitle("Submit");
            alert.setMessage("Do you want to Submit Exam?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resultSaved();
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

    private void timer(){
        new CountDownTimer(60000 * 70   , 1000) {

            public void onTick(long millisUntilFinished) {
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                examtime.setText(hms);
            }

            public void onFinish() {
                examtime.setText("Submitted");
                resultSaved();
            }
        }.start();
    }


    private void resultSaved(){
        Intent intent = new Intent(Exam.this, ResultSaveActivity.class);
        startActivity(intent);
    }

}