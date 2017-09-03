package in.askdial.askdial.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.fragments.AboutUSFragment;
import in.askdial.askdial.fragments.ContactUSFragment;
import in.askdial.askdial.fragments.HomeFragment;
import in.askdial.askdial.fragments.search.SearchFragment;
import in.askdial.askdial.services.AreaServices;
import in.askdial.askdial.services.CityServices;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // private RecipeAdapter mAdapter;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    DrawerLayout drawer;
    Toolbar toolbar,toolbar1;
    boolean doubleBackToExitPressedOnce = false;

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    AutoCompleteTextView search_Edt_Txt;
    TextView search_textview;
    EditText search_editext1;

    //SearchServices searchServices;
    static ArrayList<String> search_list;
    FunctionCalls functionCalls;
    ArrayAdapter<String> Search_adapter;
    String searched_Value;
    ImageButton home_button;
    View layout;
    LinearLayout linearlayout_search;
    ImageView ivClearSearchText;

    //get downlist of city and area in search
    CityServices cityServices;
    AreaServices areaServices;
    private Spinner city,area;
    HashSet<String> CityHashSet;
    ArrayList<String> cityArraylist;
    HashSet<String> areaHashset;
    ArrayList<String> areaArrayList;
    HashMap<String, String> cityidHashmap;

    POJOValue detailsValue=new POJOValue();
    ConnectingTask task=new ConnectingTask();
    Thread searchthread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = settings.edit();
        //Searvice for Search
        //searchServices = new SearchServices();
        //searched_Value=search_Edt_Txt.getText().toString();
        // SearchFields();
        /*Intent service = new Intent(CategoryActivity.this, SearchServices.class);
        startService(service)*/;

        city= (Spinner) findViewById(R.id.spinner_city);
        area= (Spinner) findViewById(R.id.spinner_area);
        cityServices= new CityServices();
        areaServices=new AreaServices();

        CityHashSet = new HashSet<>();
        cityArraylist= new ArrayList<>();
        CityHashSet=cityServices.citysearchset;
        ArrayList<String> citylist1 = new ArrayList<>();
        cityidHashmap = new HashMap<>();
        citylist1.addAll(CityHashSet);


        for (int i = 0; i < citylist1.size(); i++) {
            String liststaff = citylist1.get(i);
            String staff = liststaff.substring(0, liststaff.lastIndexOf(','));
            String staffid = liststaff.substring(liststaff.lastIndexOf(',')+1, liststaff.length());
            cityArraylist.add(staff);
            cityidHashmap.put(staff.toLowerCase(), staffid);
        }

        if (cityArraylist.size() > 0) {
//            functionCalls.LogStatus("Staff list Available");
            ArrayAdapter<String> citydataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityArraylist);
            citydataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city.setAdapter(citydataAdapter);
            Collections.sort(cityArraylist);
            citydataAdapter.notifyDataSetChanged();

            city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String str = city.getSelectedItem().toString();
                    String City_id = cityidHashmap.get(parent.getItemAtPosition(position).toString().toLowerCase());
                    ConnectingTask.GetAreaSearchServices searchFeching = task.new GetAreaSearchServices(City_id,detailsValue, areaHashset);
                    searchFeching.execute();
                    searchthread = null;
                    Runnable runnable = new StaffData();
                    searchthread = new Thread(runnable);
                    searchthread.start();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
           /* city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String Contractor = parent.getItemAtPosition(position).toString();
                    String City_id = cityidHashmap.get(parent.getItemAtPosition(position).toString().toLowerCase());
                    Toast.makeText(CategoryActivity.this, "City Id is: "+City_id, Toast.LENGTH_SHORT).show();
                }
            });*/

        } else {
          //  functionCalls.LogStatus("Staff list not Available");
        }
        areaHashset = new HashSet<>();
        areaArrayList= new ArrayList<>();

        //areaHashset=areaServices.areasearchset;
        /*areaArrayList.addAll(areaHashset);
        ArrayAdapter<String> areadataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,areaArrayList);
        areadataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areadataAdapter.notifyDataSetChanged();
        area.setAdapter(areadataAdapter);*/

        /*ArrayAdapter<String> citydataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, citylist1);
        citydataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citydataAdapter.notifyDataSetChanged();
        city.setAdapter(citydataAdapter);*/


        toolbar1.setVisibility(View.GONE);
        home_button= (ImageButton) findViewById(R.id.home_button);
        layout=findViewById(R.id.container_main);
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);

        //search_Edt_Txt= (AutoCompleteTextView) findViewById(R.id.search_EditText);
        linearlayout_search= (LinearLayout) findViewById(R.id.linearlayout_search);
        search_textview= (TextView) findViewById(R.id.search_textview);
        search_editext1 =(EditText) findViewById(R.id.search_edittext1);
        ivClearSearchText=(ImageView) findViewById(R.id.ivClearSearchText);

        functionCalls=new FunctionCalls();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        switchContent(new HomeFragment(), toolbar);

        search_textview.setText("");
        search_editext1.setText("");

        search_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_textview.setVisibility(View.GONE);
                layout.setVisibility(View.GONE);
                linearlayout_search.setVisibility(View.VISIBLE);
                search_editext1.requestFocus();
                search_editext1.setRawInputType(InputType.TYPE_CLASS_TEXT);
                search_editext1.setTextIsSelectable(true);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(search_editext1, InputMethodManager.SHOW_IMPLICIT);

                search_editext1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        if(charSequence.length() > 0){
                            ivClearSearchText.setVisibility(View.VISIBLE);
                        }else{
                            ivClearSearchText.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {}
                });
            }
        });


        search_editext1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    // create class object
                    if (functionCalls.isInternetOnn(CategoryActivity.this)) {
                        searched_Value= search_editext1.getText().toString();
                        SearchFragment events = new SearchFragment();
                        layout.setVisibility(View.VISIBLE);
                        search_textview.setText(searched_Value);
                        linearlayout_search.setVisibility(View.GONE);
                        search_textview.setVisibility(View.VISIBLE);
                        hideSoftKeyboard(CategoryActivity.this);
                        Bundle bundle = new Bundle();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("keyword", searched_Value);
                        events.setArguments(bundle);
                        fragmentTransaction.replace(R.id.container_main, events).addToBackStack(null).commit();
                    } else {
                        Toast.makeText(CategoryActivity.this, "Please Turn On Internet", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        ivClearSearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_editext1.setText("");
            }
        });

       /* search_Edt_Txt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    search_Edt_Txt.requestFocus();
                    handled = true;
                }
                return handled;
            }
        });*/

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_textview.setText("");
                search_editext1.setText("");
                switchContent(new HomeFragment(), toolbar);
            }
        });
    }

    class StaffData implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Fetchsearch();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }

    private void Fetchsearch() {
        if (detailsValue.isSEARCHArea_Success()) {
            detailsValue.setSEARCHArea_Success(false);
            searchthread.interrupt();
            areaArrayList.addAll(areaHashset);
            ArrayAdapter<String> areadataAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,areaArrayList);
            areadataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Collections.sort(areaArrayList);
            areadataAdapter.notifyDataSetChanged();
            area.setAdapter(areadataAdapter);

        }
        if (detailsValue.isSEARCHArea_Failure()) {
            detailsValue.setSEARCHArea_Failure(false);
            searchthread.interrupt();

        }
    }

    @Override
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bussinessDirectory) {
           /* FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new BussinessDirectoryFragment()).commit();*/
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_Classifieds) {
            if(FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();
            }else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_Events) {
            if(FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();
            }else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }


        } else if (id == R.id.nav_addBusinessDirectory) {
            if(FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();
            }else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_addClassifieds) {

        } else if (id == R.id.nav_addEvents) {

        } else if (id == R.id.nav_AboutUs) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new AboutUSFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_FAQ) {
            if(FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();
            }else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_ContactUs) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new ContactUSFragment()).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //to do switch to respective fragment
    public void switchContent(Fragment fragment, Toolbar toolbar) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, fragment);
        ft.commit();
    }

    //hide keybord when needs
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void SearchFields(){

        functionCalls.LogStatus("Staff field Started");
        HashSet<String> StaffSet = new HashSet<>();
        //StaffSet = searchServices.searchset;
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


