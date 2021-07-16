package uet.ecat.app.services;

import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uet.ecat.app.R;
import uet.ecat.app.StudentProfile;
import uet.ecat.app.models.Student;

public class StudentService {

    List<Student>  studentList = new ArrayList<>();;



    public Student findByRoll(String roll){
        loadDummyStudents();
        for (Student student: getStudentList()){
            if(student.getRoll().equalsIgnoreCase(roll)){
                return student;
            }
        }
         return null;
    }

    public void loadDummyStudents(){

        Student student = new Student();
        student.setName("Laeeq Uz Zaman Khan Niazi");
        student.setFatherName("Mehr Zaman");
        student.setCnic("3830225342463");
        student.setRoll("2019mscs13");
        student.setImage(R.drawable.laeeq);

        Student student2 = new Student();
        student2.setName("Muhammad Usman");
        student2.setFatherName("Mukhtar Ahmed");
        student2.setCnic("38302546548987");
        student2.setRoll("2019mscs26");
        student2.setImage(R.drawable.usman);

        Student student3 = new Student();
        student3.setName("Abdul Kareem");
        student3.setFatherName("Khuda Baksh");
        student3.setCnic("383058987987987");
        student3.setRoll("2020mscs134");
        student3.setImage(R.drawable.missing);

        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);

    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
