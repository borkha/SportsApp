package AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ModelClass.Score;
import nic.com.sportsapp.R;
import nic.com.sportsapp.RegistrationLinkPlayer;
import nic.com.sportsapp.WinnerList2;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ScoreSecuredAdapter extends FirebaseRecyclerAdapter<Score,ScoreSecuredAdapter.myViewHolder>
{

    public ScoreSecuredAdapter(@NonNull FirebaseRecyclerOptions<Score> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull ScoreSecuredAdapter.myViewHolder holder, int position, @NonNull Score model) {
        holder.grpAPOne.setText(model.getFirstplayer());
        holder.ScoreAPone.setText(model.getScoreFirstplayer());
        holder.grpAPTwo.setText(model.getSecondPlayer());
        holder.ScoreAPTwo.setText(model.getScoreSecondplayer());
    }

    @NonNull
    @Override
    public ScoreSecuredAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fr,parent,false);
        return new ScoreSecuredAdapter.myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView grpAPOne,ScoreAPone,grpAPTwo,ScoreAPTwo;
        RelativeLayout layoutScore;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            grpAPOne= itemView.findViewById(R.id.grpAPOne);
            ScoreAPone= itemView.findViewById(R.id.ScoreAPone);
            grpAPTwo=itemView.findViewById(R.id.grpAPTwo);
            ScoreAPTwo = itemView.findViewById(R.id.ScoreAPTwo);
            layoutScore = itemView.findViewById(R.id.layoutScore);
        }
    }
}
