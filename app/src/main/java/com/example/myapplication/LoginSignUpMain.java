package com.example.myapplication;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Fragment.LoginFragment;
import com.example.myapplication.Fragment.SignUpFragment;
public class LoginSignUpMain extends AppCompatActivity {
    private Button loginbt, signupbt;
    private TextView logintw,signuptw;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up_main);
        loginbt = findViewById(R.id.loginbt);
        signupbt = findViewById(R.id.signupbt);
        logintw=findViewById(R.id.logintw);
        signuptw=findViewById(R.id.signuptw);
        fixButtonSizes();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new LoginFragment());
        fragmentTransaction.commit();
        logintw.setHeight(6);
        signuptw.setHeight(0);


        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 0) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, new LoginFragment());
                    fragmentTransaction.commit();
                    logintw.setHeight(6);
                    signuptw.setHeight(0);
                }
            }
        });

        signupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, new SignUpFragment());
                fragmentTransaction.commit();
                count++;
                signuptw.setHeight(6);
                logintw.setHeight(0);
            }
        });

    }
    public void fixButtonSizes(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width1 = size.x;
        /*int height1 = size.y;*/
        /*button1vid.setHeight(height1 / 5);*/
        loginbt.setWidth((width1 / 2)-1);
        signupbt.setWidth((width1/2)-1);
        logintw.setWidth((width1 / 2)-1);
        signuptw.setWidth((width1 / 2)-1);

    }
}