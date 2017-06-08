package in.askdial.askdial.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import in.askdial.askdial.R;

public class SplashActivity extends AppCompatActivity {

   // ProgressBar progressBar;
    GifImageView gifImageView;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*progressBar= (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(progressBar.VISIBLE);*/

        gifImageView= (GifImageView) findViewById(R.id.gifView);


        try{

            InputStream inputStream=getAssets().open("loading4.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();

        } catch (IOException e) {
            e.printStackTrace();
        }


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, CategoryActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
