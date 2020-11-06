package com.example.disastermanegementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    EditText editPhone,editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Calling From text Boxes
        editPhone=findViewById(R.id.txtLoginPhone);
        editPassword=findViewById(R.id.txtLoginPassword);

        //Database Reference
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user_Login=database.getReference("Users");


        //Button Click Event
        findViewById(R.id.bnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user_Login.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(editPhone.getText().toString()).exists()){
                            User user=dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(editPassword.getText().toString())){
                 //               Toast.makeText(getApplicationContext(),"SignIn Sucsesfully",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                               // Common.currentUser=user;
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"SignIn Failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"User Not Found",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
                );
            }
        });
    }
}