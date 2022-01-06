package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.EAdapter;
import AdapterClass.ScoreAdapter;
import ModelClass.Detail;

public class ScoreActivity extends AppCompatActivity
{
    RecyclerView UpcomingPlayers;
    Toolbar toolbarUpcomingPlayers;
    ScoreAdapter scoreAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_secured_players);

        UpcomingPlayers =  findViewById(R.id.ScorePlayersRecycler);
        UpcomingPlayers.setHasFixedSize(true);
        UpcomingPlayers.setLayoutManager(new LinearLayoutManager(this));
        UpcomingPlayers.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarUpcomingPlayers = findViewById(R.id.toolbarScorePlayers);

        this.setSupportActionBar(toolbarUpcomingPlayers);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Detail> options =
                new FirebaseRecyclerOptions.Builder<Detail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details"),Detail.class)
                        .build();
        scoreAdapter = new ScoreAdapter(options,this);
        UpcomingPlayers.setAdapter(scoreAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        scoreAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        scoreAdapter.stopListening();
    }
}
