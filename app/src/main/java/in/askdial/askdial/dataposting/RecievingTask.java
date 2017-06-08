package in.askdial.askdial.dataposting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import in.askdial.askdial.adapter.MainFragmentAdapter;
import in.askdial.askdial.values.POJOValue;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 30-Dec-16.
 */

public class RecievingTask {

    String CATEGORIES_URL = DataApi.CATEGORIES_URL;
    String LISTINGS_URL = DataApi.LISTINGS_URL;
    POJOValue pojoValue;


    public void CategoryDetails(String result, POJOValue pojoValue, ArrayList<POJOValue> arrayList,
                                MainFragmentAdapter adapters) {
        // HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        //   String jsonStr = sh.makeServiceCall(CATEGORIES_URL);
        //  Log.e(TAG, "Response from url: " + jsonStr);

        try {
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                if (jo != null) {
                    String Status = jo.getString("message");
                    //  pojoValue.setMessageSuccess(true);
                    if (Status.equals("Success")) {
                        Log.e(TAG, "Connect for fetching from server.");

                        pojoValue.setCategory_found(true);
                        pojoValue = new POJOValue();

                        String Category_Name = jo.getString("first_level_category_name");
                        pojoValue.setFirst_Level_Category_Name(Category_Name);

                        String Category_id = jo.getString("first_level_category_id");
                        pojoValue.setFirst_Level_Category_Id(Category_id);
                        arrayList.add(pojoValue);

                       adapters.notifyDataSetChanged();
                        // String name = jo.getString("first_level_category_name");

                      /*  // String id= jo.getString("first_level_category_id");
                        HashMap<String, String> categoryName = new HashMap<>();
                        // adding each child node to HashMap key => value
                        categoryName.put("name", name);
                        // categoryName.put("id", id);
                        // categoryName.put("id", jsonArray.getString(Integer.parseInt("first_level_category_id")));
                        // adding contact to contact list*/
                    }
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }


}
