package com.example.ivan.tabletest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivan.tabletest.Login_Register.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ivan on 17.8.2017..
 */

public class ProfileFragment extends Fragment {private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    TextView textWelcome;
    Button buttonSignOut;
    EditText editName;
    EditText editSurname;
    EditText editPhone;
    EditText editAddress;
    Button buttonSave;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();

        textWelcome = (TextView) view.findViewById(R.id.textWelcome);
        buttonSignOut = (Button) view.findViewById(R.id.buttonSignOut);
        editName = (EditText) view.findViewById(R.id.editName);
        editSurname = (EditText) view.findViewById(R.id.editSurname);
        editPhone= (EditText) view.findViewById(R.id.editPhone);
        editAddress = (EditText) view.findViewById(R.id.editAddress);
        buttonSave = (Button) view.findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        textWelcome.setText("Dobrodošao " + user.getEmail());

        firebaseDatabase.getReference("User").child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);

                        if(userInfo == null){
                            return;
                        } else {
                            editName.setText(userInfo.getName());
                            editSurname.setText(userInfo.getLastName());
                            editPhone.setText(userInfo.getPhone());
                            editAddress.setText(userInfo.getAddress());
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


        return view;
    }

    private void saveData(){
        String name = editName.getText().toString();
        String lastName = editSurname.getText().toString();
        String phone = editPhone.getText().toString();
        String address = editAddress.getText().toString();

        UserInfo userInfo = new UserInfo(name, lastName, phone, address);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference = firebaseDatabase.getReference("User");
        databaseReference.child(user.getUid()).setValue(userInfo);

        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }
}
