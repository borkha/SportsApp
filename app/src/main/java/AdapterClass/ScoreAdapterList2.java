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

import java.util.List;
import ModelClass.autoModel;
import nic.com.sportsapp.FinalclassScore;
import nic.com.sportsapp.FirstclassScore;
import nic.com.sportsapp.PayclassScore;
import nic.com.sportsapp.QuartclassScore;
import nic.com.sportsapp.R;
import nic.com.sportsapp.SemiclassScore;
import nic.com.sportsapp.ThirdclassScore;

public class ScoreAdapterList2 extends RecyclerView.Adapter<ScoreAdapterList2.GroupViewholder>
{
    List<autoModel> Tournament_List;
    private Context context;

    public ScoreAdapterList2(List<autoModel> tournament_List, Context context) {
        Tournament_List = tournament_List;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreAdapterList2.GroupViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_first_round,parent, false);
        ScoreAdapterList2.GroupViewholder viewholder = new ScoreAdapterList2.GroupViewholder(view);
        return viewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull ScoreAdapterList2.GroupViewholder holder, int position) {
        holder.group.setText(Tournament_List.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return Tournament_List.size();
    }

    public class GroupViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView group;
        RelativeLayout relativeLayout;

        public GroupViewholder(@NonNull View itemView) {
            super(itemView);
            group = itemView.findViewById(R.id.groupName);
            relativeLayout = itemView.findViewById(R.id.groupLayout);
            context=itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            if (getAdapterPosition() == 0) {
                intent = new Intent(context, FirstclassScore.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 1) {
                intent = new Intent(context, QuartclassScore.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 2) {
                intent = new Intent(context, SemiclassScore.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 3) {
                intent = new Intent(context, PayclassScore.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 4) {
                intent = new Intent(context, ThirdclassScore.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 5) {
                intent = new Intent(context, FinalclassScore.class);
                context.startActivity(intent);
            }
        }
    }
}
