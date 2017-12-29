package com.androidprojects.sunilsharma.leconpsm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidprojects.sunilsharma.leconpsm.R;

public class MainActivity extends AppCompatActivity
{
    ImageView imgpsmlogo;
    TextView txtSlogan;
    TextView txtcompanyName;
    TextView txtdeveloped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgpsmlogo = (ImageView) findViewById(R.id.imgpsmlogo);
        txtSlogan = (TextView) findViewById(R.id.txtSlogan);
        txtcompanyName = (TextView) findViewById(R.id.txtcompanyName);
        txtdeveloped = (TextView) findViewById(R.id.txtdeveloped);

        Animation myanim = AnimationUtils.loadAnimation(MainActivity.this , R.anim.mytransition);
        imgpsmlogo.setAnimation(myanim);
        txtSlogan.setAnimation(myanim);
        txtcompanyName.setAnimation(myanim);
        txtdeveloped.setAnimation(myanim);

        final Intent loginIntent = new Intent(MainActivity.this , LoginActivity.class);


        Thread timer = new Thread(){
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    startActivity(loginIntent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
