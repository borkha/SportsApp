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

public class SemiclassActivity extends AppCompatActivity
{
    RecyclerView Semirrc;
    Toolbar toolbarSe;
    TournamentAdapter tournamentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semi);

        Semirrc=findViewById(R.id.SemiListrc);
        toolbarSe=findViewById(R.id.toolbarSe);

        Semirrc.setHasFixedSize(true);
        Semirrc.setLayoutManager(new LinearLayoutManager(this));
        Semirrc.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarSe);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Automatch> options =
                new FirebaseRecyclerOptions.Builder<Automatch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Semi Final Round"), Automatch.class)
                        .build();

        tournamentAdapter= new TournamentAdapter(options);
        Semirrc.setAdapter(tournamentAdapter);
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
