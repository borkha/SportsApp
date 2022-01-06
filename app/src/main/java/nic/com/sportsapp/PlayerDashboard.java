package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ModelClass.Player;

public class PlayerDashboard extends AppCompatActivity
{
    DrawerLayout drawerPlayer;
    NavigationView navigationView;
    Button backP;
    Toolbar toolbar;
    TextInputLayout FullName, email, phnNo, PSport, PGender, PAddress,PDob;
    private FirebaseUser player ;
    private String playerID;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_dashboard);

        reference = FirebaseDatabase.getInstance().getReference("Player Details");
        FullName = findViewById(R.id.name_playerDash);
        email = findViewById(R.id.email_playerDash);
        phnNo = findViewById(R.id.phone_playerDash);
        PSport = findViewById(R.id.sport_playerDash);
        PGender = findViewById(R.id.gender_playerDash);
        PAddress = findViewById(R.id.address_playerDash);
        PDob= findViewById(R.id.birth_playerDash);
        player = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Player Details");
        playerID = player.getUid();
        backP = findViewById(R.id.back_PlayerDash);
        backP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayerDashboard.this,MainActivity.class));
            }
        });
        final TextInputLayout FullName = findViewById(R.id.name_playerDash);
        final TextInputLayout email =  findViewById(R.id.email_playerDash);
        final TextInputLayout phnNo = findViewById(R.id.phone_playerDash);
        final TextInputLayout PSport = findViewById(R.id.sport_playerDash);
        final TextInputLayout PGender = findViewById(R.id.gender_playerDash);
        final TextInputLayout PAddress = findViewById(R.id.address_playerDash);
        final TextInputLayout PDob = findViewById(R.id.birth_playerDash);
        reference.child(playerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player playerProfile = snapshot.getValue(Player.class);
                if (playerProfile !=null)
                {
                    String player_name = playerProfile.name;
                    String player_email = playerProfile.email;
                    String player_sport = playerProfile.sport;
                    String player_Address = playerProfile.address;
                    String player_gender = playerProfile.gender;
                    String player_phoneNo = playerProfile.phoneNo;
                    String player_dob = playerProfile.date;

                    FullName.getEditText().setText(player_name);
                    email.getEditText().setText(player_email);
                    phnNo.getEditText().setText(player_phoneNo);
                    PSport.getEditText().setText(player_sport);
                    PGender.getEditText().setText(player_gender);
                    PAddress.getEditText().setText(player_Address);
                    PDob.getEditText().setText(player_dob);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PlayerDashboard.this,"Something wrong happened", Toast.LENGTH_LONG).show();
            }
        });
        drawerPlayer = findViewById(R.id.drawerPlayer);
        navigationView = findViewById(R.id.nav_playerDash);
        setSupportActionBar(toolbar);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_log).setVisible(false);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerPlayer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerPlayer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item)
                    {
                        switch (item.getItemId()){
                            case R.id.home_nav:
                                break;

                            case R.id.notify:
                                Intent intent = new Intent(PlayerDashboard.this,NotificationsPlayerDash.class);
                                startActivity(intent);
                                break;

                            case R.id.nav_etProfile:
                                Intent profile = new Intent(PlayerDashboard.this,EditPlayerProfile.class);
                                startActivity(profile);
                                break;
                            case R.id.nav_queries:
                                Intent query = new Intent(PlayerDashboard.this,ContactActivity.class);
                                startActivity(query);
                                break;
                            case R.id.nav_out:
                                Intent logout = new Intent(PlayerDashboard.this,MainActivity.class);
                                startActivity(logout);
                                break;
                        }
                        drawerPlayer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                }));
        navigationView.setCheckedItem(R.id.home_nav);
    }

    @Override
    public void onBackPressed()
    {
        if (drawerPlayer.isDrawerOpen(GravityCompat.START)){
            drawerPlayer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }
}
