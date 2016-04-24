package com.icebreak.adriatikgashi.icebreak;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Random;

public class LogIn extends Activity {

    private Button logInbutton;

    private TextView nameTextView;
    private SharedPreferences sharedPreferences;
    private ImageView profileImage;
    private VideoView videoView;
    private String[] pathArray;
    private Button blizzard;
    private ImageView ice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInbutton = (Button) findViewById(R.id.loginButton);

        logInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , NavigationDrawerActivity.class));
            }
        });

        pathArray = new String[4];
        pathArray[0] = "android.resource://" + getPackageName() + "/" + R.raw.sequence;
        pathArray[1] = "android.resource://" + getPackageName() + "/" + R.raw.videody;
        pathArray[2] = "android.resource://" + getPackageName() + "/" + R.raw.videotre;
        pathArray[3] = "android.resource://" + getPackageName() + "/" + R.raw.videokater;





    }

    public String randomPathFromVideos() {
        int idx = new Random().nextInt(pathArray.length);
        String random = (pathArray[idx]);

        return random;
    }
}
