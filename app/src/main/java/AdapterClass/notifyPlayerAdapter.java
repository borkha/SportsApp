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
import nic.com.sportsapp.R;
import nic.com.sportsapp.RegistrationLinkPlayer;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class notifyPlayerAdapter extends FirebaseRecyclerAdapter<Detail,notifyPlayerAdapter.myviewholder>
{
    private Context context;
    public notifyPlayerAdapter(@NonNull FirebaseRecyclerOptions<Detail> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull notifyPlayerAdapter.myviewholder holder, int position,  Detail detail)
    {
        holder.GameName.setText(detail.getGamename());
        holder.date.setText(detail.getDate());
        holder.location.setText(detail.getLocation());
        holder.lastDate.setText(detail.getLastdate());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), RegistrationLinkPlayer.class);
                intent.putExtra("gameName", detail.getGamename());
                intent.putExtra("date", detail.getDate());
                intent.putExtra("location", detail.getLocation());
                intent.putExtra("lastDate", detail.getLastdate());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
    }
    @Override
    public myviewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_notifications_player, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return new myviewholder(v);
    }

    public static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView GameName, date, location, lastDate;
        ImageView imIcon;
        RelativeLayout relativeLayout;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            GameName = (TextView) itemView.findViewById(R.id.notifySport);
            date = (TextView) itemView.findViewById(R.id.notifyApplydate);
            location = (TextView) itemView.findViewById(R.id.notifyLocation);
            lastDate = (TextView) itemView.findViewById(R.id.notifyLastDate);
            imIcon = itemView.findViewById(R.id.imgNext);
            relativeLayout = itemView.findViewById(R.id.layout_id);
        }
    }
}
