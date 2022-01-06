package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.SelectedPlayerListAdapter;
import ModelClass.Detail;

public class SelectedListPlayers extends AppCompatActivity
{
    RecyclerView UpcomingPlayers;
    Toolbar toolbarUpcomingPlayers;
    SelectedPlayerListAdapter selectedPlayerListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_list_of_players);

        UpcomingPlayers =  findViewById(R.id.RPlayersRecycler);
        UpcomingPlayers.setHasFixedSize(true);
        UpcomingPlayers.setLayoutManager(new LinearLayoutManager(this));
        UpcomingPlayers.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarUpcomingPlayers = findViewById(R.id.toolbarRPlayers);

        this.setSupportActionBar(toolbarUpcomingPlayers);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Detail> options =
                new FirebaseRecyclerOptions.Builder<Detail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details"),Detail.class)
                        .build();
        selectedPlayerListAdapter = new SelectedPlayerListAdapter(options,this);
        UpcomingPlayers.setAdapter(selectedPlayerListAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        selectedPlayerListAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        selectedPlayerListAdapter.stopListening();
    }
}