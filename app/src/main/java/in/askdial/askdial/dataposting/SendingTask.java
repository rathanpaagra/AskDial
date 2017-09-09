package in.askdial.askdial.dataposting;

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

import in.askdial.askdial.values.FunctionCalls;

import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Automotive;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Camera_dealers;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Education;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Electronics;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Entertainment;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Food;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Furnitures;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Hotels;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Movie;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Packers_movers;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Property;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Real_estate;
import static in.askdial.askdial.dataposting.DataApi.CATEGORIES_URL_Shopping;
import static in.askdial.askdial.dataposting.DataApi.GET_CATEGORY_AUTOSUGGEST;
import static in.askdial.askdial.dataposting.DataApi.GET_CITY;
import static in.askdial.askdial.dataposting.DataApi.VIEW_ALL_CATEGORIES_URL;
import static in.askdial.askdial.dataposting.DataApi.VIEW_ALL_CLASSIFIEDS_CATEGORIES;

/**
 * Created by Admin on 30-Dec-16.
 */

public class SendingTask {

    FunctionCalls functionCalls = new FunctionCalls();

   // String CATEGORIES_URL = VIEW_ALL_CATEGORIES_URL;
    String DETAIL_LISTINGS_URL = DataApi.LISTINGS_DETAILS_URL;
    String BASE_URL_SEARCH=DataApi.BASE_URL;
    String CAT_LISTINGS_ALL=DataApi.CAT_LISTINGS_ALL;
    String SendCategory;

    //Requesting for all Categories
    public String GetFields() {
        String response = "";
         //HashMap<String, String> categoryName = new HashMap<>();
        // adding each child node to HashMap key => value
       // categoryName.put("first_level_category_id", first_level_category_id);
        try {
            response = UrlGetConnection(VIEW_ALL_CATEGORIES_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //
    //Requesting for all ClassifiedsCategories
    public String GetClassifieds() {
        String response = "";
        //HashMap<String, String> categoryName = new HashMap<>();
        // adding each child node to HashMap key => value
        // categoryName.put("first_level_category_id", first_level_category_id);
        try {
            response = UrlGetConnection(VIEW_ALL_CLASSIFIEDS_CATEGORIES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    //autocomplete textview search keyword
    public String GetSearch(String search) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("search", search);
        try {
            response = UrlPostConnection("Staff1", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //calling city name and id
    public String GetCityName(String search) {
        String response = "";
        try {
            response = UrlGetConnection(GET_CITY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
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

    //calling city name and id
    public String GetAutoSuggestionCategories(String search) {
        String response = "";
        try {
            response = UrlGetConnection(GET_CATEGORY_AUTOSUGGEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

//Most Visited Categories Recived List(Sending By Category Name)
    public String sendbyCategory(String cat) {

        String category= cat;
        //most searched categories
        if(category.equals("Food")){
            SendCategory= CATEGORIES_URL_Food;
        }else if(category.equals("Automotive")){
            SendCategory= CATEGORIES_URL_Automotive;
        }else if (category.equals("Property")){
            SendCategory= CATEGORIES_URL_Property;
        }else if(category.equals("Shopping")){
            SendCategory= CATEGORIES_URL_Shopping;
        }else if (category.equals("Movie")){
            SendCategory= CATEGORIES_URL_Movie;
        }
        //search categories
        else if(category.equals("Camera Dealers")){
            SendCategory= CATEGORIES_URL_Camera_dealers;
        }else if (category.equals("Electronics")){
            SendCategory= CATEGORIES_URL_Electronics;
        }else if(category.equals("Education")){
            SendCategory= CATEGORIES_URL_Education;
        }else if (category.equals("Realestate")){
            SendCategory= CATEGORIES_URL_Real_estate;
        }else if(category.equals("Packers and Movers")){
            SendCategory= CATEGORIES_URL_Packers_movers;
        }else if (category.equals("Hotels")){
            SendCategory= CATEGORIES_URL_Hotels;
        }else if(category.equals("Entertainment")){
            SendCategory= CATEGORIES_URL_Entertainment;
        }else if (category.equals("Furnitures")){
            SendCategory= CATEGORIES_URL_Furnitures;
        }
        String response = "";
        try {
            response = UrlGetConnection(SendCategory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //Sending Category id to get Listing Details
    public String sendCategoryName_ById(String cat_id) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("first_level_category_id", cat_id);

        try {
            response = UrlPostConnection2("", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //Sending Classfied Category ID  to get Classified Listing Details
    public String sendClassifiedCategoryName_ById(String cat_id) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("classifieds_category_id", cat_id);

        try {
            response = UrlPostConnection("Classifieds", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    //senging listing by id
    public String sendListing_id(String listing_id) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("listing_id", listing_id);

        try {
            response = UrlPostConnection1("", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    //senging searched keyword and city id,area too
    public String sendKeywordcity(String keyword, String cityID,String area) {
        String response = "";
        HashMap<String, String> datamap = new HashMap<>();
        datamap.put("keywords", keyword);
        datamap.put("city_id", cityID);
        datamap.put("company_area", area);

        try {
            response = UrlPostConnection("Search1", datamap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
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

   /* private String UrlGetConnection(String Get_Url) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + VIEW_ALL_CATEGORIES_URL + Get_Url);
        URL url = new URL(VIEW_ALL_CATEGORIES_URL + Get_Url);
        functionCalls.LogStatus("URL Get Connection 1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        functionCalls.LogStatus("URL Get Connection 2");
        conn.setReadTimeout(15000);
        functionCalls.LogStatus("URL Get Connection 3");
        conn.setConnectTimeout(15000);
        functionCalls.LogStatus("URL Get Connection 4");
        int responseCode = conn.getResponseCode();
        functionCalls.LogStatus("URL Get Connection 5");
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            functionCalls.LogStatus("URL Get Connection 6");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            functionCalls.LogStatus("URL Get Connection 7");
            while ((line = br.readLine()) != null) {
                response += line;
            }
            functionCalls.LogStatus("URL Get Connection 8");
        } else {
            response = "";
            functionCalls.LogStatus("URL Get Connection 9");
        }
        functionCalls.LogStatus("URL Get Connection Response: " + response);
        return response;
    }*/

    private String UrlGetConnection(String Get_Url) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + Get_Url);
        URL url = new URL(Get_Url);
        functionCalls.LogStatus("URL Get Connection 1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        functionCalls.LogStatus("URL Get Connection 2");
        conn.setReadTimeout(15000);
        functionCalls.LogStatus("URL Get Connection 3");
        conn.setConnectTimeout(15000);
        functionCalls.LogStatus("URL Get Connection 4");
        int responseCode = conn.getResponseCode();
        functionCalls.LogStatus("URL Get Connection 5");
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            functionCalls.LogStatus("URL Get Connection 6");
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            functionCalls.LogStatus("URL Get Connection 7");
            while ((line = br.readLine()) != null) {
                response += line;
            }
            functionCalls.LogStatus("URL Get Connection 8");
        } else {
            response = "";
            functionCalls.LogStatus("URL Get Connection 9");
        }
        functionCalls.LogStatus("URL Get Connection Response: " + response);
        return response;
    }

    //used to get ALL Category Listing Details By sending Category ID through API
    private String UrlPostConnection2(String Post_Url, HashMap<String, String> datamap) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + CAT_LISTINGS_ALL + Post_Url);
        URL url = new URL(CAT_LISTINGS_ALL + Post_Url);
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


    //only for  getting complete details of perticular Listings
    private String UrlPostConnection1(String Post_Url, HashMap<String, String> datamap) throws IOException {
        String response = "";
        functionCalls.LogStatus("Connecting URL: " + DETAIL_LISTINGS_URL + Post_Url);
        URL url = new URL(DETAIL_LISTINGS_URL + Post_Url);
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


}
