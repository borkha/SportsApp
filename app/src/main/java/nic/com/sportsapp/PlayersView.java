package nic.com.sportsapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.PlayersViewAdapter;
import ModelClass.Detail;

public class PlayersView extends AppCompatActivity
{
    RecyclerView RPlayers;
    Toolbar toolbarRPlayers;
    PlayersViewAdapter playersViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_view);

        RPlayers =  findViewById(R.id.RPlayersRecycler);
        RPlayers.setHasFixedSize(true);
        RPlayers.setLayoutManager(new LinearLayoutManager(this));
        RPlayers.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        toolbarRPlayers = findViewById(R.id.toolbarRPlayers);

        this.setSupportActionBar(toolbarRPlayers);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Detail> options =
                new FirebaseRecyclerOptions.Builder<Detail>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details"),Detail.class)
                        .build();
        playersViewAdapter = new PlayersViewAdapter(options,this);
        RPlayers.setAdapter(playersViewAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        playersViewAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        playersViewAdapter.stopListening();
    }
}
