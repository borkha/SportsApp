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

public class ThirdclassScore extends AppCompatActivity
{
    RecyclerView recyclerView;
    Toolbar toolbarThfs;
    ScoreSecuredAdapter scoreSecuredAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thfs_score);

        recyclerView=findViewById(R.id.recycleThFS);
        toolbarThfs=findViewById(R.id.toolThFS);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        this.setSupportActionBar(toolbarThfs);
        this.getSupportActionBar().setTitle("");

        FirebaseRecyclerOptions<Score> options =
                new FirebaseRecyclerOptions.Builder<Score>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("Scores Secured For Third Position"), Score.class)
                        .build();

        scoreSecuredAdapter= new ScoreSecuredAdapter(options);
        recyclerView.setAdapter(scoreSecuredAdapter);

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
