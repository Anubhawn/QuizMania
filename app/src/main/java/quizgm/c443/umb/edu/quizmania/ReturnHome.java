package quizgm.c443.umb.edu.quizmania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Typeface;

public class ReturnHome extends AppCompatActivity {
    ImageButton startGame,exit;
    TextView tv;
    @Override
    protected void onCreate(Bundle insSt) {
        super.onCreate(insSt);
        setContentView(R.layout.home_activity);
        startGame =(ImageButton)findViewById(R.id.strt);
        exit = (ImageButton) findViewById(R.id.qit);
        tv = (TextView)findViewById(R.id.tQ);
        //startGame button - it will take you to the MainQuiz
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReturnHome.this, MainQuiz.class);
                startActivity(intent);
                finish();
            }
        });
        //Exit button - This will quit the game
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //This is for fonts style
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/comicfont.ttf");

        tv.setTypeface(tf);
    }
}
