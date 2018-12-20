package com.example.desktop.stechno;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.FirstPartyScopes;

import java.util.ArrayList;

public class Comadp extends RecyclerView.Adapter<Comadp.Comhol> {


   Context context;
   String id;
   ArrayList<Profile> profiles;
   public  Comadp(Context c, ArrayList<Profile> p){
       context = c;
       profiles = p;


   }


    @NonNull

    @Override
    public Comhol onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.com_view, viewGroup, false);
       return new Comhol(view);
    }

    @Override
    public void onBindViewHolder (@NonNull final Comhol comhol, final int i)  {
    comhol.txtName.setText(profiles.get(i).getTaskName());
    comhol.tskkid.setText(profiles.get(i).getTaskId());
    comhol.txtTask.setText(profiles.get(i).getTaskServiceType());
    comhol.txtStatus.setText(profiles.get(i).getTaskPriority());
    comhol.txtAssign.setText(profiles.get(i).getTaskAssignedTo());
    comhol.parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(context,"Long Pressed", Toast.LENGTH_SHORT).show();
            return false;
        }
    });
    comhol.parentlayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //  Toast.makeText(context,profiles.get(i).getTaskId(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context,Main6Activity.class);
            id = profiles.get(i).getTaskId();
            intent.putExtra("id", id);
            context.startActivity(intent);
        }
    });
   // id = profiles.get(i).getTaskId();
       // Toast.makeText(context, profiles.get(0).getTaskName(), Toast.LENGTH_LONG).show();


    }

    @Override
    public int getItemCount() {

        return profiles.size();

    }

    public class Comhol extends RecyclerView.ViewHolder  {
        TextView txtName, txtStatus, txtAssign, txtTask, tskkid;
        LinearLayout parentlayout;

        public Comhol(View itemView){
            super(itemView);
            txtAssign = itemView.findViewById(R.id.txtAssign);
            txtName = itemView.findViewById(R.id.txtName);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtTask = itemView.findViewById(R.id.txtTask);
            tskkid = itemView.findViewById(R.id.tskkid);

            parentlayout = itemView.findViewById(R.id.parentlayout);
        }

//
    }
}
