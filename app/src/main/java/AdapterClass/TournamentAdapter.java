package AdapterClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import ModelClass.Automatch;
import nic.com.sportsapp.R;

public class TournamentAdapter extends FirebaseRecyclerAdapter<Automatch,TournamentAdapter.myViewHolder> {


    public TournamentAdapter(@NonNull FirebaseRecyclerOptions<Automatch> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TournamentAdapter.myViewHolder holder, int position, @NonNull Automatch model) {
        holder.TournamentP1.setText(model.getPlayerOne());
        holder.Tversus.setText(model.getVs());
        holder.TournamentP2.setText(model.getPlayerTwo());
        holder.TrSport.setText(model.getSport());
    }

    @NonNull
    @Override
    public TournamentAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tournament_item,parent,false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView TournamentP1,Tversus,TournamentP2,TrSport;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            TournamentP1= itemView.findViewById(R.id.TournamentP1);
            Tversus= itemView.findViewById(R.id.Tversus);
            TournamentP2=itemView.findViewById(R.id.TournamentP2);
            TrSport = itemView.findViewById(R.id.TrSport);
        }
    }
}
