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
import com.orhanobut.dialogplus.ViewHolder;
import ModelClass.RPlayer;
import nic.com.sportsapp.R;

public class PlayerDetailsAdapter extends FirebaseRecyclerAdapter<RPlayer,PlayerDetailsAdapter.myViewholder> {

    private Context context;

    public PlayerDetailsAdapter(@NonNull FirebaseRecyclerOptions<RPlayer> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_players_details, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return new PlayerDetailsAdapter.myViewholder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull RPlayer model)
    {
        holder.Player.setText(model.getName());
    }

    public class myViewholder extends RecyclerView.ViewHolder {
        TextView Player;
        RelativeLayout relativeLayout;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            Player = (TextView) itemView.findViewById(R.id.Playerdetails);
            relativeLayout = itemView.findViewById(R.id.relativePlayersD);

        }
    }
}
