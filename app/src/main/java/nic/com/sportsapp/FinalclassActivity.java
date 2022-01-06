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

public class FinalclassActivity extends AppCompatActivity
{
    RecyclerView FErrc;
    Toolbar toolbarFE;
    TournamentAdapter tournamentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_e);

        FErrc=findViewById(R.id.FEListRc);
        toolbarFE=findViewById(R.id.toolbarFE);

        FErrc.setHasFixedSize(true);
        FErrc.setLayoutManager(new LinearLayoutManager(this));
        FErrc.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarFE);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Automatch> options =
                new FirebaseRecyclerOptions.Builder<Automatch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(" Final Round "), Automatch.class)
                        .build();

        tournamentAdapter= new TournamentAdapter(options);
        FErrc.setAdapter(tournamentAdapter);
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
