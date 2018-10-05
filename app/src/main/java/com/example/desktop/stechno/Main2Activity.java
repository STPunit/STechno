package com.example.desktop.stechno;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Main2Activity extends AppCompatActivity  {
    EditText DatePick;
    int mYear, mMonth, mDay;
    ImageButton BPickDate1;
    SignaturePad mSignaturePad;
    FirebaseDatabase firebaseDatabase;
    Spinner AreaSpin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference stat = database.getReference();
        DatabaseReference rev = stat.child("Status").getRef();
        rev.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> areas = new ArrayList<>();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String areaName = childDataSnapshot.child("Status").getValue(String.class);
                    areas.add(areaName);

                    ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, areas);
                    AreaSpin.setAdapter(areasAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);

        DatePick = findViewById(R.id.DatePick);
        AreaSpin = findViewById(R.id.Areaspin);
        BPickDate1 = findViewById(R.id.BPickDate1);
        String date1= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        DatePick.setText(date1);

        BPickDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == BPickDate1) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);



                    DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            DatePick.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });


    }






        public void setmSignaturePad(SignaturePad mSignaturePad) {
        this.mSignaturePad = mSignaturePad;
    }
}

