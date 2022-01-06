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
import ModelClass.autoModel;

public class SFRound extends AppCompatActivity
{
    TextView TTxt;
    RecyclerView SFRecycler;
    FloatingActionButton floatSf;
    Toolbar SfTool;
    PlayerDetailsAdapter detailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sf_round);

        TTxt = findViewById(R.id.sf);
        SFRecycler = findViewById(R.id.SFrecycler);
        floatSf = findViewById(R.id.SfFloat);
        SfTool = findViewById(R.id.sftool);

        floatSf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(SFRound.this,SFfloating.class));

            }
        });
        SFRecycler.setLayoutManager(new LinearLayoutManager(this));
        SFRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        SFRecycler.setHasFixedSize(true);
        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Players selected for Semi Final"), RPlayer.class)
                        .build();

        detailsAdapter = new PlayerDetailsAdapter(options,this);
        SFRecycler.setAdapter(detailsAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        detailsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailsAdapter.stopListening();
    }
}
