package AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.ViewHolder;
import ModelClass.Detail;
import nic.com.sportsapp.EventPlayerList2;
import nic.com.sportsapp.R;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class EAdapter extends FirebaseRecyclerAdapter<Detail,EAdapter.myViewholder> {

    private Context context;

    public EAdapter(@NonNull FirebaseRecyclerOptions<Detail> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull Detail model) {
        holder.Game.setText(model.getGamename());
        holder.Date.setText(model.getDate());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), EventPlayerList2.class);
                intent.putExtra("gameName", model.getGamename());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_registered_players, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return new EAdapter.myViewholder(v);
    }

    public class myViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Game,Date;
        ImageView imIcon;
        RelativeLayout relativeLayout;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            Game = (TextView) itemView.findViewById(R.id.GameNameRPlayer);
            Date = (TextView) itemView.findViewById(R.id.GameDateRPlayer);
            imIcon = itemView.findViewById(R.id.imNextP);
            relativeLayout = itemView.findViewById(R.id.realtivePlayersV);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            if (getAdapterPosition() == 0) {
                intent = new Intent(context,EventPlayerList2.class);
                context.startActivity(intent);
            }
        }
    }
}
