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

public class FirstclassGroupA extends AppCompatActivity
{
    RecyclerView qrc;
    Toolbar toolbarTr;
    TournamentAdapter tournamentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_class_grp_a);

        qrc=findViewById(R.id.recyclerQFA);
        toolbarTr=findViewById(R.id.toolQFA);
        qrc.setHasFixedSize(true);
        qrc.setLayoutManager(new LinearLayoutManager(this));
        qrc.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarTr);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Automatch> options =
                new FirebaseRecyclerOptions.Builder<Automatch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("First Round Matches").child("GroupA"), Automatch.class)
                        .build();

        tournamentAdapter= new TournamentAdapter(options);
        qrc.setAdapter(tournamentAdapter);

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
