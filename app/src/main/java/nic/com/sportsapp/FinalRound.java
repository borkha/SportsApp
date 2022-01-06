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

public class FinalRound extends AppCompatActivity
{
    TextView FTxt;
    RecyclerView FRecycler;
    FloatingActionButton Ffloat;
    Toolbar FTool;
    PlayerDetailsAdapter playerDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_round);

        FTxt = findViewById(R.id.fTxt);
        FRecycler = findViewById(R.id.FRecycler);
        Ffloat = findViewById(R.id.FFloat);
        FTool = findViewById(R.id.Ftool);

        Ffloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(FinalRound.this,FFloating.class));
            }
        });
        FRecycler.setLayoutManager(new LinearLayoutManager(this));
        FRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        FRecycler.setHasFixedSize(true);

        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Players selected for Final"), RPlayer.class)
                        .build();

        playerDetailsAdapter = new PlayerDetailsAdapter(options,this);
        FRecycler.setAdapter(playerDetailsAdapter);
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
