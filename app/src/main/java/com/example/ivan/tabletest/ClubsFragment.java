package com.example.ivan.tabletest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by Ivan on 17.8.2017..
 */

public class ClubsFragment extends Fragment {

    private DatabaseReference databaseReference;
    FirebaseRecyclerAdapter<ClubInfo, ViewHolder> firebaseRecyclerAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tabletest-329fc.firebaseio.com/Clubs");

        databaseReference.keepSynced(true);
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ClubInfo, ViewHolder>(ClubInfo.class, R.layout.custom_row, ViewHolder.class, databaseReference) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, final ClubInfo model, final int position) {

                viewHolder.textName.setText(model.getName());
                viewHolder.textAddress.setText(model.getAddress());
                viewHolder.setImage(getActivity().getApplicationContext(), model.getImage());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position == 1) {
                            startActivity(new Intent(getActivity().getApplicationContext(), ClubActivity.class));
                        } else {
                            Toast.makeText(getActivity(), "Pritisnut klub: " + model.getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

        return view;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textAddress;
        ImageView imageClub;

        View view;

        public ViewHolder(View itemView) {
            super(itemView);

            textName = (TextView) itemView.findViewById(R.id.textName);
            textAddress = (TextView) itemView.findViewById(R.id.textAddress);
            imageClub = (ImageView) itemView.findViewById(R.id.imageLogo);

            view = itemView;
        }
        public void setImage(Context context, String image){

            Picasso.with(context).load(image).fit().into(imageClub);
        }
    }

}