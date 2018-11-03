package com.example.desktop.stechno;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    ArrayList<proAdd> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Intent context = getIntent();
        String id = context.getStringExtra("id");
       // Toast.makeText(Main6Activity.this,id, Toast.LENGTH_LONG).show();
        final TextView TskName = findViewById(R.id.Tskname);
        final TextView Tdate = findViewById(R.id.Tdate);
        final TextView Tid = findViewById(R.id.Tid);
        final TextView Tpay = findViewById(R.id.Tpay);
        final TextView Tareal = findViewById(R.id.Tareal);
        final TextView Tprio = findViewById(R.id.Tprio);
        final TextView Tstat = findViewById(R.id.Tstat);
        final TextView Tass = findViewById(R.id.Tassign);
        final TextView Tnum = findViewById(R.id.Tnum);
        final TextView Tserc = findViewById(R.id.Tser);
        final TextView Tinf = findViewById(R.id.Tinfo);
        final TextView Tbill = findViewById(R.id.Tbill);
        final TextView Tar = findViewById(R.id.Tar);
        final ImageView Tim = findViewById(R.id.Tim);
        final ImageView Tsig = findViewById(R.id.Tsig);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("New Task").child(id);

        list = new ArrayList<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                proAdd ppp = dataSnapshot.getValue(proAdd.class);
                list.add(ppp);
                 Tdate.setText(list.get(0).getTaskDate().toUpperCase());
                TskName.setText(list.get(0).getTaskName().toUpperCase());
                Tid.setText(list.get(0).getTaskId().toUpperCase());
                Tpay.setText(list.get(0).getTaskPaymentStatus().toUpperCase());
                Tareal.setText(list.get(0).getTaskAreaLine().toUpperCase());
                Tprio.setText(list.get(0).getTaskPriority().toUpperCase());
                Tstat.setText(list.get(0).getTaskStatus().toUpperCase());
                Tass.setText(list.get(0).getTaskAssignedTo().toUpperCase());
                Tnum.setText(list.get(0).getTaskNumber());
                Tserc.setText(list.get(0).getTaskServiceType().toUpperCase());
                Tinf.setText(list.get(0).getTaskServiceInfo().toUpperCase());
                Tbill.setText(list.get(0).getTaskBilled().toUpperCase());
                Tar.setText(list.get(0).getTaskArea().toUpperCase());
                String sig = list.get(0).getTaskSignature();
                byte[] bytes = android.util.Base64.decode(sig,android.util.Base64.DEFAULT);
                ByteArrayInputStream byteArrayInputStream =  new ByteArrayInputStream(bytes);
                Tsig.setImageBitmap(BitmapFactory.decodeStream(byteArrayInputStream));
                String imm = list.get(0).getTaskImage();
                Glide.with(Main6Activity.this).load(imm).into(Tim);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
