package com.example.desktop.stechno;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;


public class Main2Activity extends AppCompatActivity  {

    EditText DatePick, Name1, Name2, Remarks, Number1, Area1, InfoSer;
    int mYear, mMonth, mDay;
    ImageButton BPickDate1;
    SignaturePad mSignaturePad;
    Image pr;
    StorageReference mStoreage;
    ImageView TestPad;
    FirebaseDatabase firebaseDatabase, Prodidd;
    DatabaseReference reef, reff;


    Button SubButton, DataAdd;
    Spinner AreaSpin, StatusSpin, PrioritySpin, BillSpin, PaymentSpin;
    MultiSelectSpinner ServiceSpin, AssignSpin;
   public ArrayAdapter<String > ads;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mStoreage = FirebaseStorage.getInstance().getReference();
        reff  = FirebaseDatabase.getInstance().getReference("New Task");




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
                List<String> Aspin = new ArrayList<>();
                AssignSpin = findViewById(R.id.AssignSpin);
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){
                    String Astr = childDataSnapshot.getValue(String.class);
                    Aspin.add(Astr);
                    ArrayAdapter<String> ads = new ArrayAdapter<>(Main2Activity.this, android.R.layout.simple_list_item_multiple_choice, Aspin);
                    AssignSpin.setAdapter( ads);

//
//                    boolean[] SelectI = new boolean[ads.getCount()];
//                    SelectI[0] = true;
//                    String argg =
//                    AssignSpin.setSelected(SelectI);
//                    AssignSpin.getFilterTouchesWhenObscured();
                 //  Array trys = getResources().getAssets().getLocales();
                  //  int lenn = ads.getCount();
//                    AssignSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                            Toast.makeText(Main2Activity.this, "Selected : " + Aspin.get(position), Toast.LENGTH_LONG ).show();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });


//                    int cntt = ads.getCount();
//                    SparseBooleanArray sp =


                   // SparseBooleanArray checked =
                  //  String trsss = AssignSpin.getSelectedItem().toString();
                   // String th = ads.getPosition(pos)
//                    if (trsss.contains("Amit Soni") ){
//                        Toast.makeText(Main2Activity.this, "Amit soni selected", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(Main2Activity.this, "Nothing here", Toast.LENGTH_SHORT).show();
//                    }



//                    List<String> gds =  new ArrayList<>();
//                    gds = AssignSpin.getSelectedItem(String.class);



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
        Name1 = findViewById(R.id.Name1);
        Name2 = findViewById(R.id.Name2);
        Remarks = findViewById(R.id.Remarks);
        InfoSer = findViewById(R.id.InfoSer);
        Number1 = findViewById(R.id.Number1);
        Area1 = findViewById(R.id.Area1);
        DataAdd = findViewById(R.id.DataAdd);
        BPickDate1 = findViewById(R.id.BPickDate1);
        StatusSpin = findViewById(R.id.StatusSpin);
        BillSpin = findViewById(R.id.BillSpin);
        PrioritySpin = findViewById(R.id.PrioritySpin);
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

        DataAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( v == DataAdd)
                {
                    AddData();
                }
            }
        });

        SubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == SubButton) {


                    Bitmap bm = mSignaturePad.getSignatureBitmap();

                   // TestPad.setImageBitmap(bm);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                    byte [] bytes11 = byteArrayOutputStream.toByteArray();
                    String bst = android.util.Base64.encodeToString(bytes11, android.util.Base64.DEFAULT);
                   // StorageReference grs= mStoreage.child("Images");
                    DatabaseReference trss = database.getReference("Image");
                    trss.setValue(bst);
                    trss.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String sss = dataSnapshot.getValue(String.class);

                            byte[] dcdstr = android.util.Base64.decode(sss, android.util.Base64.DEFAULT);
                            Bitmap bmssc = BitmapFactory.decodeByteArray(dcdstr, 0, dcdstr.length);
                           TestPad.setImageBitmap(bmssc);
                            String tryyu = AssignSpin.toString();
                            if (tryyu.contains("Amit Soni")){
                                Toast.makeText(Main2Activity.this, "Amit soni is here", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(Main2Activity.this, "Not found ", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
















//                    Uri uri  = Uri.fromFile(new Bitmap(mSignaturePad.getSignatureBitmap()));
//
//
//                    StorageReference filepath = mStoreage.child("Photos").child(bm.toString());
//                    filepath.putFile(bm, filepath);
//                    File f = new File("path");
//
//
//
//
//
//                    ByteArrayOutputStream pht = new ByteArrayOutputStream();
//                    bm.compress(Bitmap.CompressFormat.PNG, 0, pht);
//                    byte[] bitmapdata = pht.toByteArray();
//                    FileOutputStream fos = new FileOutputStream(ImgFile);
//                    fos.write(bitmapdata);
//                    fos.flush();

                }
            }
        });













    }


    public void AddData()
    {
        String dt = DatePick.getText().toString().trim();
        String nm1 = Name1.getText().toString().trim();
        String nm2 = Name2.getText().toString().trim();
        String fame = Name1 + " " + Name2;
        String numb = Number1.getText().toString().trim();
        String arr = AreaSpin.getSelectedItem().toString().trim();
        String arrr = Area1.getText().toString().trim();
        String Stype = ServiceSpin.getSelectedItem().toString().trim();
        String Sinf = InfoSer.getText().toString().trim();
        String asstp = AssignSpin.getSelectedItem().toString().trim();
        String statuss = StatusSpin.getSelectedItem().toString().trim();
        String pr = PrioritySpin.getSelectedItem().toString().trim();
        String bll = BillSpin.getSelectedItem().toString().trim();
        String pstatus = PaymentSpin.getSelectedItem().toString().trim();
        String rema = Remarks.getText().toString().trim();

        //   Image String
        Bitmap bm = mSignaturePad.getSignatureBitmap();

        // TestPad.setImageBitmap(bm);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte [] bytes11 = byteArrayOutputStream.toByteArray();
        String bst = android.util.Base64.encodeToString(bytes11, android.util.Base64.DEFAULT);
        // DatabaseReference reff = firebaseDatabase.getReference().getRef().child("New_Task");
     //    DatabaseReference reef = FirebaseDatabase.getInstance().getReference("New_Task");

//
//                 String dt = DatePick.getText().toString();
//                 String nm1 = Name1.getText().toString();
//                 String nm2 = Name2.getText().toString();
//                 String fame = Name1 + "_" + Name2;
//                 String numb = Number1.getText().toString();
//                 String Ar1 = AreaSpin.toString();
//                 String Ar2 = Area1.toString();
//                 String Stype = ServiceSpin.toString();
//                 String Sinf = InfoSer.getText().toString();
//                 String Asst = AssignSpin.toString();
//                 String Statuss = StatusSpin.toString();
//                 String pr = PrioritySpin.toString();
//                 String bll = BillSpin.toString();
//                 String pstatus = PaymentSpin.toString();
//                 String rema = Remarks.getText().toString();
//
//                 //   Image String
//                 Bitmap bm = mSignaturePad.getSignatureBitmap();
//
//                 // TestPad.setImageBitmap(bm);
//                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                 bm.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
//                 byte [] bytes11 = byteArrayOutputStream.toByteArray();
//                 String bst = android.util.Base64.encodeToString(bytes11, android.util.Base64.DEFAULT);


                reff = FirebaseDatabase.getInstance().getReference("New Task");

                 String ndd = reff.push().getKey();
                 proAdd pr1 = new proAdd(dt, fame, numb, arr, arrr, Stype, Sinf, asstp, statuss, pr, bll, pstatus, rema, bst, ndd);
                 reff.child(ndd).setValue(pr1);
                 Toast.makeText(Main2Activity.this, "Task Added", Toast.LENGTH_LONG).show();

             }

    }









