package com.example.desktop.stechno;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Comadp extends RecyclerView.Adapter<Comadp.Comhol> {
    DatabaseReference tsk = FirebaseDatabase.getInstance().getReference();
    long countpo;
    DatabaseReference rootref = tsk.child("New Task");
    Query query = rootref.orderByChild("TaskStatus").equalTo("COMPLETED");
    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            long countpo = dataSnapshot.getChildrenCount();
            String stpo = Long.toString(countpo);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    @NonNull

    @Override
    public Comhol onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.com_view, viewGroup, false);
       return new Comhol(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comhol comhol, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
