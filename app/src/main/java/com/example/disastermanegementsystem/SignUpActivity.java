package com.example.disastermanegementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class SignUpActivity extends AppCompatActivity {

EditText name,email,phone,password,family;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Get FindViewById
        name=findViewById(R.id.txtName);
        email=findViewById(R.id.txtEmail);
        phone=findViewById(R.id.txtPhone);
        password=findViewById(R.id.txtPassword);

        //Database Reference
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("Users");


        //For Passing Family Detail List
        ListView listView=findViewById(R.id.listView_lv);
        String[] items={};
        arrayList=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtItem,arrayList);
        listView.setAdapter(adapter);
        txtInput=findViewById(R.id.txtFamilyName);
        Button btnAdd=findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem=txtInput.getText().toString();

                arrayList.add(newItem);
              txtInput.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        //Button ClickListener
        findViewById(R.id.bnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(phone.getText().toString()).exists()){
                        Toast.makeText(getApplicationContext(), "Phone number already exist.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        User user = new User(name.getText().toString(),phone.getText().toString(),email.getText().toString(), password.getText().toString(),arrayList);

                        table_user.child(phone.getText().toString()).setValue(user);

                        Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(i);
                    }                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            }
        });
    }
}