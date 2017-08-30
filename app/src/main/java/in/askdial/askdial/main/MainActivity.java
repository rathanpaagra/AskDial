package in.askdial.askdial.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import in.askdial.askdial.R;

public class MainActivity extends AppCompatActivity {


    Button btn_login, btn_Register, btn_login_fb,btn_login_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        btn_login = (Button) findViewById(R.id.login_btn);
        btn_Register= (Button) findViewById(R.id.reg_btn);
        btn_login_fb= (Button) findViewById(R.id.btn_facebook);
        btn_login_google= (Button) findViewById(R.id.btn_google);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

}
