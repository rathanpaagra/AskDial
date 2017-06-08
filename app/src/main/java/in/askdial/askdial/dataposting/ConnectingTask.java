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

    public class CategoryFields extends AsyncTask<String, String, String> {
        String result = "", firstLevelCategory_id;
        ArrayList<POJOValue> arrayList;
        MainFragmentAdapter mainFragmentAdapter;
        POJOValue details;
        Context context;
        private LayoutInflater mInflater;

        public CategoryFields(ArrayList<POJOValue> arrayList, MainFragmentAdapter mainFragmentAdapter, POJOValue detailsValue,
                             Context context) {
           this.arrayList=arrayList;
            this.details = detailsValue;
            this.mainFragmentAdapter = mainFragmentAdapter;
            this.details = detailsValue;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
            recievingTask.CategoryDetails(result, details, arrayList,mainFragmentAdapter);
        }
    }

}








