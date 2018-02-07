package bhoomii.max.larcata.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import bhoomii.max.larcata.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        inti();
    }

    private void inti() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                overridePendingTransition(R.anim.signin_incoming_screen_right_to_mean_position, R.anim.signin_current_screen_move_mean_to_left);
                finish();
            }
        }, 1000);
    }
}