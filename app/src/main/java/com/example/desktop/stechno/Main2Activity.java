package com.example.desktop.stechno;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.RecoverySystem;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.LoggingMXBean;

import javax.net.ssl.HttpsURLConnection;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;


public class Main2Activity extends AppCompatActivity {

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
    String ss, idc;
    Long rt;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //Toolbar toolbar;
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tbr();


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



        ButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == ButtonDate) {
                    final Calendar c = Calendar.getInstance();

                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    final DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {

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


        DatabaseReference rev = stat.child("Status").getRef();
        DatabaseReference spinS = stat.child("Assign TO").getRef();
        DatabaseReference spinSer = stat.child("Service Type").getRef();
        DatabaseReference arel = stat.child("AreaList").getRef();
        DatabaseReference prir = stat.child("Priority").getRef();
        DatabaseReference bl = stat.child("Billing").getRef();
        DatabaseReference PayStat = stat.child("PStatus").getRef();
        DatabaseReference coun = FirebaseDatabase.getInstance().getReference().child("TaskCount");





 coun.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("tr").exists()){
                    idc = dataSnapshot.child("tr").getValue(String.class);
                 //   Toast.makeText(Main2Activity.this,idc, Toast.LENGTH_LONG).show();
                }
               // Toast.makeText(Main2Activity.this, String.valueOf(rt), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        //Billing spin
        bl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> pr1 = new ArrayList<>();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String prname1 = childDataSnapshot.getValue(String.class);
                    pr1.add(prname1);
                    ArrayAdapter<String> pradp1 = new ArrayAdapter<>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, pr1);
                    BillSpin.setAdapter(pradp1);
                    BillSpin.setSelection(1);
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
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String prname = childDataSnapshot.getValue(String.class);
                    pr.add(prname);
                    ArrayAdapter<String> pradp = new ArrayAdapter<>(Main2Activity.this, R.layout.support_simple_spinner_dropdown_item, pr);
                    PrioritySpin.setAdapter(pradp);
                    PrioritySpin.setSelection(2);
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
                    StatusSpin.setSelection(2);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });


        String date1 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        DatePick.setText(date1);

        DataAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == DataAdd) {

                    AddData();
                }
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
                    Toast.makeText(Main2Activity.this, "Permission denied", Toast.LENGTH_LONG).show();
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

                    ss = imguri.toString();
                    ImgView.setImageURI(Image_uri);
                    //Imge view with glide
                    //   Glide.with(Main2Activity.this).load(ss).into(ImgView);


                    Toast.makeText(Main2Activity.this, "Image Uploaded", Toast.LENGTH_LONG).show();

                }
            });

        } else {
            Toast.makeText(Main2Activity.this, "Image is not set", Toast.LENGTH_LONG).show();
        }
    }


    public void AddData() {

//imgUrl rr = new imgUrl();
//Toast.makeText(Main2Activity.this, ssimg, Toast.LENGTH_LONG).show();

        String dt = DatePick.getText().toString().toUpperCase();
        String nm1 = Name1.getText().toString().toUpperCase();
        String nm2 = Name2.getText().toString().toUpperCase();
        String fame = nm1 + " " + nm2;
        String time1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
        String numb = Number1.getText().toString().trim();
        String arr = AreaSpin.getSelectedItem().toString().trim().toUpperCase();
        String arrr = Area1.getText().toString().trim().toUpperCase();
        final String Stype = ServiceSpin.getSelectedItem().toString().trim().toUpperCase();
        String Sinf = InfoSer.getText().toString().trim().toUpperCase();
        String asstp = AssignSpin.getSelectedItem().toString().trim().toUpperCase();
        String statuss = StatusSpin.getSelectedItem().toString().trim().toUpperCase();
        String pr = PrioritySpin.getSelectedItem().toString().trim().toUpperCase();
        String bll = BillSpin.getSelectedItem().toString().trim().toUpperCase();
        String pstatus = PaymentSpin.getSelectedItem().toString().trim().toUpperCase();
        String rema = Remarks.getText().toString().trim().toUpperCase();

        //   Image String
        Bitmap bm = mSignaturePad.getSignatureBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] bytes11 = byteArrayOutputStream.toByteArray();
        String bst = android.util.Base64.encodeToString(bytes11, android.util.Base64.DEFAULT);



        reff = FirebaseDatabase.getInstance().getReference("New Task");
        final DatabaseReference coun = FirebaseDatabase.getInstance().getReference().child("TaskCount");

        
        String ndd = "ST" + idc;
      //  Toast.makeText(Main2Activity.this, ndd, Toast.LENGTH_LONG).show();

        proAdd pr1 = new proAdd(dt, time1, fame, numb, arr, arrr, ss, Stype, Sinf, asstp, statuss, pr, bll, pstatus, rema, bst, ndd);

        assert ndd != null;
        reff.child(ndd).setValue(pr1);
           Toast.makeText(Main2Activity.this, "Task " +ndd + " Added", Toast.LENGTH_LONG).show();
        rt = Long.decode(idc)+1;
        coun.child("tr").setValue(Long.toString(rt));
          Handler handler = new Handler();
          handler.postDelayed(new Runnable() {
              @Override
              public void run() {

              }
          },3000);
          Intent intent= getIntent();
          finish();
          startActivity(intent);
    }
    private void tbr(){
        toolbar  = findViewById(R.id.tbr);
        drawerLayout = findViewById(R.id.drawid);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(Main2Activity.this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.navview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.addTask:
                        //startActivity(new Intent(Main2Activity.this, Main2Activity.class));
                          Toast.makeText(Main2Activity.this, "Already Adding ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.viewTask:
                        startActivity(new Intent(Main2Activity.this, Main7Activity.class));
                        // Toast.makeText(MainActivity.this,"view", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });



    }
}