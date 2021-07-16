package uet.ecat.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import uet.ecat.app.models.Student;
import uet.ecat.app.services.StudentService;

public class StudentProfile extends AppCompatActivity {

    TextView studentRollLabel, studentnameLabel, fathernameLabel, cnicLabel;
    ImageView studentpicture;
    Button notVerifyButton, verifyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        StudentService studentService= new StudentService();
        studentService.loadDummyStudents();
        registerControls();
        events();
        loadStudentInfo(student);
    }

    private void events(){
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentProfile.this, SupridendentPinScreen.class);
                startActivity(intent);
            }
        });
    }
    private void registerControls(){
        studentRollLabel = findViewById(R.id.studentRollLabel);
        studentnameLabel = findViewById(R.id.studentnameLabel);
        fathernameLabel = findViewById(R.id.fathernameLabel);
        cnicLabel = findViewById(R.id.cnicLabel);
        studentpicture = findViewById(R.id.studentpicture);
        notVerifyButton = findViewById(R.id.notVerifyButton);
        verifyButton = findViewById(R.id.verifyButton);
    }
    private void loadStudentInfo(Student student){
        studentRollLabel.setText(student.getRoll());
        studentnameLabel.setText(student.getName());
        fathernameLabel.setText(student.getFatherName());
        cnicLabel.setText(student.getCnic());
        studentpicture.setImageResource(student.getImage());

    }
}