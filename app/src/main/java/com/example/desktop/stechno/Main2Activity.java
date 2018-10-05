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
    Spinner AreaSpin, StatusSpin, PrioritySpin, BillSpin, PaymentSpin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference stat = database.getReference();
        DatabaseReference rev = stat.child("Status").getRef();
        DatabaseReference arel = stat.child("AreaList").getRef();
        DatabaseReference prir = stat.child("Priority").getRef();
        DatabaseReference bl = stat.child("Billing").getRef();
        DatabaseReference PayStat = stat.child("PStatus").getRef();

        //Billing spin
        bl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> pr1 = new ArrayList<>();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren() ){
                    String prname1 = childDataSnapshot.getValue(String.class);
                    pr1.add(prname1);
                    ArrayAdapter<String> pradp1 = new ArrayAdapter<>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, pr1);
                    BillSpin.setAdapter(pradp1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //PAyment Spin

        PayStat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final  List<String> pay1 = new ArrayList<>();

                for (DataSnapshot childDataSnapShot : dataSnapshot.getChildren()) {
                    String stpay = childDataSnapShot.getValue(String.class);
                    pay1.add(stpay);
                    
                    ArrayAdapter<String> payadp = new ArrayAdapter<>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, pay1);
                    PaymentSpin.setAdapter(payadp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //priority spin

        prir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> pr = new ArrayList<>();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren() ){
                    String prname = childDataSnapshot.getValue(String.class);
                    pr.add(prname);
                    ArrayAdapter<String> pradp = new ArrayAdapter<>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, pr);
                    PrioritySpin.setAdapter(pradp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //area spinner

        arel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> areas = new ArrayList<>();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String areaname = childDataSnapshot.getValue(String.class);
                    areas.add(areaname);

                    ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, areas);
                    AreaSpin.setAdapter(areaAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // status spinner
        rev.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> stats = new ArrayList<>();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String statusname = childDataSnapshot.child("Status").getValue(String.class);
                    stats.add(statusname);

                    ArrayAdapter<String> statAdapter = new ArrayAdapter<String>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, stats);
                    StatusSpin.setAdapter(statAdapter);

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
        StatusSpin = findViewById(R.id.StatusSpin);
        BillSpin = findViewById(R.id.BillSpin);
        PrioritySpin = findViewById(R.id.PrioritySpin);
        PaymentSpin = findViewById(R.id.PaymentSpin);

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

    public SignaturePad getmSignaturePad() {
        return mSignaturePad;
    }
}

