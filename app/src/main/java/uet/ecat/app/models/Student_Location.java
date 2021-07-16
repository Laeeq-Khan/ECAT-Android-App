package uet.ecat.app.models;

public class Student_Location {

    private Student_Location student_location;
    private Student_Location(){

    }

    public Student_Location getInstance(){
        if(student_location == null){
            student_location = new Student_Location();
        }
        return student_location;
    }
}
