package in.askdial.askdial.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import in.askdial.askdial.fragments.BussinessDirectoryFragment;
import in.askdial.askdial.R;
import in.askdial.askdial.fragments.AboutUSFragment;
import in.askdial.askdial.fragments.ContactUSFragment;
import in.askdial.askdial.fragments.EventsFragment;
import in.askdial.askdial.fragments.HomeFragment;
import in.askdial.askdial.fragments.MainFragment;
import in.askdial.askdial.values.FunctionCalls;

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // private RecipeAdapter mAdapter;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    DrawerLayout drawer;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce = false;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        switchContent(new HomeFragment(), toolbar);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }


    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean handled = false;
        switch (item.getItemId()) {
            case R.id.action_logout:
                Intent i = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(CategoryActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                handled = true;
                break;
            case R.id.action_setting:
                Toast.makeText(CategoryActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
                handled = true;
                break;
            case R.id.action_quit:
                handled = true;
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_enquiry) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bussinessDirectory) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new BussinessDirectoryFragment()).commit();

        } else if (id == R.id.nav_Classifieds) {
            Intent i = new Intent(CategoryActivity.this, MainActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_Events) {
            EventsFragment events = new EventsFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putString("message", "Events");
            events.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, events).commit();


        } else if (id == R.id.nav_addBusinessDirectory) {

            if(FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.container_main, new MainFragment()).commit();
            }else{
                Toast.makeText(this, "Please Turn On the Internet", Toast.LENGTH_SHORT).show();
            }
            /*Car car = new Car();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putString("message", "Chetan");
            car.setArguments(bundle);
            fragmentTransaction.replace(R.id.content_main, car).commit();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, new Car()).commit();*/

        } else if (id == R.id.nav_addClassifieds) {


        } else if (id == R.id.nav_addEvents) {

        } else if (id == R.id.nav_AboutUs) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new AboutUSFragment()).commit();

        } else if (id == R.id.nav_FAQ) {
            if(FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).commit();
            }else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_ContactUs) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new ContactUSFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchContent(Fragment fragment, Toolbar toolbar) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, fragment);
        ft.commit();
        toolbar.setTitle("Home");


    }

}


