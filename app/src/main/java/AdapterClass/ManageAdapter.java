package AdapterClass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import ModelClass.Detail;
import nic.com.sportsapp.R;

public class ManageAdapter extends FirebaseRecyclerAdapter<Detail,ManageAdapter.myviewholder>
{
    private  Context context;

    public ManageAdapter(@NonNull FirebaseRecyclerOptions<Detail> options, Context context)
    {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ManageAdapter.myviewholder holder, int position, @NonNull Detail detail)
    {
        holder.Sport.setText(detail.getGamename());
        holder.Date.setText(detail.getDate());
        holder.LastDate.setText(detail.getLastdate());
        holder.Location.setText(detail.getLocation());

        holder.btnEdit.setOnClickListener(view -> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.Sport.getContext())
                    .setContentHolder(new ViewHolder(R.layout.update_popup_manage))
                    .setExpanded(Boolean.parseBoolean("true"),1500)
                    .create();

            dialogPlus.show();
            View view1 = dialogPlus.getHolderView();
            TextView sport = view1.findViewById(R.id.txtSport);
            TextView date = view1.findViewById(R.id.txtDate);
            TextView lastDate = view1.findViewById(R.id.txtLastDate);
            TextView location = view1.findViewById(R.id.txtlocation);

            Button btnSave = view1.findViewById(R.id.btnUpdate);
            sport.setText(detail.getGamename());
            date.setText(detail.getDate());
            lastDate.setText(detail.getLastdate());
            location.setText(detail.getLocation());

            dialogPlus.show();
            btnSave.setOnClickListener(view2 -> {
                Map<String,Object> map = new HashMap<>();
                map.put("date",date.getText().toString());
                map.put("gamename",sport.getText().toString());
                map.put("lastdate",lastDate.getText().toString());
                map.put("location",location.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details")
                        .child(getRef(position).getKey()).updateChildren(map)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(holder.Sport.getContext(),"Details Saved",Toast.LENGTH_SHORT).show();
                            dialogPlus.dismiss();
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( Exception e) {
                                Toast.makeText(holder.Sport.getContext(),"Error while Updating",Toast.LENGTH_SHORT).show();
                            }
                        });
            });

        });
        holder.BtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Sport.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be Undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Upcoming Event Details")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.Sport.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public ManageAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_manage_admin, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        TextView Sport, Date, Location,LastDate;
        Button btnEdit, BtDelete ;
        RelativeLayout relativeLayout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            Date = (TextView)itemView.findViewById(R.id.date);
            Location = (TextView)itemView.findViewById(R.id.loca);
            Sport = (TextView)itemView.findViewById(R.id.sport);
            LastDate = (TextView)itemView.findViewById(R.id.lastDate);
            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            BtDelete = (Button)itemView.findViewById(R.id.BtDelete);
            relativeLayout = itemView.findViewById(R.id.lay);
        }
    }
}
