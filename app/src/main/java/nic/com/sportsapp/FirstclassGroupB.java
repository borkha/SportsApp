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

public class FirstclassGroupB extends AppCompatActivity
{
    RecyclerView trb;
    Toolbar toolbarTrb;
    TournamentAdapter tournamentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_class_grp_b);

        trb=findViewById(R.id.Trb);
        toolbarTrb=findViewById(R.id.toolbarTrb);

        trb.setHasFixedSize(true);
        trb.setLayoutManager(new LinearLayoutManager(this));
        trb.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarTrb);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Automatch> options =
                new FirebaseRecyclerOptions.Builder<Automatch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("First Round Matches").child("Group B"), Automatch.class)
                        .build();

        tournamentAdapter= new TournamentAdapter(options);
        trb.setAdapter(tournamentAdapter);

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
