package com.myapplicationsqlite;

import static com.myapplicationsqlite.DatabaseHandler.useremailDB;
import static com.myapplicationsqlite.DatabaseHandler.usernameDB;
import static com.myapplicationsqlite.SigininFragment.isLoggedin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.media.RouteListingPreference;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    Toolbar buttonToggle;
    NavigationView navigationView;
    TextView tvUsername, tvEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isLoggedin){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, AffichageFragment.class, null)
                    .commit();
        }
        if (!isLoggedin){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, SigininFragment.class, null)
                    .commit();
        }

        frameLayout = findViewById(R.id.fragment_container);
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonToggle = findViewById(R.id.menuBtn);
        navigationView = findViewById(R.id.navigationView);

        //Updating header Drawer username and email
        View v = navigationView.getHeaderView(0);
        tvUsername = v.findViewById(R.id.userName);
        tvEmail = v.findViewById(R.id.userEmail);

        //Checking if user is logged in so we can set visiblity and update header Drawer values
        // And also sending the right Fragment Affichage or Login to MainActivity...
        if (isLoggedin) {
            navigationView.getMenu().removeItem(R.id.login_menu);
            tvUsername.setText(usernameDB);
            tvEmail.setText(useremailDB);
        }
        if (!isLoggedin) {
            navigationView.getMenu().removeItem(R.id.logout_menu);

        }
        buttonToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                if (itemId == R.id.calculator_menu) {
                    if (isLoggedin)
                        replaceFragment(new CalculatorFragment());
                    else
                        Toast.makeText(MainActivity.this, "Please Login First", Toast.LENGTH_LONG).show();
                }
                if (itemId == R.id.login_menu) {
                    replaceFragment(new SigininFragment());
                }
                if (itemId == R.id.afficher_menu) {
                    if (isLoggedin)
                        replaceFragment(new AffichageFragment());
                    else
                        Toast.makeText(MainActivity.this, "Please Login First", Toast.LENGTH_LONG).show();
                }
                if (itemId == R.id.logout_menu){
                    isLoggedin = false;
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    Toast.makeText(MainActivity.this, "See you soon !", Toast.LENGTH_LONG).show();
                }
                if (itemId == R.id.convert_menu) {
                    if (isLoggedin)
                        replaceFragment(new ConvertFragment());
                    else
                        Toast.makeText(MainActivity.this, "Please Login First", Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });

    }
    public void replaceFragment(Fragment fr){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fr);
        fragmentTransaction.commit();
    }

}