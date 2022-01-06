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

public class PayclassActivity extends AppCompatActivity
{
    RecyclerView Perrc;
    Toolbar toolbarPe;
    TournamentAdapter tournamentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymatch);

        Perrc=findViewById(R.id.PeListRc);
        toolbarPe=findViewById(R.id.toolbarPe);

        Perrc.setHasFixedSize(true);
        Perrc.setLayoutManager(new LinearLayoutManager(this));
        Perrc.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarPe);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Automatch> options =
                new FirebaseRecyclerOptions.Builder<Automatch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PayMatch Between Players"), Automatch.class)
                        .build();

        tournamentAdapter= new TournamentAdapter(options);
        Perrc.setAdapter(tournamentAdapter);
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
