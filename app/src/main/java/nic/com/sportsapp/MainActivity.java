package nic.com.sportsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView cardEvents, cardScores, cardWinner, cardPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        cardEvents = findViewById(R.id.cardEvent);
        cardScores = findViewById(R.id.cardScores);
        cardWinner = findViewById(R.id.cardWinners);
        cardPlayers = findViewById(R.id.cardSelectPlayers);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(this);
        cardEvents.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, EventActivity.class)));

        cardScores.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ScoreActivity.class)));

        cardWinner.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, WinnerListActivity.class)));

        cardPlayers.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SelectedListPlayers.class)));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_login:
                Intent intent = new Intent(MainActivity.this, PlayerLogin.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                break;

            case R.id.nav_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id- h1");
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "ShareVia"));
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_admin:
                intent = new Intent(MainActivity.this, AdminActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                break;

            case R.id.nav_contact:
                Intent contact = new Intent(MainActivity.this, ContactActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(contact);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}