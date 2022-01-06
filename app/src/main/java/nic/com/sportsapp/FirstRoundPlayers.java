package nic.com.sportsapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstRoundPlayers extends AppCompatActivity
{
    Button clickgroup;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_round);

        setSupportActionBar(findViewById(R.id.manageToolbar));
        clickgroup=findViewById(R.id.clickGrp);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Registered Players For Tournaments").child("Badminton");
        list = new ArrayList<>();
        String game = "badminton";

        clickgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickgroup.setEnabled(false);
                startActivity(new Intent(FirstRoundPlayers.this, GroupsFirstRound.class));
                splitToGroup(game);
            }
        });
    }

    private void splitToGroup(String game)
    {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                int s = 0;
                if (snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        String item = data.child("name").getValue(String.class);
                        list.add(item);
                    }
                    if ((list.size()/2) % 2 == 0 )
                        s = list.size()/2;
                    else
                    {
                        s = (list.size()/2)-1;
                    }

                    for (int i = 0; i < s; i++) {
                        myRef.child(game).child("group A").push().child("name").setValue(list.get(i));
                    }
                    for (int i = s; i < list.size(); i++)
                    {
                        myRef.child(game).child("group B").push().child("name").setValue(list.get(i));
                    }
                } else
                {
                    Toast.makeText(getApplicationContext(), "error...", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });
    }
}
