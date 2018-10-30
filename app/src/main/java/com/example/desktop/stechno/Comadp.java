package com.example.desktop.stechno;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;


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
    public void onBindViewHolder (@NonNull  Comhol comhol,  int i)  {
    comhol.txtName.setText(profiles.get(i).getTaskName());
    comhol.txtTask.setText(profiles.get(i).getTaskServiceType());
    comhol.txtStatus.setText(profiles.get(i).getTaskPriority());
    comhol.txtAssign.setText(profiles.get(i).getTaskAssignedTo());
   // id = profiles.get(i).getTaskId();
       // Toast.makeText(context, profiles.get(0).getTaskName(), Toast.LENGTH_LONG).show();


    }

    @Override
    public int getItemCount() {

        return profiles.size();

    }

    public class Comhol extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtStatus, txtAssign, txtTask;

        public Comhol(View itemView){
            super(itemView);


            itemView.setOnClickListener(this);
            //RecyclerView Taskcom1 = itemView.findViewById(R.id.Taskcom1);
            txtAssign = itemView.findViewById(R.id.txtAssign);
            txtName = itemView.findViewById(R.id.txtName);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtTask = itemView.findViewById(R.id.txtTask);
//            int ii = Taskcom1.getChildAdapterPosition(itemView);
//            id = profiles.get(ii).getTaskId();
//            Toast.makeText(context, id, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onClick(View v) {
            RecyclerView recyclerView = v.findViewById(R.id.Taskcom1);
            int ii = recyclerView.getChildAdapterPosition(v);
            id = profiles.get(ii).getTaskId();


            Intent intent = new Intent(context, Main6Activity.class);
            intent.putExtra("id", id);

            context.startActivity(intent);

          //  context.startActivity(new Intent(context, Main6Activity.class).putExtra(id, id));
        }
    }
}
