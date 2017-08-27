package com.example.ivan.tabletest;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ClubActivity extends AppCompatActivity {

    ImageView imageClub;
    EditText editName, editLocation;
    Button buttonReserve;
    int number = 2;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();



        imageClub = (ImageView) findViewById(R.id.imageClub);

        Picasso.with(ClubActivity.this).load("https://firebasestorage.googleapis.com/v0/b/tabletest-329fc.appspot.com/o/Club_Logo%2F18425026_1519345904776442_7708343206979372485_n.png?alt=media&token=bb48375f-daca-46e6-813a-a678d7cc1ce7").fit().into(imageClub);


        buttonReserve = (Button) findViewById(R.id.buttonReserve);

        buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ClubActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_reserve, null);
                final TextView editNumber = (TextView) view.findViewById(R.id.textNumber);
                Button buttonMinus = (Button) view.findViewById(R.id.buttonMinus);
                Button buttonPlus = (Button) view.findViewById(R.id.buttonPlus);
                Button buttonConfirm = (Button) view.findViewById(R.id.buttonConfirm);



                builder.setView(view);
                final AlertDialog dialog = builder.create();
                dialog.show();

                buttonMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(number > 2) {
                            number = number - 1;
                            editNumber.setText("" + number);
                        } else {
                            Toast.makeText(ClubActivity.this, "Greska", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                buttonPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(number < 9 ) {
                            number = number + 1;
                            editNumber.setText("" + number);
                        }else {
                            Toast.makeText(ClubActivity.this, "Greska", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                buttonConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        createReservation(number);



                        Toast.makeText(ClubActivity.this, "Uspjesna rezervacija", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });
    }
    private void createReservation(int number) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();

        databaseReference = firebaseDatabase.getReference("Reservations/Vanilla/Saturday");
        databaseReference.child(user.getUid()).child("email").setValue(email);
        databaseReference.child(user.getUid()).child("peopleNumber").setValue(number);
    }

}
