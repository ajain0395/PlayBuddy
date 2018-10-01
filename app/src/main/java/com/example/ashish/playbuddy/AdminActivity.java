package com.example.ashish.playbuddy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView name ,email;
    ImageView dp;
    String name_r,email_r;
    Uri dp_r;
    int level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView_r = (NavigationView) findViewById(R.id.nav_view);
        navigationView_r.setNavigationItemSelectedListener(this);


        View navigationView = navigationView_r.getHeaderView(0);

        dp = navigationView.findViewById(R.id.user_dp);
        email = navigationView.findViewById(R.id.user_email);
        name = navigationView.findViewById(R.id.user_name);
        Intent intent = getIntent();
        name_r =intent.getStringExtra("name");
        email_r = intent.getStringExtra("email");
        level = intent.getIntExtra("level",2);
        dp_r =        (Uri)intent.getParcelableExtra("dp");
        dp.setImageURI(dp_r);

        Log.i("info",name_r + " " + email_r +" "+ level);

       name.setText(name_r);
        email.setText(email_r);



        if(level == 2) {

            NavigationView sidebar = findViewById(R.id.nav_view);
            sidebar.getMenu().clear();
            sidebar.inflateMenu(R.menu.useractivitymenu);
            Toast.makeText(this, "User Activity Entry", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Admin Activity Entry", Toast.LENGTH_SHORT).show();
        }

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
        Log.i("info 2",name_r + " " + email_r +" "+ level);

      //  getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    /**
     * Not in user
     * used for top right corner settings
     * @param item
     * @return
     */
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
    //    Log.i("info",name_r + " " + email_r +" "+ level);

        int id = item.getItemId();

        if (id == R.id.sports) {
            // Handle the camera action
        } else if (id == R.id.news) {

        } else if (id == R.id.event) {

        } else if (id == R.id.committee) {

        } else if (id == R.id.logout) {
            Intent intt = new Intent(this,Login_Main.class);
            intt.putExtra("logouttoken",1);
            startActivity(intt);
            finish();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
