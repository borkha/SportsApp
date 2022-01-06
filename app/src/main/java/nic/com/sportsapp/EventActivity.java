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
import ModelClass.Detail;

public class EventActivity extends AppCompatActivity
{
    RecyclerView UpcomingPlayers;
    Toolbar toolbarUpcomingPlayers;
    EAdapter eAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_players);

        UpcomingPlayers =  findViewById(R.id.EPlayersRecycler);
        UpcomingPlayers.setHasFixedSize(true);
        UpcomingPlayers.setLayoutManager(new LinearLayoutManager(this));
        UpcomingPlayers.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarUpcomingPlayers = findViewById(R.id.toolbarEPlayers);

        this.setSupportActionBar(toolbarUpcomingPlayers);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Detail> options =
                new FirebaseRecyclerOptions.Builder<Detail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details"),Detail.class)
                        .build();
        eAdapter = new EAdapter(options,this);
        UpcomingPlayers.setAdapter(eAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        eAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        eAdapter.stopListening();
    }
}
