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
import nic.com.sportsapp.FinalRoundList;
import nic.com.sportsapp.PMRoundList;
import nic.com.sportsapp.QFRoundList;
import nic.com.sportsapp.R;
import nic.com.sportsapp.SFRoundList;
import nic.com.sportsapp.ThirdPRoundList;

public class SelectedAdapterList2 extends RecyclerView.Adapter<SelectedAdapterList2.GroupViewholder>
{
    List<autoModel> WinGrp_List;
    private Context context;

    public SelectedAdapterList2(List<autoModel> winGrp_List, Context context) {
        WinGrp_List = winGrp_List;
        this.context = context;
    }
    @NonNull
    @Override
    public SelectedAdapterList2.GroupViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_first_round,parent, false);
        SelectedAdapterList2.GroupViewholder viewholder = new SelectedAdapterList2.GroupViewholder(view);
        return viewholder;
    }
    @Override
    public void onBindViewHolder(@NonNull SelectedAdapterList2.GroupViewholder holder, int position)
    {
        holder.group.setText(WinGrp_List.get(position).getName());
    }
    @Override
    public int getItemCount()
    {
        return WinGrp_List.size();
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
        public void onClick(View view)
        {
            Intent intent = new Intent();
            if (getAdapterPosition() == 0){
                intent =  new Intent(context, QFRoundList.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 1){
                intent =  new Intent(context, SFRoundList.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 2){
                intent =  new Intent(context, PMRoundList.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 3){
                intent =  new Intent(context, ThirdPRoundList.class);
                context.startActivity(intent);
            }
            if (getAdapterPosition() == 4){
                intent =  new Intent(context, FinalRoundList.class);
                context.startActivity(intent);
            }
        }

        }
    }
