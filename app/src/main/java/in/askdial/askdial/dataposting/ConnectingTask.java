package in.askdial.askdial.dataposting;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;

import in.askdial.askdial.adapter.MainFragmentAdapter;
import in.askdial.askdial.adapter.MasterFragmentAdapter;
import in.askdial.askdial.adapter.SearchedCategoryAdapter;
import in.askdial.askdial.adapter.ViewdCategoryAdapter;
import in.askdial.askdial.adapter.classifiedsAdapter.ClassifiedCategory_ListAdapter;
import in.askdial.askdial.adapter.classifiedsAdapter.ClassifiedsCategoryAdapter;
import in.askdial.askdial.adapter.eventsAdapter.All_EventsAdapter;
import in.askdial.askdial.fragments.classifieds.ClassifiedsCat_Listings;
import in.askdial.askdial.fragments.events.All_Events;
import in.askdial.askdial.values.POJOValue;

/**
 * Created by Admin on 30-Dec-16.
 */

public class ConnectingTask {
    SendingTask sendingTask = new SendingTask();
    RecievingTask recievingTask = new RecievingTask();


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show, Context context, final View mProgressBar) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressBar.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    //View All Categories list in Database
    public class CategoryFields extends AsyncTask<String, String, String> {
        String result = "", firstLevelCategory_id;
        ArrayList<POJOValue> arrayList;
        MainFragmentAdapter mainFragmentAdapter;
        POJOValue details;
        Context context;
        View Progressbar;
        private LayoutInflater mInflater;

        public CategoryFields(ArrayList<POJOValue> arrayList, MainFragmentAdapter mainFragmentAdapter, POJOValue detailsValue,
                              Context context, View progressbar) {
            this.arrayList = arrayList;
            this.details = detailsValue;
            this.mainFragmentAdapter = mainFragmentAdapter;
            this.details = detailsValue;
            Progressbar = progressbar;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true, context, Progressbar);
            // super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                result = sendingTask.GetFields();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showProgress(false, context, Progressbar);
            recievingTask.CategoryDetails(result, details, arrayList, mainFragmentAdapter);
        }
    }

    //All Classifieds List Here
    public class ClassifiedsCategoryFields extends AsyncTask<String, String, String> {
        String result = "", firstLevelCategory_id;
        ArrayList<POJOValue> arrayList;
        ClassifiedsCategoryAdapter mainFragmentAdapter;
        POJOValue details;
        Context context;
        View Progressbar;
        private LayoutInflater mInflater;

        public ClassifiedsCategoryFields(ArrayList<POJOValue> arrayList, ClassifiedsCategoryAdapter mainFragmentAdapter, POJOValue detailsValue,
                              Context context, View progressbar) {
            this.arrayList = arrayList;
            this.details = detailsValue;
            this.mainFragmentAdapter = mainFragmentAdapter;
            this.details = detailsValue;
            Progressbar = progressbar;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true, context, Progressbar);
            // super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                result = sendingTask.GetClassifieds();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showProgress(false, context, Progressbar);
            recievingTask.ClassifiedsCategoryDetails(result, details, arrayList, mainFragmentAdapter);
        }
    }

    //request for Clssified Categoty Listings
    //All Category Listing
    public class ClassifiedCat_Listings extends AsyncTask<String, String, String> {
        String result = "", firstLevelCategory_id, category_id;
        ArrayList<POJOValue> arrayList;
        ClassifiedCategory_ListAdapter classifiedCategory_listAdapter;
        POJOValue details;
        Context context;
        View Progressbar;
        private LayoutInflater mInflater;

        public ClassifiedCat_Listings(ArrayList<POJOValue> arrayList, String Category_id, ClassifiedCategory_ListAdapter mainFragmentAdapter, POJOValue detailsValue,
                              Context context, View progressbar) {
            this.arrayList = arrayList;
            this.details = detailsValue;
            this.category_id = Category_id;
            this.classifiedCategory_listAdapter = mainFragmentAdapter;
            this.details = detailsValue;
            Progressbar = progressbar;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true, context, Progressbar);
           /* super.onPreExecute();*/
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String Food = "Food";
                result = sendingTask.sendClassifiedCategoryName_ById(category_id);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showProgress(false, context, Progressbar);
            recievingTask.ByClassifiedCategoryListingDetails(result, details, arrayList, classifiedCategory_listAdapter);
        }
    }

    //request for all Classifieds listing Details
    //getListing by id
    public class GetClassifieds_Listings extends AsyncTask<String, String, String> {
        String result = "";
        String listing_id;
        POJOValue details;


        public GetClassifieds_Listings(String Listing_id, POJOValue details) {
            listing_id = Listing_id;
            this.details = details;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendingTask.sendClassifiedListing_id(listing_id);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            recievingTask.ReciveClassifiedListingDetails(result, details);
        }
    }

    //Search for all Categories
    public class SearchAll extends AsyncTask<String, String, String> {
        String result = "", SearchingName;
        POJOValue details;
        java.util.HashSet<String> HashSet;

        public SearchAll(String search, POJOValue detailsValue, HashSet<String> hashSet) {
            this.SearchingName = search;
            this.details = detailsValue;
            this.HashSet = hashSet;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                result = sendingTask.GetSearch(SearchingName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            recievingTask.GetSearchDetails(result, details, HashSet);
        }
    }

    //JSON for listing all events information
    //All Classifieds List Here
    public class AllEventsList extends AsyncTask<String, String, String> {
        String result = "", firstLevelCategory_id;
        ArrayList<POJOValue> arrayList;
        All_EventsAdapter all_eventsAdapter;
        POJOValue details;
        Context context;
        View Progressbar;
        private LayoutInflater mInflater;

        public AllEventsList(ArrayList<POJOValue> arrayList, All_EventsAdapter mainFragmentAdapter, POJOValue detailsValue,
                             Context context, View progressbar) {
            this.arrayList = arrayList;
            this.details = detailsValue;
            this.all_eventsAdapter = mainFragmentAdapter;
            this.details = detailsValue;
            Progressbar = progressbar;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true, context, Progressbar);
            // super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                result = sendingTask.GetEvents();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showProgress(false, context, Progressbar);
            recievingTask.ReciveEventsList(result, details, arrayList, all_eventsAdapter);
        }
    }

    //request for all Classifieds listing Details
    //getListing by event id
    public class Event_Listings extends AsyncTask<String, String, String> {
        String result = "";
        String listing_id;
        POJOValue details;


        public Event_Listings(String Listing_id, POJOValue details) {
            listing_id = Listing_id;
            this.details = details;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendingTask.sendEventListing_id(listing_id);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            recievingTask.ReciveEventDetails(result, details);
        }
    }


    //getcity list
    public class GetCitySearchServices extends AsyncTask<String, String, String> {
        String result = "", SearchingName;
        POJOValue details;
        java.util.HashSet<String> HashSet;

        public GetCitySearchServices(String search, POJOValue detailsValue, HashSet<String> hashSet) {
            this.SearchingName = search;
            this.details = detailsValue;
            this.HashSet = hashSet;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                //SearchingName="Search";
                result = sendingTask.GetCityName(SearchingName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            recievingTask.GetCitySearchDetails(result, details, HashSet);
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
                result = sendingTask.GetAreaName(SearchingName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            recievingTask.GetAreaSearchDetails(result, details, HashSet/*arrayList*/);
        }
    }

    //getall searching Categories list
    public class GetCategoriesSearchServices extends AsyncTask<String, String, String> {
        String result = "", SearchingName;
        POJOValue details;
        java.util.HashSet<String> HashSet;

        public GetCategoriesSearchServices(String search, POJOValue detailsValue, HashSet<String> hashSet) {
            this.SearchingName = search;
            this.details = detailsValue;
            this.HashSet = hashSet;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                //SearchingName="Search";
                result = sendingTask.GetAutoSuggestionCategories(SearchingName);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            recievingTask.GetCategoryAutosuggestDetails(result, details, HashSet);
        }
    }

    //All listings information
    //Single Listings
    public class SingleListingsFields extends AsyncTask<String, String, String> {
        String result = "", firstLevelCategory_id, category_name;
        ArrayList<POJOValue> arrayList;
        MasterFragmentAdapter masterFragmentAdapter;
        POJOValue details;
        Context context;
        View Progressbar;
        private LayoutInflater mInflater;

        public SingleListingsFields(ArrayList<POJOValue> arrayList, String Category, MasterFragmentAdapter mainFragmentAdapter, POJOValue detailsValue,
                                    Context context, View progressbar) {
            this.arrayList = arrayList;
            this.details = detailsValue;
            this.category_name = Category;
            this.masterFragmentAdapter = mainFragmentAdapter;
            this.details = detailsValue;
            Progressbar = progressbar;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true, context, Progressbar);
           /* super.onPreExecute();*/
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String Food = "Food";
                result = sendingTask.sendbyCategory(category_name);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showProgress(false, context, Progressbar);
            recievingTask.ListingDetails(result, details, arrayList, masterFragmentAdapter);
        }
    }

    //All Category Listing
    public class ListingsFields extends AsyncTask<String, String, String> {
        String result = "", firstLevelCategory_id, category_id;
        ArrayList<POJOValue> arrayList;
        ViewdCategoryAdapter masterFragmentAdapter;
        POJOValue details;
        Context context;
        View Progressbar;
        private LayoutInflater mInflater;

        public ListingsFields(ArrayList<POJOValue> arrayList, String Category_id, ViewdCategoryAdapter mainFragmentAdapter, POJOValue detailsValue,
                              Context context, View progressbar) {
            this.arrayList = arrayList;
            this.details = detailsValue;
            this.category_id = Category_id;
            this.masterFragmentAdapter = mainFragmentAdapter;
            this.details = detailsValue;
            Progressbar = progressbar;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true, context, Progressbar);
           /* super.onPreExecute();*/
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String Food = "Food";
                result = sendingTask.sendCategoryName_ById(category_id);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showProgress(false, context, Progressbar);
            recievingTask.ByCategoryListingDetails(result, details, arrayList, masterFragmentAdapter);
        }
    }

    //getListing by id
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
                result = sendingTask.sendListing_id(listing_id);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            recievingTask.ReciveListingDetails(result, details);
        }
    }

    //Search By keyword
    public class GetSearchedListings extends AsyncTask<String, String, String> {
        String result = "";
        String city_id, keywords, area_name;
        POJOValue details;
        ArrayList<POJOValue> arrayList;
        SearchedCategoryAdapter searchCategoryAdapter;
        View Progressbar;
        Context context;

        public GetSearchedListings(ArrayList<POJOValue> arrayList, String Keywords, String City_id,String Area_name, POJOValue details, SearchedCategoryAdapter SearchCategoryAdapter,
                                   Context context, View progressbar) {
            keywords = Keywords;
            city_id = City_id;
            area_name = Area_name;
            this.details = details;
            searchCategoryAdapter = SearchCategoryAdapter;
            Progressbar = progressbar;
            this.context = context;
            this.arrayList = arrayList;
        }

        @Override
        protected void onPreExecute() {
            showProgress(true, context, Progressbar);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                result = sendingTask.sendKeywordcity(keywords, city_id,area_name);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            showProgress(false, context, Progressbar);
            recievingTask.BySearchListingDetails(result, details, arrayList, searchCategoryAdapter);
        }
    }
}








