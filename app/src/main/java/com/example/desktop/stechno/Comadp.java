package com.example.desktop.stechno;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;

public class Comadp extends RecyclerView.Adapter<Comadp.Comhol> {

   Context context;
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
    public void onBindViewHolder(@NonNull  Comhol comhol,  int i) {
    comhol.txtName.setText(profiles.get(i).getTaskName());
    comhol.txtTask.setText(profiles.get(i).getTaskServiceType());
    comhol.txtStatus.setText(profiles.get(i).getTaskPriority());
    comhol.txtAssign.setText(profiles.get(i).getTaskAssignedTo());


    }

    @Override
    public int getItemCount() {




        return profiles.size();

    }

    public class Comhol extends RecyclerView.ViewHolder{
        TextView txtName, txtStatus, txtAssign, txtTask;

        public Comhol(View itemView){
            super(itemView);
            txtAssign = itemView.findViewById(R.id.txtAssign);
            txtName = itemView.findViewById(R.id.txtName);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtTask = itemView.findViewById(R.id.txtTask);
        }
    }
}
