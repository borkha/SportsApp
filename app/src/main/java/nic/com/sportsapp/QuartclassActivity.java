package nic.com.sportsapp;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import AdapterClass.TournamentAdapter;
import ModelClass.Automatch;

public class QuartclassActivity extends AppCompatActivity
{
    RecyclerView Quartrrc;
    Toolbar toolbarQe;
    TournamentAdapter tournamentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quart);

        Quartrrc=findViewById(R.id.QuartrListrc);
        toolbarQe=findViewById(R.id.toolbarQe);

        Quartrrc.setHasFixedSize(true);
        Quartrrc.setLayoutManager(new LinearLayoutManager(this));
        Quartrrc.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarQe);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Automatch> options =
                new FirebaseRecyclerOptions.Builder<Automatch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Quarter Final Round"), Automatch.class)
                        .build();

        tournamentAdapter= new TournamentAdapter(options);
        Quartrrc.setAdapter(tournamentAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tournamentAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        tournamentAdapter.stopListening();
    }
}
