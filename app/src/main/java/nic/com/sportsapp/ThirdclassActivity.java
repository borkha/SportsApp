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

public class ThirdclassActivity extends AppCompatActivity
{
    RecyclerView ThListRc;
    Toolbar toolbarThe;
    TournamentAdapter tournamentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdposition);

        ThListRc = findViewById(R.id.ThListRc);
        toolbarThe = findViewById(R.id.toolbarThe);

        ThListRc.setHasFixedSize(true);
        ThListRc.setLayoutManager(new LinearLayoutManager(this));
        ThListRc.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarThe);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Automatch> options =
                new FirebaseRecyclerOptions.Builder<Automatch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Third Position Round"), Automatch.class)
                        .build();

        tournamentAdapter = new TournamentAdapter(options);
        ThListRc.setAdapter(tournamentAdapter);
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
