package quizgm.c443.umb.edu.quizmania;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;


public class MainQuiz extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;
    TextView questionText, QuziText, Timert, resultText, score;
    QuizeHelper QZHelper;
    QuizQuestion currentQust;
    List<QuizQuestion> QuestionLList;
    int qid = 0;
    int timeValue = 15;
    int scorevalue = 0;
    CountDownTimer countdwn;
    Typeface tab, bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);


        questionText = (TextView) findViewById(R.id.quziQuestion);
        QuziText = (TextView) findViewById(R.id.QuizText);
        Timert = (TextView) findViewById(R.id.timeText);
        btn1 = (Button) findViewById(R.id.buttonA);
        resultText = (TextView) findViewById(R.id.resultText);
        score = (TextView) findViewById(R.id.coinText);
        btn2 = (Button) findViewById(R.id.buttonB);
        btn3 = (Button) findViewById(R.id.buttonC);
        btn4 = (Button) findViewById(R.id.buttonD);



        //stylish text and button fonts
        bs = Typeface.createFromAsset(getAssets(), "fonts/comicfont.ttf");
        tab = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");

//
        btn1.setTypeface(tab);
        btn2.setTypeface(tab);
        QuziText.setTypeface(bs);
        questionText.setTypeface(tab);
        Timert.setTypeface(tab);
        resultText.setTypeface(bs);
        btn3.setTypeface(tab);
        btn4.setTypeface(tab);

        score.setTypeface(tab);
        QZHelper = new QuizeHelper(this);
       //create db
        QZHelper.getWritableDatabase();

        if (QZHelper.getQuestions().size() == 0) {
            QZHelper.SetQuestions();
        }

        //return list of QuizQuestion
        QuestionLList = QZHelper.getQuestions();

        //shuffle question
        Collections.shuffle(QuestionLList);

        currentQust = QuestionLList.get(qid);

        countdwn = new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {

                Timert.setText(String.valueOf(timeValue) + "\"");

                // iteration 1 sec
                timeValue -= 1;

               //finished time
                if (timeValue == -1) {

                    resultText.setText(getString(R.string.timeup));
                    //disable button after time
                    disableButton();
                }
            }

            public void onFinish() {

                timeFinished();
            }
        }.start();

        updateQueAndOptions();


    }


    public void updateQueAndOptions() {


        questionText.setText(currentQust.getQuestion());
        btn3.setText(currentQust.getVal3());
        btn4.setText(currentQust.getVal4());
        btn1.setText(currentQust.getVal1());
        btn2.setText(currentQust.getVal2());

        timeValue = 15;
        countdwn.cancel();
        countdwn.start();

        score.setText(String.valueOf(scorevalue));
        scorevalue++;

    }

    // listener button1
    public void buttonA(View view) {

        if (currentQust.getVal1().equals(currentQust.getAnswer()))
        {
            btn1.setBackgroundColor(getResources().getColor(R.color.red));

            if (qid < QuestionLList.size() - 1)
            {
                // user ans is correct so
                disableButton();
                correctDialog();
            }//if exceed limit of question
            else {

                gameWon();

            }
        }
       //user ans wrong
        else {

            gameLostPlayAgain();

        }
    }


    public void buttonB(View view) {
        if (currentQust.getVal2().equals(currentQust.getAnswer()))
        {
            btn2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < QuestionLList.size() - 1)
            {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }




    public void gameWon() {
        Intent intent = new Intent(this, WinningActivity.class);
        startActivity(intent);
        finish();
    }
    public void buttonD(View view) {
        if (currentQust.getVal4().equals(currentQust.getAnswer())) {
            btn4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < QuestionLList.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }


    public void buttonC(View view) {
        if (currentQust.getVal3().equals(currentQust.getAnswer())) {
            btn3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < QuestionLList.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {

            gameLostPlayAgain();
        }
    }

    public void timeFinished() {
        Intent it = new Intent(this, TimeCounter.class);
        startActivity(it);
        finish();
        /*qid++;
        //get the que and option put in currentQuestion
        currentQust = QuestionLList.get(qid);
        //show new question
        updateQueAndOptions();
        resetColor();
        enableButton();
        scorevalue--;*/

    }
    public void gameLostPlayAgain()
    {
        Intent it = new Intent(this, RestartGame.class);
        startActivity(it);
        finish();
    }



    @Override
    public void onBackPressed() {
        Intent it = new Intent(this, ReturnHome.class);
        startActivity(it);
        finish();
    }


    @Override
    protected void onStop() {
        super.onStop();
        countdwn.cancel();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        countdwn.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        countdwn.cancel();
    }



    //This dialog is show to the user after he ans correct
    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(MainQuiz.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.right_answer);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        //Since the dialog is show to user just pause the timer in background
        onPause();


        TextView correctText = (TextView) dialogCorrect.findViewById(R.id.correctText);
        Button buttonNext = (Button) dialogCorrect.findViewById(R.id.dialogNext);

        correctText.setTypeface(bs);
        buttonNext.setTypeface(bs);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogCorrect.dismiss();
                //increment question number
                qid++;
                //get the que and option put in currentQuestion
                currentQust = QuestionLList.get(qid);
               //show new question
                updateQueAndOptions();
                resetColor();
                enableButton();
            }
        });
    }

    public void enableButton() {
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn1.setEnabled(true);
        btn2.setEnabled(true);

    }

    public void resetColor() {
        btn3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
        btn4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
        btn1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
        btn2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));

    }


    public void disableButton() {
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn1.setEnabled(false);
        btn2.setEnabled(false);

    }

}
