package in.askdial.askdial.fragments.search;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wang.avi.AVLoadingIndicatorView;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    String keywords="Cars",city_id="47";
    EditText search_keyword;

    FunctionCalls functionCalls = new FunctionCalls();
    String LISTINGS_URL = DataApi.LISTINGS_DETAILS_URL;
    Thread mythread;
    POJOValue pojoValue;
    ConnectingTask task;
    static ProgressDialog dialog = null;
    private AVLoadingIndicatorView progressBar;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);

        task = new ConnectingTask();
        pojoValue=new POJOValue();

        search_keyword= (EditText) view.findViewById(R.id.search_textview1);

        //progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.loading_bar);

        ConnectingTask.GetSearchedListings login = task.new GetSearchedListings(keywords,city_id, pojoValue);
        login.execute();
        //progressBar.setVisibility(View.VISIBLE);
        dialog = ProgressDialog.show(getActivity(), "", "Please Wait...", true);
        dialog.setCancelable(true);
        ListThread();
    return  view;
    }

    private void ListThread() {
        Log.d("debug", "MobileNo Suggest Timer Started");
        mythread = null;
        Runnable runnable = new LoginTimer();
        mythread = new Thread(runnable);
        mythread.start();
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
                        progressBar.setVisibility(View.GONE);
                        //dialog.dismiss();
                        Successview();
                        mythread.interrupt();
                    }
                    if (pojoValue.isListingbyIdRecivedFailure()) {
                        pojoValue.setListingbyIdRecivedFailure(true);
                        dialog.dismiss();
                        mythread.interrupt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void Successview() {


    }

    }
