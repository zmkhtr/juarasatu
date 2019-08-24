package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.web.azammukhtar.R;
import id.web.azammukhtar.UI.Fragment.AkunFragment;
import id.web.azammukhtar.UI.Fragment.CatatanFragment;
import id.web.azammukhtar.UI.Fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    final Fragment homeFragment = new HomeFragment();
    final Fragment catatanFragment = new CatatanFragment();
    final Fragment akunFragment = new AkunFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        fm.beginTransaction().add(R.id.main_container, akunFragment, "3").hide(akunFragment).commit();
        fm.beginTransaction().add(R.id.main_container, catatanFragment, "2").hide(catatanFragment).commit();
        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
//        startActivity(new Intent(this, LoginActivity.class));
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.item_home:
                fm.beginTransaction().hide(active).show(homeFragment).commit();
                active = homeFragment;
                getSupportActionBar().setTitle("");
                getSupportActionBar().setElevation(0);
                return true;
            case R.id.item_catatan:
                fm.beginTransaction().hide(active).show (catatanFragment).commit();
                active = catatanFragment;
                    getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>History</font>"));
                return true;
            case R.id.item_akun:
                fm.beginTransaction().hide(active).show(akunFragment).commit();
                active = akunFragment;
                getSupportActionBar().setTitle("");
                getSupportActionBar().setElevation(0);
                return true;
        }
        return false;
    };
    @Override
    protected void onResume() {
        super.onResume();
        fm.beginTransaction().show(active).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fm.beginTransaction().hide(active).commit();
    }

}
