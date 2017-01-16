package es.android.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import es.android.utils.Utils.Constants;

public class MainActivity extends AppCompatActivity {

    private final String ARG_SELECTED_ITEM = "arg_selected_item";
    private int selectedItem;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
            checkPermission();
        }

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem savedItem;
        if(savedInstanceState != null){
            selectedItem = savedInstanceState.getInt(ARG_SELECTED_ITEM, 0);
            savedItem = navigationView.getMenu().findItem(selectedItem);
        }else {
            savedItem = navigationView.getMenu().getItem(0);
        }
        selectFragment(savedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_SELECTED_ITEM, selectedItem);
        super.onSaveInstanceState(outState);
    }

    private void selectFragment(MenuItem item){
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menu_documents:
                fragment = new DirectoriesFragment();
                break;
            case R.id.menu_prueba_uno:
                fragment = new UnknownFragment();
                break;
            case R.id.menu_prueba_dos:
                fragment = new Unknown2Fragment();
        }

        selectedItem = item.getItemId();

        if(fragment != null){
            setTitle(item.getTitle());

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, fragment, fragment.getTag());
            ft.commit();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.READ_AND_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == Constants.READ_AND_WRITE_EXTERNAL_STORAGE) {
            checkPermission();
        }
    }


}
