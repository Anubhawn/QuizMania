package quizgm.c443.umb.edu.quizmania;

import android.content.Intent;
import android.app.Activity;
import android.view.View;
import android.os.Bundle;


public class WinningActivity extends Activity {

    @Override
    protected void onCreate(Bundle insSt) {
        super.onCreate(insSt);
        setContentView(R.layout.win_activity);
    }
    @Override
    public void onBackPressed()
    {
        this.finish();
    }
    public void PlayAgain(View view)
    {
        Intent intent = new Intent(WinningActivity.this, MainQuiz.class);
        startActivity(intent);
        finish();
    }

}
