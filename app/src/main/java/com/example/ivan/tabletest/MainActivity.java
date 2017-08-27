package com.example.ivan.tabletest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private SectionStatePagerAdapter sectionStatePagerAdapter;
    private ViewPager viewPager;

    DatabaseReference databaseReference;



//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//
//                case R.id.navigation_home:
//                    viewPager.setCurrentItem(0);
//
//                    return true;
//                case R.id.navigation_clubs:
//                    viewPager.setCurrentItem(1);
//                    return true;
//
//                case R.id.navigation_reservations:
//                    viewPager.setCurrentItem(2);
//                    return true;
//
//                case R.id.navigation_profile:
//                    viewPager.setCurrentItem(3);
//                    return true;
//            }
//            return false;
//        }
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.keepSynced(true);

        sectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.content);

        setupViewPager(viewPager);

        viewPager.setCurrentItem(0);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String title = "";
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;

                    case R.id.navigation_clubs:
                        viewPager.setCurrentItem(1);
                        return true;

                    case R.id.navigation_reservations:
                        viewPager.setCurrentItem(2);
                        return true;

                    case R.id.navigation_profile:
                        viewPager.setCurrentItem(3);
                        return true;

                }
                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new ClubsFragment(), "Clubs");
        adapter.addFragment(new ReservationsFragment(), "Reservations");
        adapter.addFragment(new ProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
