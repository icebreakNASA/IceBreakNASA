package com.icebreak.adriatikgashi.icebreak;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView nameTextView;
    private SharedPreferences sharedPreferences;
    private ImageView profileImage;
    private VideoView videoView;
    private String[] pathArray;
    private Button blizzard;
    private ImageView ice;

    private static final String TAG = "NavigationDrawer";


    private SlidingUpPanelLayout mLayout;

    private ImageView bizardButton;
    private ImageView erathButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        blizzard = (Button) findViewById(R.id.imageView5);
        ice = (ImageView)findViewById(R.id.imageView6);
        erathButton = (ImageView) findViewById(R.id.imageView3);

        pathArray = new String[4];
        pathArray[0] = "android.resource://" + getPackageName() + "/" + R.raw.sequence;
        pathArray[1] = "android.resource://" + getPackageName() + "/" + R.raw.videody;
        pathArray[2] = "android.resource://" + getPackageName() + "/" + R.raw.videotre;
        pathArray[3] = "android.resource://" + getPackageName() + "/" + R.raw.videokater;

        videoView = (VideoView) findViewById(R.id.videoViewRelative);

        String uriPath = randomPathFromVideos();
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        videoView.start();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        nameTextView = (TextView) header.findViewById(R.id.nameTextViewId);
        profileImage = (ImageView) header.findViewById(R.id.imageView);

        if (sharedPreferences.getString("nameKey", "Android") == null) {
            nameTextView.setText("Adriatik Gashi");
        }

        String name = sharedPreferences.getString("nameKey", "Android") + " " + sharedPreferences.getString("surnameKey", "Studio");
        nameTextView.setText(name);


        ImageView img = (ImageView) findViewById(R.id.imageView2);
        img.setBackgroundResource(R.drawable.animationtwo);

        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();


        blizzard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(randomBizaro());
            }
        });

        ice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(randomActivity());
            }
        });

        erathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(randomEarth());
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
           //startActivity(new Intent(getApplicationContext() , NavigationDrawerActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(getApplicationContext(),StatisticActivity.class));
        } else if (id == R.id.nav_slideshow) {
             startActivity(new Intent(getApplicationContext() , RadarActivity.class));
        } else if (id == R.id.nav_manage) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String randomPathFromVideos() {
        int idx = new Random().nextInt(pathArray.length);
        String random = (pathArray[idx]);

        return random;
    }

    public Intent randomActivity()
    {
        List<Intent> activities = new ArrayList<>();
        activities.add(new Intent(getApplicationContext() , IceLow.class));
        activities.add(new Intent(getApplicationContext() , IceMedium.class));
        activities.add(new Intent(getApplicationContext() , IceHigh.class));

        int idx = new Random().nextInt(activities.size());

        Intent random = (activities.get(idx));

        return random;
    }

    @Override
    protected void onResume() {
        super.onResume();

        videoView = (VideoView) findViewById(R.id.videoViewRelative);

        String uriPath = randomPathFromVideos();
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        videoView.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        videoView = (VideoView) findViewById(R.id.videoViewRelative);

        String uriPath = randomPathFromVideos();
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        videoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        videoView = (VideoView) findViewById(R.id.videoViewRelative);

        String uriPath = randomPathFromVideos();
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        videoView = (VideoView) findViewById(R.id.videoViewRelative);

        String uriPath = randomPathFromVideos();
        Uri uri = Uri.parse(uriPath);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        videoView.start();
    }

    public Intent randomBizaro()
    {
        List<Intent> activities = new ArrayList<>();
        activities.add(new Intent(getApplicationContext() , BlizzardLow.class));
        activities.add(new Intent(getApplicationContext() , IceHigh.class));

        int idx = new Random().nextInt(activities.size());

        Intent random = (activities.get(idx));

        return random;

    }

    public Intent randomEarth()
    {
        List<Intent> activities = new ArrayList<>();
        activities.add(new Intent(getApplicationContext() , EarthquakeMedium.class));
        activities.add(new Intent(getApplicationContext() , EarthquakeLow.class));
        activities.add(new Intent(getApplicationContext() , IceHigh.class));


        int idx = new Random().nextInt(activities.size());

        Intent random = (activities.get(idx));

        return random;

    }

}
