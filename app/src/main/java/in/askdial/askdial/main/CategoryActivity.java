package in.askdial.askdial.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import in.askdial.askdial.R;
import in.askdial.askdial.fragments.AboutUSFragment;
import in.askdial.askdial.fragments.BussinessDirectoryFragment;
import in.askdial.askdial.fragments.ContactUSFragment;
import in.askdial.askdial.fragments.EventsFragment;
import in.askdial.askdial.fragments.HomeFragment;
import in.askdial.askdial.fragments.categories.Visited_CatgFragment;
import in.askdial.askdial.services.SearchServices;
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

    AutoCompleteTextView search_Edt_Txt;
    SearchServices searchServices;
    static ArrayList<String> search_list;
    FunctionCalls functionCalls;
    ArrayAdapter<String> Search_adapter;
    String searched_Value;
    ImageButton home_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        home_button= (ImageButton) findViewById(R.id.home_button);

        search_Edt_Txt= (AutoCompleteTextView) findViewById(R.id.search_EditText);
        searchServices = new SearchServices();
        functionCalls=new FunctionCalls();
        searched_Value=search_Edt_Txt.getText().toString();

       // SearchFields();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        Intent service = new Intent(CategoryActivity.this, SearchServices.class);
        startService(service);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        switchContent(new HomeFragment(), toolbar);
       // onBackPressed();

        search_Edt_Txt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    search_Edt_Txt.requestFocus();
                    handled = true;
                }
                return handled;
            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchContent(new HomeFragment(), toolbar);
            }
        });
    }

    /*@Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0);

                        }
                    })
                    .setNeutralButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;

                }
            }, 500);
        }else{
            super.onBackPressed();
            return;
        }

    }*/
  /*  @Override
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
            switchContent(new HomeFragment(), toolbar);
        } else {
            super.onBackPressed();
        }
    }*/

  /*  @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0);

                        }
                    })
                    .setNeutralButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;

                }
            }, 500);
        }else{
            super.onBackPressed();
            return;
        }

    }*/
//importanat 10 aug2017
  /*  @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (getFragmentManager().getBackStackEntryCount()>0 && !drawer.isDrawerOpen(GravityCompat.START)) {
            getFragmentManager().popBackStack();
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
    }*/


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
        // automatically handle clicks on the Home/Up btn_login, so long
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

                fragmentTransaction.replace(R.id.container_main, new Visited_CatgFragment()).commit();
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

    private void SearchFields(){

        functionCalls.LogStatus("Staff field Started");
        HashSet<String> StaffSet = new HashSet<>();
        StaffSet = searchServices.searchset;
        search_list = new ArrayList<>();
        search_list.addAll(StaffSet);
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String liststaff = list.get(i);
            String staff = liststaff.substring(0, liststaff.lastIndexOf(','));
            String staffid = liststaff.substring(liststaff.lastIndexOf(',') + 1, liststaff.length());
            search_list.add(staff);
            // listid.put(staff.toLowerCase(), staffid);
        }

        if (search_list.size() > 0) {
            functionCalls.LogStatus("Staff list Available");
            Search_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, search_list);
            search_Edt_Txt.setAdapter(Search_adapter);
            Collections.sort(search_list);
            Search_adapter.notifyDataSetChanged();
            search_Edt_Txt.setThreshold(1);
            search_Edt_Txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    searched_Value = parent.getItemAtPosition(position).toString();
                    // StaffTomeetId = listid.get(parent.getItemAtPosition(position).toString().toLowerCase());
                }
            });

        } else {
            functionCalls.LogStatus("Staff list not Available");
        }
    }

}


