package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.PlayerDetailsAdapter;
import ModelClass.RPlayer;

public class GroupA extends AppCompatActivity
{
    TextView groupA;
    RecyclerView recyclerView;
    PlayerDetailsAdapter playerDetailsAdapter;
    FloatingActionButton floatBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_a);

        groupA = findViewById(R.id.groupA);
        recyclerView = findViewById(R.id.groupAFirstRoundrecycler);
        floatBtn = findViewById(R.id.grpAFloat);

        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(GroupA.this,FloatingActivitygrpA.class));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Registered Players For Tournaments").child("Badminton")
                                .child("badminton").child("group A"), RPlayer.class)
                        .build();

        playerDetailsAdapter = new PlayerDetailsAdapter(options, this);
        recyclerView.setAdapter(playerDetailsAdapter);
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
