package in.askdial.askdial.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.fragments.categories.Listing_Category_DetailsFragment;
import in.askdial.askdial.fragments.categories.Visited_CatgFragment;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 09-Aug-17.
 */

public class MasterFragmentAdapter extends RecyclerView.Adapter<MasterFragmentAdapter.MasterFragmentViewHolder> {
    ArrayList<POJOValue> arrayList = new ArrayList<>();
    Visited_CatgFragment visitedCatgFragment;
    String ContextView;
    Context context;
    Activity activity;
    String SendCategory_listing_id;
    String LISTINGS_URL = DataApi.LISTINGS_DETAILS_URL;

    FunctionCalls functionCalls = new FunctionCalls();

    String lisiting_id, tv_comapny_name, tv_company_area, tv_company_city, tv_company_contact_person, tv_company_mobile1, tv_company_mobile2, tv_company_pincode,
            tv_company_address, tv_company_email, tv_company_website, tv_company_landline, tv_company_fax, tv_company_toll_free, tv_company_landmark;


    public MasterFragmentAdapter(ArrayList<POJOValue> arrayList, String contextView, Context context, Visited_CatgFragment visited_catgFragment) {
        this.arrayList = arrayList;
        this.context = context;
        this.ContextView = contextView;
        activity = (Activity) context;
        this.visitedCatgFragment = visited_catgFragment;
    }

    @Override
    public MasterFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        MasterFragmentViewHolder viewHolder = new MasterFragmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MasterFragmentViewHolder holder, int position) {
        POJOValue pojoValue = arrayList.get(position);
        holder.textView_company_name.setText(pojoValue.getCompany_name());
        holder.textView_company_area.setText(pojoValue.getCompany_area());
        holder.textView_company_mobile.setText(pojoValue.getCompany_mobile1());
        holder.textView_company_email.setText(pojoValue.getCompany_email());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MasterFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_company_name, textView_company_area, textView_company_mobile, textView_company_email;

        public MasterFragmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView_company_name = (TextView) itemView.findViewById(R.id.textview_company_name);
            textView_company_area = (TextView) itemView.findViewById(R.id.textview_company_area);
            textView_company_mobile = (TextView) itemView.findViewById(R.id.textview_company_mobile);
            textView_company_email = (TextView) itemView.findViewById(R.id.textview_company_email);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            POJOValue content = arrayList.get(pos);
            lisiting_id = content.getCompany_lisiting_id();

            //Parsing listingID to get Listing Details
            //new GetListings(lisiting_id, content).execute();
            String lisiting_category_name = content.getCompany_category_name();
            tv_comapny_name=content.getCompany_name();
            tv_company_email=content.getCompany_area();
            tv_company_mobile1=content.getCompany_mobile1();
            tv_company_email=content.getCompany_email();

            Listing_Category_DetailsFragment fragment = new Listing_Category_DetailsFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = visitedCatgFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("listing_id", lisiting_id);
            bundle.putString("listing_category_name", lisiting_category_name);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();

            //region intenting all fields (right now not using it)
            //details to intent
           /* bundle.putString("company_name", tv_comapny_name);
            bundle.putString("company_email", tv_company_email);
            bundle.putString("category_website", tv_company_website);
            bundle.putString("category_mobile1", tv_company_mobile1);
            bundle.putString("category_mobile2", tv_company_mobile2);
            bundle.putString("category_contact_person", tv_company_contact_person);
            bundle.putString("category_landline", tv_company_landline);
            bundle.putString("category_fax", tv_company_fax);
            bundle.putString("category_tollfree", tv_company_toll_free);
            bundle.putString("company_area", tv_company_area);
            bundle.putString("city_name", tv_company_city);
            bundle.putString("company_landmark", tv_company_landmark);
            bundle.putString("category_pincode", tv_company_pincode);
            bundle.putString("company_address", tv_company_address);*/
            //endregion

        }
    }


    //region for parsing listing ID to get the listing details (right now not using it)
    public class GetListings extends AsyncTask<String, String, String> {
        String result = "";
        String listing_id;
        POJOValue details;


        public GetListings(String Listing_id, POJOValue details) {
            listing_id = Listing_id;
            this.details = details;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendListing_id(listing_id);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            ReciveListingDetails(result, details);
        }
    }

    public String sendListing_id(String listing_id) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("listing_id", listing_id);

        try {
            response = UrlPostConnection("", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void ReciveListingDetails(String result, POJOValue details) {
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
                        details = new POJOValue();

                        /*tv_comapny_name = jo.getString("company_name");
                        details.setCompany_name(tv_comapny_name);
                        tv_company_area = jo.getString("company_area");
                        details.setCompany_name(tv_company_area);
                        tv_company_email = jo.getString("company_email");
                        details.setCompany_email(tv_company_email);
                        tv_company_mobile1 = jo.getString("category_mobile1");
                        details.setCompany_mobile1(tv_company_mobile1);*/
                        tv_company_website = jo.getString("category_website");
                        details.setCompany_website(tv_company_website);
                        tv_company_mobile2 = jo.getString("category_mobile2");
                        details.setCompany_mobile2(tv_company_mobile2);
                        tv_company_contact_person = jo.getString("category_contact_person");
                        details.setCompany_contact_person(tv_company_contact_person);
                        tv_company_landline = jo.getString("category_landline");
                        details.setCompany_landline(tv_company_landline);
                        tv_company_fax = jo.getString("category_fax");
                        details.setCompany_fax(tv_company_fax);
                        tv_company_toll_free = jo.getString("category_tollfree");
                        details.setCompany_toll_free(tv_company_toll_free);
                        tv_company_city = jo.getString("city_name");
                        details.setCompany_city(tv_company_city);
                        tv_company_landmark = jo.getString("company_landmark");
                        details.setCompany_landmark(tv_company_landmark);
                        tv_company_pincode = jo.getString("category_pincode");
                        details.setCompany_pincode(tv_company_pincode);
                        tv_company_address = jo.getString("company_address");
                        details.setCompany_address(tv_company_address);
                    }
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    private String UrlPostConnection(String Post_Url, HashMap<String, String> datamap) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + LISTINGS_URL + Post_Url);
        URL url = new URL(LISTINGS_URL + Post_Url);
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
    //endregion
}
