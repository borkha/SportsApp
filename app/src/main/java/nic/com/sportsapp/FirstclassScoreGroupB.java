package nic.com.sportsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.ScoreSecuredAdapter;
import ModelClass.Score;

public class FirstclassScoreGroupB extends AppCompatActivity
{
    RecyclerView frac;
    Toolbar toolbarfra;
    ScoreSecuredAdapter scoreSecuredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fr_groupb);

        frac=findViewById(R.id.FRbrc);
        toolbarfra=findViewById(R.id.frgrpBtoolbar);

        frac.setHasFixedSize(true);
        frac.setLayoutManager(new LinearLayoutManager(this));
        frac.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarfra);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Score> options =
                new FirebaseRecyclerOptions.Builder<Score>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Scores Secured In First Round")
                                .child("GroupB"), Score.class)
                        .build();

        scoreSecuredAdapter= new ScoreSecuredAdapter(options);
        frac.setAdapter(scoreSecuredAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        scoreSecuredAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        scoreSecuredAdapter.stopListening();
    }

}
