package com.example.desktop.stechno;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;

public class Main5Activity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    private static final int CREATE_CAPTURE_CODE = 1000;
    private static final int PERMISSION_CODE = 1000;
    EditText DatePick, Name1, Name2, Remarks, Number1, Area1, InfoSer;
    int mYear, mMonth, mDay;
    SignaturePad mSignaturePad;
    StorageReference mStoreage;
    ImageView ImgView;
    DatabaseReference reff;
    Uri Image_uri;
    ProgressDialog progressDialog;
    Button btnIm, DataAdd, ButtonDate;
    Spinner AreaSpin, StatusSpin, PrioritySpin, BillSpin, PaymentSpin;
    MultiSelectSpinner ServiceSpin, AssignSpin;
    Uri imguri;
    DatabaseReference reference;
    String ss, idc, getAr, getSt, getPt,getBl, getPs, getAs, getSertt, getimm,getsim;
    Long rt;
    ArrayList<proAdd> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        final Bundle extras = getIntent().getExtras();
        assert extras != null;
        String msd = extras.getString("id");
        //Toast.makeText(this, msd, Toast.LENGTH_SHORT).show();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mStoreage = FirebaseStorage.getInstance().getReference();
        reff = FirebaseDatabase.getInstance().getReference("New Task");


        final DatabaseReference stat = database.getReference();
        mSignaturePad = findViewById(R.id.signature_pad);
        DatePick = findViewById(R.id.DatePick);
        btnIm = findViewById(R.id.btnIm);
        ImgView = findViewById(R.id.ImgView);
        AreaSpin = findViewById(R.id.Areaspin);
        Name1 = findViewById(R.id.Name1);
        Name2 = findViewById(R.id.Name2);
        Remarks = findViewById(R.id.Remarks);
        InfoSer = findViewById(R.id.InfoSer);
        Number1 = findViewById(R.id.Number1);
        Area1 = findViewById(R.id.Area1);
        DataAdd = findViewById(R.id.DataAdd);
        StatusSpin = findViewById(R.id.StatusSpin);
        BillSpin = findViewById(R.id.BillSpin);
        PrioritySpin = findViewById(R.id.PrioritySpin);
        PaymentSpin = findViewById(R.id.PaymentSpin);
        ButtonDate = findViewById(R.id.ButtonDate);
        progressDialog = new ProgressDialog(this);
        DatabaseReference rev = stat.child("Status").getRef();
        DatabaseReference spinS = stat.child("Assign TO").getRef();
        DatabaseReference spinSer = stat.child("Service Type").getRef();
        final  DatabaseReference arel = stat.child("AreaList").getRef();
        DatabaseReference prir = stat.child("Priority").getRef();
        DatabaseReference bl = stat.child("Billing").getRef();
        DatabaseReference PayStat = stat.child("PStatus").getRef();

        if (msd != null) {
            reference = FirebaseDatabase.getInstance().getReference().child("New Task").child(msd);
        }

        list = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                proAdd ppp = dataSnapshot.getValue(proAdd.class);
                list.add(ppp);
                DatePick.setText(list.get(0).getTaskDate());
                Name1.setText(list.get(0).getTaskName().toUpperCase());
                Number1.setText(list.get(0).getTaskNumber());
                //AreaSpin.setTag(list.get(0).getTaskArea());
                getAr = list.get(0).getTaskArea();
                getSt = list.get(0).getTaskStatus();
                getPt = list.get(0).getTaskPriority();
                getBl = list.get(0).getTaskBilled();
                getPs = list.get(0).getTaskPaymentStatus();
                getSertt = list.get(0).getTaskServiceType();
                getAs = list.get(0).getTaskAssignedTo();
                Number1.setText(list.get(0).getTaskNumber());
                Area1.setText(list.get(0).getTaskAreaLine());
                InfoSer.setText(list.get(0).getTaskServiceInfo());
                Remarks.setText(list.get(0).getTaskRemarks());
                getimm = list.get(0).getTaskImage();
                getsim = list.get(0).getTaskSignature();
                Toast.makeText(getApplicationContext(), getimm, Toast.LENGTH_LONG).show();

                // Setting image if not null
        if (getimm != null){
         //   Toast.makeText(getApplicationContext(), "inside inm", Toast.LENGTH_LONG).show();
            Glide.with(Main5Activity.this).load(getimm).into(ImgView);
        }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        ButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == ButtonDate) {
                    final Calendar c = Calendar.getInstance();

                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    final DatePickerDialog datePickerDialog = new DatePickerDialog(Main5Activity.this, new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                            String dd = dayOfMonth + "-" + (month + 1) + "-" + year;

                            DatePick.setText(dd);

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });




        bl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> pr1 = new ArrayList<>();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String prname1 = childDataSnapshot.getValue(String.class);
                    pr1.add(prname1);
                    int index = -1;
                    for (int i = 0; i<pr1.size();i++){
                        if (pr1.get(i).equalsIgnoreCase(getBl)){
                            index = i;
                            break;
                        }
                    }
                    ArrayAdapter<String> pradp1 = new ArrayAdapter<>(Main5Activity.this, R.layout.support_simple_spinner_dropdown_item, pr1);
                    BillSpin.setAdapter(pradp1);
                    BillSpin.setSelection(index);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Taking image from camera
        btnIm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission already granteerd
                        openCamera();
                    }


                } else {
                    // sdk < mashmallow
                    openCamera();
                }


            }


        });

        //PAyment Spin

        PayStat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> pay1 = new ArrayList<>();

                for (DataSnapshot childDataSnapShot : dataSnapshot.getChildren()) {
                    String stpay = childDataSnapShot.getValue(String.class);
                    pay1.add(stpay);
                    int index = -1;
                    for (int i = 0; i<pay1.size();i++){
                        if (pay1.get(i).equalsIgnoreCase(getPs)){
                            index = i;
                            break;
                        }
                    }

                    ArrayAdapter<String> payadp = new ArrayAdapter<>(Main5Activity.this, R.layout.support_simple_spinner_dropdown_item, pay1);
                    PaymentSpin.setAdapter(payadp);
                    PaymentSpin.setSelection(index);
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
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String prname = childDataSnapshot.getValue(String.class);
                    pr.add(prname);

                    int index = -1;
                    for (int i = 0; i<pr.size();i++){
                        if (pr.get(i).equalsIgnoreCase(getPt)){
                            index = i;
                            break;
                        }
                    }

                    ArrayAdapter<String> pradp = new ArrayAdapter<>(Main5Activity.this, R.layout.support_simple_spinner_dropdown_item, pr);
                    PrioritySpin.setAdapter(pradp);
                    PrioritySpin.setSelection(index);
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
                    int index = -1;
                    for (int i = 0; i<areas.size();i++){
                        if (areas.get(i).equalsIgnoreCase(getAr)){
                            index = i;
                            break;
                        }
                    }


                    ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(Main5Activity.this, R.layout.support_simple_spinner_dropdown_item, areas);
                    AreaSpin.setAdapter(areaAdapter);
                    AreaSpin.setSelection(index);
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
                final List<String> ServS = new ArrayList<>();

                ServiceSpin = findViewById(R.id.ServiceSpin);
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    ServiceSpin = findViewById(R.id.ServiceSpin);
                    String SerSpin = childDataSnapshot.getValue(String.class);
                    ServS.add(SerSpin);

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
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String Astr = childDataSnapshot.getValue(String.class);
                    Aspin.add(Astr);
//                    int index = -1;
//                    for ( int i = 0; i<Aspin.size();i++){
//                        if (getAs.contains(Aspin.get(i))){
//                            index = i;
//
//                        }
//                    }

                    AssignSpin.setItems(Aspin);
                   // AssignSpin.setSelection(2);


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
                    int index = -1;
                    for (int i = 0; i<Resv.size();i++){
                        if (Resv.get(i).equalsIgnoreCase(getSt)){
                            index = i;
                            break;
                        }
                    }

                    ArrayAdapter<String> statAdapter = new ArrayAdapter<>(Main5Activity.this, R.layout.support_simple_spinner_dropdown_item, Resv);
                    StatusSpin.setAdapter(statAdapter);
                    StatusSpin.setSelection(index);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });






    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission from popup wa granted
                    openCamera();
                } else {
                    //permission from pouup denied
                    Toast.makeText(Main5Activity.this, "Permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    public void openCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Pictutre");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        Image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Image_uri);
        startActivityForResult(intent, CREATE_CAPTURE_CODE);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            //     ImgView.setImageURI(Image_uri);

            progressDialog.setMessage("Uploading Image ...");
            progressDialog.show();


            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference filepth = storageReference.child("Images").child(Image_uri.getSchemeSpecificPart());
            filepth.putFile(Image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful()) ;
                    imguri = urlTask.getResult();

                    getimm = imguri.toString();
                    ImgView.setImageURI(Image_uri);
                    //Imge view with glide
                    //   Glide.with(Main5Activity.this).load(ss).into(ImgView);


                    Toast.makeText(Main5Activity.this, "Image Uploaded", Toast.LENGTH_LONG).show();

                }
            });

        } else {
            Toast.makeText(Main5Activity.this, "Image is not set", Toast.LENGTH_LONG).show();
        }
    }
}
