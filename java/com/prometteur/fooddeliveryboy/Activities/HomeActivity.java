package com.prometteur.fooddeliveryboy.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prometteur.fooddeliveryboy.Fragments.MyRidesFragment;
import com.prometteur.fooddeliveryboy.Fragments.HomeFragment;
import com.prometteur.fooddeliveryboy.Fragments.NotificationsFragment;
import com.prometteur.fooddeliveryboy.Fragments.UserProfileFragment;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    ActivityHomeBinding viewBinding;
    Context nContext;
    Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        nContext=this;
        viewBinding.btmNavView.setOnNavigationItemSelectedListener(navListener);
        selectedFragment=new HomeFragment(nContext);
        getSupportFragmentManager().beginTransaction().replace(viewBinding.FragmentContainer.getId(), selectedFragment).commit();

    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.navigation_Home:
                            selectedFragment=new HomeFragment(nContext);
                            break;
                        case R.id.navigation_Ride:
                            selectedFragment=new MyRidesFragment(nContext);
                            break;
                        case R.id.navigation_Notification:
                            selectedFragment=new NotificationsFragment(nContext);
                            break;
                        case R.id.navigation_Profile:
                            selectedFragment=new UserProfileFragment(nContext);
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(viewBinding.FragmentContainer.getId(), selectedFragment).commit();
                    return true;
                }
            };
}