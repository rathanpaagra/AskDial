package in.askdial.askdial.fragments.categories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class Listing_Category_DetailsFragment extends Fragment {

    String list_id, list_Category_Name;
    Button button_back,button_call,button_location;
    TextView tv_comapny_name,tv_company_area,tv_company_city,tv_company_contact_person,tv_company_mobile1,tv_company_mobile2,tv_company_pincode,
            tv_company_address,tv_company_email,tv_company_website,tv_company_landline,tv_company_fax,tv_company_toll_free,tv_company_landmark;

    String s_comapny_name,s_company_area,s_company_city,s_company_contact_person,s_company_mobile1,s_company_mobile2,s_company_pincode,
            s_company_address,s_company_email,s_company_website,s_company_landline,s_company_fax,s_company_toll_free,s_company_landmark;

    LinearLayout linearLayout_company_area,linearLayout__company_contact_details,linearLayout_company_contact_person,
            linearLayout_company_mobile,linearLayout_company_adress,linearLayout_company_email,linearLayout_company_website,linearLayout_company_landline,
            linearLayout_company_fax,linearLayout_company_tollfree,linearLayout_company_landmark,linearLayout_company_pincode;

    FunctionCalls functionCalls = new FunctionCalls();
    String LISTINGS_URL = DataApi.LISTINGS_URL;
    Thread mythread;
    POJOValue pojoValue;
    ConnectingTask task;
    public Listing_Category_DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_listing__category__details, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        list_id=bundle.getString("listing_id");
        list_Category_Name=bundle.getString("listing_category_name");
        task = new ConnectingTask();
        pojoValue=new POJOValue();
        //comapany details
        //details to intent

       // new GetListings(list_id).execute();
        ConnectingTask.GetListings login = task.new GetListings(list_id, pojoValue);
        login.execute();
        mythread = null;
        Runnable runnable = new LoginTimer();
        mythread = new Thread(runnable);
        mythread.start();
        //Initialization
        tv_comapny_name= (TextView) view.findViewById(R.id.tv_listing_company_name);
        tv_company_area= (TextView) view.findViewById(R.id.tv_listing_company_area);
        tv_company_city= (TextView) view.findViewById(R.id.tv_listing_company_city);
        tv_company_email= (TextView) view.findViewById(R.id.tv_company_email);
        tv_company_contact_person= (TextView) view.findViewById(R.id.tv_contact_person);
        tv_company_mobile1= (TextView) view.findViewById(R.id.tv_contact_person_mobile1);
        tv_company_mobile2= (TextView) view.findViewById(R.id.tv_contact_person_mobile2);
        tv_company_pincode= (TextView) view.findViewById(R.id.tv_company_pincode);
        tv_company_address= (TextView) view.findViewById(R.id.tv_company_address);
        tv_company_website= (TextView) view.findViewById(R.id.tv_company_website);
        tv_company_landline= (TextView) view.findViewById(R.id.tv_company_landline);
        tv_company_fax= (TextView) view.findViewById(R.id.tv_company_fax);
        tv_company_toll_free= (TextView) view.findViewById(R.id.tv_company_tollfree);
        tv_company_landmark= (TextView) view.findViewById(R.id.tv_company_landmark);

        linearLayout_company_area= (LinearLayout) view.findViewById(R.id.ll_company_area);
        linearLayout__company_contact_details= (LinearLayout) view.findViewById(R.id.ll_company_contact_details);
        linearLayout_company_mobile= (LinearLayout) view.findViewById(R.id.ll_listing_mobile);
        linearLayout_company_contact_person= (LinearLayout) view.findViewById(R.id.ll_listing_contact_person);
        linearLayout_company_adress= (LinearLayout) view.findViewById(R.id.ll_listing_address);
        linearLayout_company_email= (LinearLayout) view.findViewById(R.id.ll_listing_email);
        linearLayout_company_website= (LinearLayout) view.findViewById(R.id.ll_listing_website);
        linearLayout_company_landline= (LinearLayout) view.findViewById(R.id.ll_listing_landline);
        linearLayout_company_fax= (LinearLayout) view.findViewById(R.id.ll_listing_fax);
        linearLayout_company_tollfree= (LinearLayout) view.findViewById(R.id.ll_listing_tollfree);
        linearLayout_company_landmark= (LinearLayout) view.findViewById(R.id.ll_listing_landmark);
        linearLayout_company_pincode= (LinearLayout) view.findViewById(R.id.ll_listing_pincode);

        /*tv_comapny_name.setText(s_comapny_name);
        tv_company_area.setText(s_company_area);
        tv_company_city.setText(s_company_city);
        tv_company_email.setText(s_company_email);
        tv_company_contact_person.setText(s_company_contact_person);
        tv_company_mobile1.setText(s_company_mobile1);
        tv_company_mobile2.setText(s_company_mobile2);
        tv_company_pincode.setText(s_company_pincode);
        tv_company_address.setText(s_company_address);
        tv_company_website.setText(s_company_website);
        tv_company_landline.setText(s_company_landline);
        tv_company_fax.setText(s_company_fax);
        tv_company_toll_free.setText(s_company_toll_free);
        tv_company_landmark.setText(s_company_landline);*/
        //set Text in intent

        return  view;
    }
    class LoginTimer implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doWork() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (pojoValue.isListingbyIdRecivedSuccess()) {
                        pojoValue.setListingbyIdRecivedSuccess(false);
                        Successview();
                        mythread.interrupt();
                    }
                    if (pojoValue.isListingbyIdRecivedFailure()) {
                        pojoValue.setListingbyIdRecivedFailure(true);

                        mythread.interrupt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void Successview() {
        tv_comapny_name.setText(pojoValue.getCompany_name());
        tv_company_area.setText(pojoValue.getCompany_area());
        tv_company_city.setText(pojoValue.getCompany_city());
        tv_company_email.setText(pojoValue.getCompany_email());
        tv_company_contact_person.setText(pojoValue.getCompany_contact_person());
        tv_company_mobile1.setText(pojoValue.getCategory_mobile1());
        tv_company_mobile2.setText(pojoValue.getCompany_mobile2());
        tv_company_pincode.setText(pojoValue.getCompany_pincode());
        tv_company_address.setText(pojoValue.getCompany_address());
        tv_company_website.setText(pojoValue.getCompany_website());
        tv_company_landline.setText(pojoValue.getCompany_landline());
        tv_company_fax.setText(pojoValue.getCompany_fax());
        tv_company_toll_free.setText(pojoValue.getCompany_toll_free());
        tv_company_landmark.setText(pojoValue.getCompany_landline());
    }
   /* public class GetListings extends AsyncTask<String, String, String> {
        String result = "";
        String listing_id;
        POJOValue details;


        public GetListings(String Listing_id*//*, POJOValue details*//*) {
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
            ReciveListingDetails(result*//*, details*//*);
        }
    }*/

   /* public String sendListing_id(String listing_id) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("listing_id", listing_id);

        try {
            response = UrlPostConnection("", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }*/

    /*public void ReciveListingDetails(String result*//*, POJOValue details*//*) {
        // HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        //   String jsonStr = sh.makeServiceCall(CATEGORIES_URL);
        //  Log.e(TAG, "Response from url: " + jsonStr);

        try {
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject bundle = ja.getJSONObject(i);
                if (bundle != null) {
                    String Status = bundle.getString("message");
                    //  pojoValue.setMessageSuccess(true);
                    if (Status.equals("Success")) {
                        Log.e(TAG, "Connect for fetching from server.");
                        s_comapny_name=bundle.getString("company_name");
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
                        s_company_address=bundle.getString("company_address");
                        *//*
                       // details = new POJOValue();

                        *//**//*tv_comapny_name = jo.getString("company_name");
                        details.setCompany_name(tv_comapny_name);
                        tv_company_area = jo.getString("company_area");
                        details.setCompany_name(tv_company_area);
                        tv_company_email = jo.getString("company_email");
                        details.setCompany_email(tv_company_email);
                        tv_company_mobile1 = jo.getString("category_mobile1");
                        details.setCompany_mobile1(tv_company_mobile1);*//**//*

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
                        details.setCompany_address(tv_company_address);*//*

                    }
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }*/

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

}
