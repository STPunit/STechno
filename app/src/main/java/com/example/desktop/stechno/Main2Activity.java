package com.example.desktop.stechno;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.github.gcacace.signaturepad.utils.SvgBuilder;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;


public class Main2Activity extends AppCompatActivity  {

    EditText DatePick;
    int mYear, mMonth, mDay;
    ImageButton BPickDate1;
    SignaturePad mSignaturePad;
    Image pr;
    StorageReference mStoreage;
    ImageView TestPad;
    FirebaseDatabase firebaseDatabase;


    Button SubButton;
    Spinner AreaSpin, StatusSpin, PrioritySpin, BillSpin, PaymentSpin;
    MultiSelectSpinner ServiceSpin, AssignSpin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mStoreage = FirebaseStorage.getInstance().getReference();



        final DatabaseReference stat = database.getReference();

        DatabaseReference rev = stat.child("Status").getRef();
        DatabaseReference spinS = stat.child("Assign TO").getRef();
        DatabaseReference spinSer = stat.child("Service Type").getRef();
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

        //Service Spin

        spinSer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final  List<String>  ServS = new ArrayList<>();

                ServiceSpin = findViewById(R.id.ServiceSpin);
                for ( DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){
                   ServiceSpin = findViewById(R.id.ServiceSpin);
                    String SerSpin = childDataSnapshot.getValue(String.class);
                    ServS.add(SerSpin);

                 //   ArrayAdapter<String> MsSPin = new ArrayAdapter<>(Main2Activity.this, R.layout.);
                    ServiceSpin.setItems(ServS);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        spinS.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> Aspin = new ArrayList<>();
                AssignSpin = findViewById(R.id.AssignSpin);
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){
                    String Astr = childDataSnapshot.getValue(String.class);
                    Aspin.add(Astr);
                    AssignSpin.setItems(Aspin);

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
                final List<String> Resv = new ArrayList<>();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String statusname = childDataSnapshot.child("Status").getValue(String.class);
                    Resv.add(statusname);
                    ArrayAdapter<String> statAdapter = new ArrayAdapter<>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, Resv);
                    StatusSpin.setAdapter(statAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {






            }
        });



        mSignaturePad = findViewById(R.id.signature_pad);

        DatePick = findViewById(R.id.DatePick);

        AreaSpin = findViewById(R.id.Areaspin);
       // AssignSpin = findViewById(R.id.AssignSpin);
        BPickDate1 = findViewById(R.id.BPickDate1);
        StatusSpin = findViewById(R.id.StatusSpin);
        BillSpin = findViewById(R.id.BillSpin);
        PrioritySpin = findViewById(R.id.PrioritySpin);
      //  MultiSelectSpinner ServiceSpin = (MultiSelectSpinner) findViewById(R.id.ServiceSpin);
        PaymentSpin = findViewById(R.id.PaymentSpin);
        SubButton = findViewById(R.id.SubButton);
        TestPad = findViewById(R.id.TestPad);

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

        SubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == SubButton) {


                     Bitmap bm = mSignaturePad.getSignatureBitmap();
                    TestPad.setImageBitmap(bm);
//                    Uri uri  = Uri.fromFile(new Bitmap(mSignaturePad.getSignatureBitmap()));
//
//
//                    StorageReference filepath = mStoreage.child("Photos").child(bm.toString());
//                    filepath.putFile(bm, filepath);
                }
            }
        });







    }



    //SIgnature






}

