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
import ModelClass.Groups;
import nic.com.sportsapp.FirstclassGroupA;
import nic.com.sportsapp.FirstclassGroupB;
import nic.com.sportsapp.R;

public class FirstclassRGroupAdapter extends RecyclerView.Adapter<FirstclassRGroupAdapter.GroupViewholder>
{
    List<Groups> group_List;
    private Context context;
    public FirstclassRGroupAdapter (List<Groups> groupList, Context context)
    {
        group_List = groupList;
        this.context = context;
    }

    @NonNull
    @Override
    public FirstclassRGroupAdapter.GroupViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group_first_round,parent, false);
        FirstclassRGroupAdapter.GroupViewholder groupViewholder = new FirstclassRGroupAdapter.GroupViewholder(view);
        return groupViewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull FirstclassRGroupAdapter.GroupViewholder holder, int position) {
        holder.group.setText(group_List.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return group_List.size();
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
            if (getAdapterPosition() == 0){
                intent =  new Intent(context, FirstclassGroupA.class);
                context.startActivity(intent);
            } else {
                if (getAdapterPosition() == 1){
                    intent =  new Intent(context, FirstclassGroupB.class);
                }
                context.startActivity(intent);
            }
        }
    }
}
