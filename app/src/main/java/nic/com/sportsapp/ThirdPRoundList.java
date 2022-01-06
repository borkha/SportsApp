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

public class ThirdPRoundList extends AppCompatActivity
{
    TextView ThTxt;
    RecyclerView ThRecycler;
    Toolbar ThTool;
    PlayerDetailsAdapter playerDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.th_round_list);


        ThTxt = findViewById(R.id.ThTxt);
        ThRecycler = findViewById(R.id.ThRecycler);
        ThTool = findViewById(R.id.Thtool);

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
