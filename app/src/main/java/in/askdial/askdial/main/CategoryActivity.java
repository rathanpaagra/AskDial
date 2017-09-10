package in.askdial.askdial.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.fragments.AboutUSFragment;
import in.askdial.askdial.fragments.ContactUSFragment;
import in.askdial.askdial.fragments.HomeFragment;
import in.askdial.askdial.fragments.classifieds.ClassifiedsCategory;
import in.askdial.askdial.fragments.search.SearchFragment;
import in.askdial.askdial.fragments.viewmoreCategories.MainFragment;
import in.askdial.askdial.services.CategoriesServices;
import in.askdial.askdial.services.CityServices;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // private RecipeAdapter mAdapter;
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    DrawerLayout drawer;
    Toolbar toolbar, toolbar1;
    boolean doubleBackToExitPressedOnce = false;

    AutoCompleteTextView search_Edt_Txt;
    TextView search_textview;
    AutoCompleteTextView search_editext1;

    //SearchServices searchServices;
    static ArrayList<String> search_list;
    FunctionCalls functionCalls;
    ArrayAdapter<String> Search_adapter;
    String searched_Value;
    ImageButton home_button;
    View layout;
    LinearLayout linearlayout_search,linearlayout_spinner,linear_layout_autocomplete_txt,linear_layout_spinner_area,
                 linear_layoutbutton_for_spinner_area;
    ImageView ivClearSearchText;

    //get downlist of city and area in search
    CityServices cityServices;
    CategoriesServices categoriesServices;
    Spinner city, area;
    String City_id,City_Area, Area_Name, Area_ID;

    HashSet<String> CityHashSet;
    ArrayList<String> cityArraylist;
    HashMap<String, String> cityidHashmap;

    HashSet<String> areaHashset1;
    ArrayList<String> areaArrayList;
    ArrayList<String> areaArrayList1;
    HashMap<String, String> areaidHashmap;

    HashSet<String> CategorySearchHashSet;
    ArrayList<String> CategorySearchArraylist;

    POJOValue detailsValue = new POJOValue();
    ConnectingTask task = new ConnectingTask();
    Thread searchthread;

    String BASE_URL_SEARCH = DataApi.BASE_URL;

    static ProgressDialog dialog = null;
    ArrayAdapter<String> areadataAdapter;

    AutoCompleteTextView search_autocompletetextview;
    Button button_for_spinner;
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
        startService(service)*/
        ;
        city = (Spinner) findViewById(R.id.spinner_city);
        area = (Spinner) findViewById(R.id.spinner_area);
        search_autocompletetextview= (AutoCompleteTextView) findViewById(R.id.search_autocompletetextview);

        //search_Edt_Txt= (AutoCompleteTextView) findViewById(R.id.search_EditText);
        linearlayout_search = (LinearLayout) findViewById(R.id.linearlayout_search);
        linearlayout_spinner = (LinearLayout) findViewById(R.id.linearlayout_spinner);
        linear_layout_autocomplete_txt = (LinearLayout) findViewById(R.id.linear_layout_autocomplete_txt);
        linear_layout_spinner_area = (LinearLayout) findViewById(R.id.linear_layout_spinner_area);
        linear_layoutbutton_for_spinner_area = (LinearLayout) findViewById(R.id.linear_layoutbutton_for_spinner_area);
        button_for_spinner= (Button) findViewById(R.id.button_for_spinner_area);
        search_textview = (TextView) findViewById(R.id.search_textview);
        search_editext1 = (AutoCompleteTextView) findViewById(R.id.search_edittext1);

        ivClearSearchText = (ImageView) findViewById(R.id.ivClearSearchText);
        functionCalls = new FunctionCalls();
        cityServices = new CityServices();
        categoriesServices = new CategoriesServices();

        //Autosuggestion for search Category
        CategorySearchHashSet = new HashSet<>();
        CategorySearchArraylist = new ArrayList<>();
        CategorySearchHashSet=categoriesServices.categoriesSearchset;
        CategorySearchArraylist.addAll(CategorySearchHashSet);

        //Autosuggestion for search City and Area
        CityHashSet = new HashSet<>();
        cityArraylist = new ArrayList<>();
        final HashSet<String> areaHashset = new HashSet<>();
        areaHashset1 = new HashSet<>();
        areaArrayList = new ArrayList<>();
        areaArrayList1 = new ArrayList<>();
        CityHashSet = cityServices.citysearchset;

        ArrayList<String> citylist1 = new ArrayList<>();
        cityidHashmap = new HashMap<>();
        areaidHashmap = new HashMap<>();
        citylist1.addAll(CityHashSet);

        for (int i = 0; i < citylist1.size(); i++) {
            String liststaff = citylist1.get(i);
            String staff = liststaff.substring(0, liststaff.lastIndexOf(','));
            String staffid = liststaff.substring(liststaff.lastIndexOf(',') + 1, liststaff.length());
            cityArraylist.add(staff);
            cityidHashmap.put(staff.toLowerCase(), staffid);
        }

        if (cityArraylist.size() > 0) {
            ArrayAdapter<String> citydataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityArraylist);
            citydataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city.setAdapter(citydataAdapter);
            Collections.sort(cityArraylist);
            citydataAdapter.notifyDataSetChanged();

            city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String str = city.getSelectedItem().toString();
                    City_id = cityidHashmap.get(parent.getItemAtPosition(position).toString().toLowerCase());
                    areaArrayList.clear();
                    area.setAdapter(new ArrayAdapter<String>(CategoryActivity.this,android.R.layout.simple_dropdown_item_1line,areaArrayList1));
                    new GetAreaSearchServices(City_id, detailsValue, areaHashset).execute();
                    /*area.performClick();
                    area.requestFocus();
                    area.requestFocusFromTouch();*/
                    /*ConnectingTask.GetAreaSearchServices searchFeching = task.new GetAreaSearchServices(City_id,detailsValue, areaHashset);
                    searchFeching.execute();
                    dialog = ProgressDialog.show(CategoryActivity.this, "", "Please Wait...", true);
                    dialog.setCancelable(true);
                    searchthread = null;
                    Runnable runnable = new StaffData();
                    searchthread = new Thread(runnable);
                    searchthread.start();
                    // Refresh main activity upon close of dialog box*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } else {
            //  functionCalls.LogStatus("Staff list not Available");
        }

        //Auto Suggestion for Search Category

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, CategorySearchArraylist);
        search_editext1.setDropDownBackgroundResource(R.color.white);
        search_editext1.setTextColor(Color.WHITE);
        //search_autocompletetextview.setBackgroundColor(Color.WHITE);
        search_editext1.setAdapter(adapter);
        search_editext1.setThreshold(1);
        search_editext1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searched_Value = parent.getItemAtPosition(position).toString();
                // StaffTomeetId = listid.get(parent.getItemAtPosition(position).toString().toLowerCase());
            }
        });


        toolbar1.setVisibility(View.GONE);
        home_button = (ImageButton) findViewById(R.id.home_button);
        layout = findViewById(R.id.container_main);
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);


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
                linearlayout_spinner.setVisibility(View.VISIBLE);
                linearlayout_search.setVisibility(View.VISIBLE);

                button_for_spinner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        linear_layout_autocomplete_txt.setVisibility(View.GONE);
                        linear_layoutbutton_for_spinner_area.setVisibility(View.GONE);
                        linear_layout_spinner_area.setVisibility(View.VISIBLE);
                        area.performClick();
                        area.requestFocus();
                    }
                });
                /*city.performClick();
                city.requestFocus();
                city.requestFocusFromTouch();*/

                /*search_editext1.requestFocus();
                search_editext1.setRawInputType(InputType.TYPE_CLASS_TEXT);
                search_editext1.setTextIsSelectable(true);*/

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(search_editext1, InputMethodManager.SHOW_IMPLICIT);

                search_editext1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        if (charSequence.length() > 0) {
                            ivClearSearchText.setVisibility(View.VISIBLE);
                        } else {
                            ivClearSearchText.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
            }
        });


        search_editext1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    // create class object
                    if (functionCalls.isInternetOnn(CategoryActivity.this)) {
                        searched_Value = search_editext1.getText().toString();
                        SearchFragment events = new SearchFragment();
                        layout.setVisibility(View.VISIBLE);
                        search_textview.setText(searched_Value);
                        linearlayout_search.setVisibility(View.GONE);
                        linearlayout_spinner.setVisibility(View.GONE);
                        search_textview.setVisibility(View.VISIBLE);
                        Bundle bundle = new Bundle();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        bundle.putString("city_id", City_id);
                        bundle.putString("area_name", Area_Name);
                        bundle.putString("keyword", searched_Value);
                        events.setArguments(bundle);
                        hideSoftKeyboard(CategoryActivity.this);
                        fragmentTransaction.replace(R.id.container_main, events).addToBackStack(null).commit();
                    } else {
                        Toast.makeText(CategoryActivity.this, "Please Turn On Internet", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

        search_autocompletetextview.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    search_editext1.requestFocus();
                    handled = true;
                }
                return handled;
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
                //search_textview.setText("");
                //search_editext1.setText("");
                refereshactivity();
                //switchContent(new HomeFragment(), toolbar);
            }
        });
    }

    //used to refresh the activity
    public void refereshactivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void Spinneritem() {

        //areaArrayList.add(0,"All");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, areaArrayList);
        search_autocompletetextview.setDropDownBackgroundResource(R.color.white);
        search_autocompletetextview.setTextColor(Color.WHITE);
        //search_autocompletetextview.setBackgroundColor(Color.WHITE);
        search_autocompletetextview.setAdapter(adapter);
        search_autocompletetextview.setThreshold(1);
        search_autocompletetextview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Area_Name = parent.getItemAtPosition(position).toString();
                // StaffTomeetId = listid.get(parent.getItemAtPosition(position).toString().toLowerCase());
            }
        });


        areadataAdapter = new ArrayAdapter<String>(CategoryActivity.this, android.R.layout.simple_spinner_item, areaArrayList);
        areadataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Collections.sort(areaArrayList);
        area.setAdapter(areadataAdapter);
        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Area_Name = area.getSelectedItem().toString();
                //Area_ID = areaidHashmap.get(parent.getItemAtPosition(position).toString().toLowerCase());
                search_autocompletetextview.setText(Area_Name);
                linear_layout_autocomplete_txt.setVisibility(View.VISIBLE);
                linear_layoutbutton_for_spinner_area.setVisibility(View.VISIBLE);
                linear_layout_spinner_area.setVisibility(View.GONE);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                linear_layout_autocomplete_txt.setVisibility(View.VISIBLE);
                linear_layoutbutton_for_spinner_area.setVisibility(View.VISIBLE);
                linear_layout_spinner_area.setVisibility(View.GONE);

            }
        });
        /*areaArrayList.addAll(areaHashset);
        for (int i = 0; i < areaArrayList.size(); i++) {
            String liststaff = areaArrayList.get(i);
            String staff = liststaff.substring(0, liststaff.lastIndexOf(','));
            String staffid = liststaff.substring(liststaff.lastIndexOf(',') + 1, liststaff.length());
            areaArrayList1.add(staff);
            areaidHashmap.put(staff.toLowerCase(), staffid);
        }

        if (areaArrayList1.size() > 0) {
            ArrayAdapter<String> citydataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, areaArrayList1);
            citydataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            area.setAdapter(citydataAdapter);
            Collections.sort(areaArrayList1);
            citydataAdapter.notifyDataSetChanged();
            area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     Area_Name = area.getSelectedItem().toString();
                    //Area_ID = areaidHashmap.get(parent.getItemAtPosition(position).toString().toLowerCase());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

        } else {
            //  functionCalls.LogStatus("Staff list not Available");
        }*/



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
            dialog.dismiss();
            //check();
            searchthread.interrupt();
            /*//refereshactivity();
            areaHashset1=areaHashset;
            ArrayList<String> arealist=new ArrayList<>();
            arealist.addAll(areaHashset1);

            for (int i = 0; i < arealist.size(); i++) {
                String liststaff = arealist.get(i);
                String staff = liststaff.substring(0, liststaff.lastIndexOf(','));
                String staffid = liststaff.substring(liststaff.lastIndexOf(',') + 1, liststaff.length());
                areaArrayList.add(staff);
                areaidHashmap.put(staff.toLowerCase(), staffid);
            }

            if (areaArrayList.size() > 0) {
                ArrayAdapter<String> areadataAdapter = new ArrayAdapter<String>(CategoryActivity.this, android.R.layout.simple_spinner_item, areaArrayList);
                areadataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Collections.sort(areaArrayList);
                area.setAdapter(areadataAdapter);
            }*/

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
        } else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!doubleBackToExitPressedOnce) {
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bussinessDirectory) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new MainFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_Classifieds) {
            if (FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new ClassifiedsCategory()).addToBackStack(null).commit();
            } else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_Events) {
            if (FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();
            } else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }


        } else if (id == R.id.nav_addBusinessDirectory) {
            if (FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();
            } else {
                Toast.makeText(this, "Please turn on the Internet", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_addClassifieds) {

        } else if (id == R.id.nav_addEvents) {

        } else if (id == R.id.nav_AboutUs) {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_main, new AboutUSFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_FAQ) {
            if (FunctionCalls.isInternetOn(CategoryActivity.this)) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new HomeFragment()).addToBackStack(null).commit();
            } else {
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
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void SearchFields() {

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

    //after sending city id getting area names and ID
    public class GetAreaSearchServices extends AsyncTask<String, String, String> {
        String result = "", SearchingName;
        POJOValue details;
        ArrayList<POJOValue> arrayList;
        java.util.HashSet<String> HashSet;


        public GetAreaSearchServices(String search, POJOValue detailsValue, HashSet<String> hashSet/*ArrayList<POJOValue> arrayList*/) {
            this.SearchingName = search;
            this.details = detailsValue;
            this.HashSet = hashSet;
            //this.arrayList=arrayList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                result = GetAreaName(SearchingName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            GetAreaSearchDetails(result, details, HashSet/*arrayList*/);
//            areadataAdapter.notifyDataSetChanged();
        }
    }

    //calling area name and id
    public String GetAreaName(String city_id) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("city_id", city_id);
        //datamap.put("comapany_area", area);

        try {
            response = UrlPostConnection("Get_area", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //getArea Results
    public void GetAreaSearchDetails(String result, POJOValue details, HashSet<String> hashSet) {
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                if (jo != null) {
                    String message = jo.getString("message");
                    if (message.equals("Success")) {
                        //String StaffId = jo.getString("area_id");
                        String Staffname = jo.getString("area_name");
                        // list.add(Staff);
                        //list.add(Staffname);
                        list.add(Staffname/*+","+StaffId*/);
                        areaArrayList.clear();
                        area.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,areaArrayList));
                        areaArrayList.addAll(list);
                        Spinneritem();
                        if (i == (ja.length() - 1)) {
                            details.setSEARCHArea_Success(true);
                            // hashSet.addAll(list);
                            //hashSet.addAll(list);
                            //Spinneritem();

                        }
                    } else {
                        details.setSEARCHArea_Failure(true);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String UrlPostConnection(String Post_Url, HashMap<String, String> datamap) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + BASE_URL_SEARCH + Post_Url);
        URL url = new URL(BASE_URL_SEARCH + Post_Url);
        functionCalls.LogStatus("URL Connection 1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        functionCalls.LogStatus("URL Connection 2");
        conn.setReadTimeout(15000);
        functionCalls.LogStatus("URL Connection 3");
        conn.setConnectTimeout(15000);
        functionCalls.LogStatus("URL Connection 4");
        conn.setRequestMethod("POST");
        functionCalls.LogStatus("URL Connection 5");
        conn.setDoInput(true);
        functionCalls.LogStatus("URL Connection 6");
        conn.setDoOutput(true);
        functionCalls.LogStatus("URL Connection 7");

        OutputStream os = conn.getOutputStream();
        functionCalls.LogStatus("URL Connection 8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        functionCalls.LogStatus("URL Connection 9");
        writer.write(getPostDataString(datamap));
        functionCalls.LogStatus("URL Connection 10");
        writer.flush();
        functionCalls.LogStatus("URL Connection 11");
        writer.close();
        functionCalls.LogStatus("URL Connection 12");
        os.close();
        functionCalls.LogStatus("URL Connection 13");
        int responseCode = conn.getResponseCode();
        functionCalls.LogStatus("URL Connection 14");
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            functionCalls.LogStatus("URL Connection 15");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            functionCalls.LogStatus("URL Connection 16");
            while ((line = br.readLine()) != null) {
                response += line;
            }
            functionCalls.LogStatus("URL Connection 17");
        } else {
            response = "";
            functionCalls.LogStatus("URL Connection 18");
        }
        functionCalls.LogStatus("URL Connection Response: " + response);
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            functionCalls.LogStatus(result.toString());
        }

        return result.toString();
    }
}


