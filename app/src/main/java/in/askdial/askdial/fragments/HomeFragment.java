package in.askdial.askdial.fragments;


import android.app.Activity;
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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import in.askdial.askdial.R;
import in.askdial.askdial.adapter.MainFragmentAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.dataposting.HttpHandler;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String PREFS_NAME = "MyPrefsFile";

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    View view;
    private String TAG = HomeFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    private ImageView imaView1, imaView2, imaView3;

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

        lv = (ListView) view.findViewById(R.id.list_view);

        imaView1 = (ImageView) view.findViewById(R.id.imgView1);
        imaView2 = (ImageView) view.findViewById(R.id.imgView2);
        imaView3 = (ImageView) view.findViewById(R.id.imgView3);

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
        });

        //region for REcyclerview Using JSON
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_title1);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);

        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainFragmentAdapter = new MainFragmentAdapter(arrayList, contextview, getActivity());

        ConnectingTask.CategoryFields checkVisitors = task.new CategoryFields(arrayList, mainFragmentAdapter, pojoValue, context);
        checkVisitors.execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mainFragmentAdapter);
        //endregion
        new GetCategoriesList().execute();
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


