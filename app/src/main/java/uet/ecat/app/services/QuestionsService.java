package uet.ecat.app.services;

import java.util.ArrayList;
import java.util.List;

import uet.ecat.app.models.Question;

public class QuestionsService {

    List<Question> questionList = new ArrayList<>();


    public List<Question> getQuestionList() {
        loadData();
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void loadData(){
        Question question1 = new Question();
        question1.setQuestion("What is your name");
        question1.setOption1("Laeeq");
        question1.setOption2("Ali");
        question1.setOption3("Nasir");
        question1.setOption4("Wasif");
        question1.setCorrect(1);

        Question question2 = new Question();
        question2.setQuestion("What is your City name");
        question2.setOption1("Lahore");
        question2.setOption2("Mianwali");
        question2.setOption3("Karachi");
        question2.setOption4("Lahore");
        question2.setCorrect(3);

        Question question3 = new Question();
        question3.setQuestion("What is your age");
        question3.setOption1("25");
        question3.setOption2("26");
        question3.setOption3("29");
        question3.setOption4("22");
        question3.setCorrect(2);

        questionList.add(question1);
        questionList.add(question2);
        questionList.add(question3);
        questionList.add(question1);


    }

}
