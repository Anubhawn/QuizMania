package quizgm.c443.umb.edu.quizmania;

import android.app.Activity;

public class QuizQuestion extends Activity {
    private int id;
    private String value3;
    private String value4;
    private String value1;
    private String value2;
    private String question;
    private String ans;


    public QuizQuestion(String q, String v1, String v2, String v3, String v4, String Answer) {

        question = q;
        ans = Answer;
        value3 = v3;
        value4 = v4;
        value1 = v1;
        value2 = v2;

    }

    public QuizQuestion() {
        id = 0;

        value3 = "";
        value4 = "";
        ans = "";
        question = "";
        value1 = "";
        value2 = "";
    }



    public void setQuestion(String q1)
    {

        question = q1;
    }

    public void setOptA(String v1)
    {
        value1 = v1;
    }

    public void setOptB(String v2)
    {
        value2 = v2;
    }

    public void setOptC(String v3)
    {
        value3 = v3;
    }

    public void setOptD(String v4)
    {
        value4 = v4;
    }

    public void setAnswer(String anss)
    {
        ans = anss;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getVal1()
    {
        return value1;
    }

    public String getVal2()
    {
        return value2;
    }

    public String getVal3()
    {
        return value3;
    }

    public String getVal4()
    {
        return value4;
    }

    public String getAnswer()
    {
        return ans;
    }

    public void setId(int idd)
    {
        id = idd;
    }


}
