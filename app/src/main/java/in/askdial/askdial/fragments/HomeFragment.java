package in.askdial.askdial.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import in.askdial.askdial.R;
import in.askdial.askdial.adapter.MainFragmentAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.dataposting.HttpHandler;
import in.askdial.askdial.fragments.categories.Visited_CatgFragment;
import in.askdial.askdial.services.SearchServices;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    public static final String PREFS_NAME = "MyPrefsFile";

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    View view;
    private String TAG = HomeFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    private ImageView imaView1, imaView2, imaView3, imaView4;

    RecyclerView recyclerView;
    ConnectingTask task = new ConnectingTask();
    POJOValue pojoValue = new POJOValue();
    String contextview;
    Context context;
    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView.LayoutManager layoutManager;
    MainFragmentAdapter mainFragmentAdapter;

    // URL to get contacts JSON
    String BASE_URL = DataApi.CATEGORIES_URL;
    ArrayList<HashMap<String, String>> categoryList;
    SliderLayout sliderLayout;
    HashMap<String, String> Hash_file_maps;
    HashMap<String, Integer> file_maps;

    //search Implementation
    AutoCompleteTextView search_autocomplete;
    String search_txtview;
    ArrayAdapter<String> Searchadapter;
    ArrayList<String> searchlist;
    FunctionCalls functionCalls;
    Toolbar toolbar;
    SearchServices searchService;

    //Social Media Links
    Button facebok, twitter, googleplus, linkedin, pintrest;

    //Most Visited Links
    Button button_properties, button_food, button_movie, button_automotive, button_shopping;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Home");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        checkInternetConnection();
        if (getArguments() != null) {

            Toast.makeText(getActivity(), getArguments().getString("message"), Toast.LENGTH_SHORT).show();
        }

        categoryList = new ArrayList<>();
        //checkInternetConnection();
        //isInternetOn(getActivity());

        //Initialization
        facebok = (Button) view.findViewById(R.id.btn_fb_icon);
        twitter = (Button) view.findViewById(R.id.btn_twitter_icon);
        googleplus = (Button) view.findViewById(R.id.btn_gplus_icon);
        linkedin = (Button) view.findViewById(R.id.btn_linked_in_icon);
        pintrest = (Button) view.findViewById(R.id.btn_pintrest_icon);

        //Most Visited Buttons Intialization

        button_properties = (Button) view.findViewById(R.id.btn_properties);
        button_food = (Button) view.findViewById(R.id.btn_Food);
        button_movie = (Button) view.findViewById(R.id.btn_Movie);
        button_automotive = (Button) view.findViewById(R.id.btn_Automotive);
        button_shopping = (Button) view.findViewById(R.id.btn_Shoping);

        button_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String properties = "Property";
                Visited_CatgFragment visited_catgFragment = new Visited_CatgFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle.putString("category", properties);
                visited_catgFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_main, visited_catgFragment).commit();
            }
        });
        button_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String properties = "Food";
                Visited_CatgFragment visited_catgFragment = new Visited_CatgFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle.putString("category", properties);
                visited_catgFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_main, visited_catgFragment).commit();
            }
        });
        button_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String properties = "Movie";
                Visited_CatgFragment visited_catgFragment = new Visited_CatgFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle.putString("category", properties);
                visited_catgFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_main, visited_catgFragment).commit();
            }
        });
        button_automotive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String properties = "Automotive";
                Visited_CatgFragment visited_catgFragment = new Visited_CatgFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle.putString("category", properties);
                visited_catgFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_main, visited_catgFragment).commit();
            }
        });
        button_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String properties = "Shopping";
                Visited_CatgFragment visited_catgFragment = new Visited_CatgFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                bundle.putString("category", properties);
                visited_catgFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container_main, visited_catgFragment).commit();
            }
        });


        //fallow us on links browser view
        facebok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/AskDial/"));
                startActivity(browserIntent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/askdial"));
                startActivity(browserIntent);
            }
        });
        googleplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+Askdial"));
                startActivity(browserIntent);
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/ask-dial-limited"));
                startActivity(browserIntent);
            }
        });
        pintrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pinterest.com/askdial/"));
                startActivity(browserIntent);
            }
        });

        //slider Activity
        file_maps = new HashMap<String, Integer>();
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        functionCalls = new FunctionCalls();

        //footer = (Toolbar) view.findViewById(R.id.footer);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        //search fileds
        //search_autocomplete = (AutoCompleteTextView) toolbar.findViewById(R.id.search_EditText);
        //SearchFileds();
        file_maps.put("Rajalaxmi Group", R.drawable.rajalakshmi_logo);
        file_maps.put("Prajwal Marketing", R.drawable.prajwal_marketing);
        file_maps.put("Celebrate Parlours", R.drawable.celebrate_parlours);
        file_maps.put("Ascent Elevators", R.drawable.ascent_elevators);

        for (String name : file_maps.keySet()) {

            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(1500);
        sliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) this);

        //region for REcyclerview Using JSON
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_title1);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainFragmentAdapter = new MainFragmentAdapter(arrayList, contextview, getActivity());

        ConnectingTask.CategoryFields checkVisitors = task.new CategoryFields(arrayList, mainFragmentAdapter, pojoValue, context);
        checkVisitors.execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mainFragmentAdapter);
        //endregion

        //Category list checking task
        //new GetCategoriesList().execute();
        return view;
    }

    private class GetCategoriesList extends AsyncTask<Object, Object, ArrayList<HashMap<String, String>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
            // Toast.makeText(getActivity(), "Please Turn On Internet", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(Object... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(BASE_URL);
            Log.e(TAG, "Response from url: " + jsonStr);

            try {
                JSONArray ja = new JSONArray(jsonStr);
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    if (jo != null) {
                        String Status = jo.getString("message");
                        if (Status.equals("Success")) {
                            Log.e(TAG, "Connect for fetching from server.");
                            String name = jo.getString("first_level_category_name");

                            // String id= jo.getString("first_level_category_id");
                            HashMap<String, String> categoryName = new HashMap<>();
                            // adding each child node to HashMap key => value
                            categoryName.put("name", name);
                            // categoryName.put("id", id);
                            // categoryName.put("id", jsonArray.getString(Integer.parseInt("first_level_category_id")));
                            // adding contact to contact list
                            categoryList.add(categoryName);
                        }
                    }
                }

            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return categoryList;
        }


        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), categoryList, R.layout.categorylist_view, new String[]{"name"}, new int[]{R.id.name});

            lv.setAdapter(adapter);
        }
    }

    public void SearchFileds() {
        functionCalls.LogStatus("Staff field Started");
        HashSet<String> StaffSet = new HashSet<>();
        StaffSet = searchService.searchset;
        searchlist = new ArrayList<>();
        searchlist.addAll(StaffSet);
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String liststaff = list.get(i);
            String staff = liststaff.substring(0, liststaff.lastIndexOf(','));
            String staffid = liststaff.substring(liststaff.lastIndexOf(',') + 1, liststaff.length());
            searchlist.add(staff);
            // listid.put(staff.toLowerCase(), staffid);
        }

        if (searchlist.size() > 0) {
            functionCalls.LogStatus("Staff list Available");
            Searchadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, searchlist);
            search_autocomplete.setAdapter(Searchadapter);
            Collections.sort(searchlist);
            Searchadapter.notifyDataSetChanged();
            search_autocomplete.setThreshold(1);
            search_autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    search_txtview = parent.getItemAtPosition(position).toString();
                    // StaffTomeetId = listid.get(parent.getItemAtPosition(position).toString().toLowerCase());
                }
            });

        } else {
            functionCalls.LogStatus("Staff list not Available");
        }
    }


    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            /*new loadListView().execute();*/
            return true;
        } else {
            Toast.makeText(getActivity(), "Internet Connection Required", Toast.LENGTH_LONG).show();


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            getActivity();
                            //System.exit(0);
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return false;
    }

    public final boolean isInternetOn(FragmentActivity activity) {
        ConnectivityManager connect = (ConnectivityManager) activity.getSystemService(activity.getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connect.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true)
            return true;
        else return false;
    }

    //slider for image
    @Override
    public void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        slider.getBundle().getStringArrayList(String.valueOf(R.drawable.app_ico10));

        String s = (String) slider.getBundle().get("extra");
        if (s.equals("Rajalaxmi Group")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/Rajalakshmi/"));
            startActivity(browserIntent);
        } else if (s.equals("Prajwal Marketing")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/prajwal/"));
            startActivity(browserIntent);
        } else if (s.equals("Celebrate Parlours")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/celebratelife/"));
            startActivity(browserIntent);
        } else if (s.equals("Ascent Elevators")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/ascent/"));
            startActivity(browserIntent);
        }
       /* else if(s.equals("1")){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/prajwal/"));
            startActivity(browserIntent);
        }*/
        //  Toast.makeText(getActivity(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


}

//region JSON using Listview
            /*HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {


                try {
                   JSONArray jsonArray = new JSONArray(jsonStr);
                    JSONObject message = jsonArray.getJSONObject(Integer.parseInt("message"));
                    if (message.equals("Success")) {

                        String name = message.getString("first_level_category_name");
                        HashMap<String, String> categoryName = new HashMap<>();
                        // adding each child node to HashMap key => value
                        categoryName.put("name", name);
                        // categoryName.put("id", jsonArray.getString(Integer.parseInt("first_level_category_id")));
                        // adding contact to contact list
                        categoryList.add(categoryName);
                    } else {
                        Toast.makeText(getActivity(), "No Input Found", Toast.LENGTH_SHORT).show();
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;*/

/*

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            */
/**
 * Updating parsed JSON data into ListView
 *//*

            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), categoryList, R.layout.categorylist_view, new String[]{"name"}, new int[]{R.id.name});

            lv.setAdapter(adapter);
        }

    }
*/
//endregion

//region list view and onclick browser display

// lv = (ListView) view.findViewById(R.id.list_view);

//onclick Intent to webview
        /*imaView1 = (ImageView) view.findViewById(R.id.imgView1);
        imaView2 = (ImageView) view.findViewById(R.id.imgView2);
        imaView3 = (ImageView) view.findViewById(R.id.imgView3);
        imaView4 = (ImageView) view.findViewById(R.id.imgView4);

        imaView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/Rajalakshmi/"));
                startActivity(browserIntent);
            }
        });
        imaView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/prajwal/"));
                startActivity(browserIntent);
            }
        });
        imaView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/celebratelife/"));
                startActivity(browserIntent);
            }
        });
        imaView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.askdial.com/ascent/"));
                startActivity(browserIntent);
            }
        });*/
//endregion

