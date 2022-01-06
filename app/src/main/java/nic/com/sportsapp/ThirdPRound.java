package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.PlayerDetailsAdapter;
import ModelClass.RPlayer;

public class ThirdPRound extends AppCompatActivity
{
    TextView ThTxt;
    RecyclerView ThRecycler;
    FloatingActionButton Thfloat;
    Toolbar ThTool;
    PlayerDetailsAdapter playerDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_position_round);

        ThTxt = findViewById(R.id.ThTxt);
        ThRecycler = findViewById(R.id.ThRecycler);
        Thfloat = findViewById(R.id.ThFloat);
        ThTool = findViewById(R.id.Thtool);

        Thfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ThirdPRound.this,ThFloating.class));
            }
        });
        ThRecycler.setLayoutManager(new LinearLayoutManager(this));
        ThRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ThRecycler.setHasFixedSize(true);

        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Players Playing For Third Position"), RPlayer.class)
                        .build();

        playerDetailsAdapter = new PlayerDetailsAdapter(options,this);
        ThRecycler.setAdapter(playerDetailsAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        playerDetailsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerDetailsAdapter.stopListening();
    }
}
