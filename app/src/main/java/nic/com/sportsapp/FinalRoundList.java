package nic.com.sportsapp;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.PlayerDetailsAdapter;
import ModelClass.RPlayer;

public class FinalRoundList extends AppCompatActivity
{
    TextView FTxt;
    RecyclerView FRecycler;
    Toolbar FTool;
    PlayerDetailsAdapter playerDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_round_list);

        FTxt = findViewById(R.id.fTxt);
        FRecycler = findViewById(R.id.FRecycler);
        FTool = findViewById(R.id.Ftool);

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
