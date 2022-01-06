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

public class QFRound extends AppCompatActivity
{
    TextView quartTxt;
    RecyclerView QuartRecycler;
    FloatingActionButton quartfloat;
    Toolbar quartTool;
    PlayerDetailsAdapter playerDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qf_round);

        quartTxt = findViewById(R.id.qf);
        QuartRecycler = findViewById(R.id.QFrecycler);
        quartfloat = findViewById(R.id.qfFloat);
        quartTool = findViewById(R.id.qftool);

        quartfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(QFRound.this,QFfloating.class));
            }
        });
        QuartRecycler.setLayoutManager(new LinearLayoutManager(this));
        QuartRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        QuartRecycler.setHasFixedSize(true);

        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Players selected for Quarter Final"), RPlayer.class)
                        .build();

        playerDetailsAdapter = new PlayerDetailsAdapter(options,this);
        QuartRecycler.setAdapter(playerDetailsAdapter);
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
