package quizgm.c443.umb.edu.quizmania;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class TimeCounter extends AppCompatActivity {
    Button playbtn;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle insSt) {
        super.onCreate(insSt);
        setContentView(R.layout.time_activity);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/comicfont.ttf");
        timeUpText = (TextView)findViewById(R.id.timeUpText);
        playbtn = (Button)findViewById(R.id.playAgainButton);


        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(TimeCounter.this,MainQuiz.class);
                startActivity(it);
                finish();


            }
        });

        timeUpText.setTypeface(tf);
        playbtn.setTypeface(tf);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        this.finish();
    }
}
