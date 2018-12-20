package quizgm.c443.umb.edu.quizmania;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class RestartGame extends Activity {


    TextView WrongBtn;
    Button restartGm;

    @Override
    protected void onCreate(Bundle insSt) {
        super.onCreate(insSt);
        setContentView(R.layout.replay_activity);
        WrongBtn = (TextView)findViewById(R.id.wrongAns);
        restartGm = (Button) findViewById(R.id.playAgainButton);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/comicfont.ttf");
        restartGm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(RestartGame.this, MainQuiz.class);
                startActivity(it);
                finish();
            }
        });


        WrongBtn.setTypeface(tf);
        restartGm.setTypeface(tf);

    }
    @Override
    public void onBackPressed()
    {
        this.finish();
    }
}
