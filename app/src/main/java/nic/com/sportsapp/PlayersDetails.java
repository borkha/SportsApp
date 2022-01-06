package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.PlayerDetailsAdapter;
import ModelClass.RPlayer;

public class PlayersDetails extends AppCompatActivity
{
    RecyclerView RPlayers;
    Toolbar toolbarRPlayers;
    PlayerDetailsAdapter playerDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        RPlayers =  findViewById(R.id.recycler_playerDetails);
        RPlayers.setHasFixedSize(true);
        RPlayers.setLayoutManager(new LinearLayoutManager(this));
        RPlayers.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarRPlayers = findViewById(R.id.toolPlayerDetails);

        this.setSupportActionBar(toolbarRPlayers);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Registered Players For Tournaments").child("Badminton"),RPlayer.class)
                        .build();
        playerDetailsAdapter = new PlayerDetailsAdapter(options,this);
        RPlayers.setAdapter(playerDetailsAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        playerDetailsAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        playerDetailsAdapter.stopListening();
    }
}
