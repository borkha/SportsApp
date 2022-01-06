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
import AdapterClass.WinnerAdapter;
import ModelClass.Detail;

public class WinnerListActivity extends AppCompatActivity
{
    RecyclerView UpcomingPlayers;
    Toolbar toolbarUpcomingPlayers;
    WinnerAdapter winnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winners_of_events);

        UpcomingPlayers =  findViewById(R.id.WPlayersRecycler);
        UpcomingPlayers.setHasFixedSize(true);
        UpcomingPlayers.setLayoutManager(new LinearLayoutManager(this));
        UpcomingPlayers.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarUpcomingPlayers = findViewById(R.id.toolbarWPlayers);

        this.setSupportActionBar(toolbarUpcomingPlayers);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Detail> options =
                new FirebaseRecyclerOptions.Builder<Detail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details"),Detail.class)
                        .build();
        winnerAdapter = new WinnerAdapter(options,this);
        UpcomingPlayers.setAdapter(winnerAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        winnerAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        winnerAdapter.stopListening();
    }
}
