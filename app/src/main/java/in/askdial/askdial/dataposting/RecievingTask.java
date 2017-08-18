package in.askdial.askdial.dataposting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

import in.askdial.askdial.adapter.MainFragmentAdapter;
import in.askdial.askdial.adapter.MasterFragmentAdapter;
import in.askdial.askdial.adapter.ViewdCategoryAdapter;
import in.askdial.askdial.values.POJOValue;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 30-Dec-16.
 */

public class RecievingTask {

    String CATEGORIES_URL = DataApi.VIEW_ALL_CATEGORIES_URL;
    String LISTINGS_URL = DataApi.LISTINGS_DETAILS_URL;
    POJOValue pojoValue;


    public void CategoryDetails(String result, POJOValue pojoValue, ArrayList<POJOValue> arrayList,
                                MainFragmentAdapter adapters) {
        // HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        //   String jsonStr = sh.makeServiceCall(VIEW_ALL_CATEGORIES_URL);
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

    //getSearch
    public void GetSearchDetails(String result, POJOValue details, HashSet<String> hashSet) {
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                if (jo != null) {
                    String message = jo.getString("message");
                    if (message.equals("Success")) {
                        String Staff = jo.getString("staff_name");
                        String StaffId = jo.getString("staff_id");
                        // list.add(Staff);
                        list.add(Staff/*+","+StaffId*/);
                        if (i == (ja.length()-1)) {
                            details.setSearchExists(true);
                            // hashSet.addAll(list);
                            hashSet.addAll(list);
                        }
                    } else {
                        details.setNoSearchfExist(true);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //get Single Listing details
    public void ListingDetails(String result, POJOValue pojoValue, ArrayList<POJOValue> arrayList,
                                MasterFragmentAdapter adapters) {
        // HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        //   String jsonStr = sh.makeServiceCall(VIEW_ALL_CATEGORIES_URL);
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
                        String Company_listingId = jo.getString("listing_id");
                        pojoValue.setCompany_lisiting_id(Company_listingId);
                        String Company_listing_categoryname = jo.getString("first_level_category_name");
                        pojoValue.setCompany_category_name(Company_listing_categoryname);
                        String Company_Name = jo.getString("company_name");
                        pojoValue.setCompany_name(Company_Name);
                        String Company_Area = jo.getString("company_area");
                        pojoValue.setCompany_area(Company_Area);
                        String Company_Mobile = jo.getString("category_mobile1");
                        pojoValue.setCompany_mobile1(Company_Mobile);
                        String Company_Email = jo.getString("company_email");
                        pojoValue.setCompany_email(Company_Email);
                        arrayList.add(pojoValue);
                        adapters.notifyDataSetChanged();
                    }
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    //get ALL Listing details by sending Category ID
    public void ByCategoryListingDetails(String result, POJOValue pojoValue, ArrayList<POJOValue> arrayList,
                                         ViewdCategoryAdapter adapters) {
        // HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        //   String jsonStr = sh.makeServiceCall(VIEW_ALL_CATEGORIES_URL);
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
                        String Company_listingId = jo.getString("listing_id");
                        pojoValue.setCompany_lisiting_id(Company_listingId);
                        String Company_listing_categoryname = jo.getString("first_level_category_name");
                        pojoValue.setCompany_category_name(Company_listing_categoryname);
                        String Company_Name = jo.getString("company_name");
                        pojoValue.setCompany_name(Company_Name);
                        String Company_Area = jo.getString("company_area");
                        pojoValue.setCompany_area(Company_Area);
                        String Company_Mobile = jo.getString("category_mobile1");
                        pojoValue.setCompany_mobile1(Company_Mobile);
                        String Company_Email = jo.getString("company_email");
                        pojoValue.setCompany_email(Company_Email);
                        arrayList.add(pojoValue);
                        adapters.notifyDataSetChanged();
                    }
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    //Recieve All Listing Details by sending Listing ID
    public void ReciveListingDetails(String result, POJOValue details) {

        try {
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                if (jo != null) {
                    String Status = jo.getString("message");
                    //  pojoValue.setMessageSuccess(true);
                    if (Status.equals("Success")) {
                        Log.e(TAG, "Connect for fetching from server.");

                        //details = new POJOValue();
                        details.setListingbyIdRecivedSuccess(true);

                        String tv_comapny_name = jo.getString("company_name");
                        details.setCompany_name(tv_comapny_name);
                        String tv_company_area = jo.getString("company_area");
                        details.setCompany_area(tv_company_area);
                        String tv_company_email = jo.getString("company_email");
                        details.setCompany_email(tv_company_email);

                        String tv_company_mobile1 = jo.getString("category_mobile1");
                        details.setCompany_mobile1(tv_company_mobile1);
                        String tv_company_website = jo.getString("category_website");
                        details.setCompany_website(tv_company_website);
                        String  tv_company_mobile2 = jo.getString("category_mobile2");
                        details.setCompany_mobile2(tv_company_mobile2);
                        String tv_company_contact_person = jo.getString("category_contact_person");
                        details.setCompany_contact_person(tv_company_contact_person);
                        String tv_company_landline = jo.getString("category_landline");
                        details.setCompany_landline(tv_company_landline);
                        String tv_company_fax = jo.getString("category_fax");
                        details.setCompany_fax(tv_company_fax);
                        String  tv_company_toll_free = jo.getString("category_tollfree");
                        details.setCompany_toll_free(tv_company_toll_free);
                        String tv_company_city = jo.getString("city_name");
                        details.setCompany_city(tv_company_city);
                        String tv_company_landmark = jo.getString("company_landmark");
                        details.setCompany_landmark(tv_company_landmark);
                        String tv_company_pincode = jo.getString("category_pincode");
                        details.setCompany_pincode(tv_company_pincode);
                        String tv_company_address = jo.getString("company_address");
                        details.setCompany_address(tv_company_address);


                      /*  s_comapny_name=bundle.getString("company_name");
                        s_company_email=bundle.getString("company_email");
                        s_company_website=bundle.getString("category_website");
                        s_company_mobile1=bundle.getString("category_mobile1");
                        s_company_mobile2=bundle.getString("category_mobile2");
                        s_company_contact_person=bundle.getString("category_contact_person");
                        s_company_landline=bundle.getString("category_landline");
                        s_company_fax=bundle.getString("category_fax");
                        s_company_toll_free=bundle.getString("category_tollfree");
                        s_company_area=bundle.getString("company_area");
                        s_company_city=bundle.getString("city_name");
                        s_company_landmark=bundle.getString("company_landmark");
                        s_company_pincode=bundle.getString("category_pincode");
                        s_company_address=bundle.getString("company_address");*/
                    }else {

                    details.setListingbyIdRecivedFailure(true);
                }
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }
}
