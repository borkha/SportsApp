package nic.com.sportsapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import AdapterClass.PlayerDetailsAdapter;
import ModelClass.RPlayer;

public class PMRoundList extends AppCompatActivity
{
    TextView PayTxt;
    RecyclerView PayMRecycler;
    Toolbar payTool;
    PlayerDetailsAdapter detailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm_round_list);

        PayTxt = findViewById(R.id.pm);
        PayMRecycler = findViewById(R.id.PMrecycler);
        payTool = findViewById(R.id.pmTool);

        PayMRecycler.setLayoutManager(new LinearLayoutManager(this));
        PayMRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        PayMRecycler.setHasFixedSize(true);

        FirebaseRecyclerOptions<RPlayer> options =
                new FirebaseRecyclerOptions.Builder<RPlayer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Pay Match"), RPlayer.class)
                        .build();

        detailsAdapter = new PlayerDetailsAdapter(options,this);
        PayMRecycler.setAdapter(detailsAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        detailsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailsAdapter.stopListening();
    }
}
