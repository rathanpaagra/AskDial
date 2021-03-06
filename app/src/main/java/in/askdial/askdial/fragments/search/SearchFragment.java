package in.askdial.askdial.fragments.search;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.adapter.SearchedCategoryAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    //String keywords="Garments",city_id="47";
    String keywords,city_id,areaname;

    EditText search_keyword;

    FunctionCalls functionCalls = new FunctionCalls();
    Thread mythread;
    static ProgressDialog dialog = null;
    private AVLoadingIndicatorView progressBar;

    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchedCategoryAdapter searchedCategoryAdapter;
    ConnectingTask task = new ConnectingTask();
    POJOValue pojoValue = new POJOValue();
    String contextview;
    TextView searchresult_textview;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        city_id=bundle.getString("city_id");
        areaname=bundle.getString("area_name");
        keywords=bundle.getString("keyword");
        search_keyword= (EditText) view.findViewById(R.id.search_textview1);
        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.loading_bar3);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview_search);
        searchresult_textview= (TextView) view.findViewById(R.id.searchresult_textview);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        searchedCategoryAdapter = new SearchedCategoryAdapter(arrayList, contextview, getActivity(),SearchFragment.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchedCategoryAdapter);

        ConnectingTask.GetSearchedListings login = task.new GetSearchedListings(arrayList,keywords,city_id,areaname, pojoValue,searchedCategoryAdapter, getActivity(),progressBar);
        login.execute();
        mythread = null;
        Runnable runnable = new LoginTimer();
        mythread = new Thread(runnable);
        mythread.start();
        //progressBar.setVisibility(View.VISIBLE);
        //dialog = ProgressDialog.show(getActivity(), "", "Please Wait...", true);
       // dialog.setCancelable(true);
        //ListThread();
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
                        if (pojoValue.isSearchKeywordSuccess()) {
                            pojoValue.setSearchKeywordSuccess(false);
                            //progressBar.setVisibility(View.GONE);
                            //dialog.dismiss();
                            mythread.interrupt();
                        }
                        if (pojoValue.isSearchKeyWordFailure()) {
                            pojoValue.setSearchKeyWordFailure(false);
                            mythread.interrupt();
                            recyclerView.setVisibility(View.GONE);
                            searchresult_textview.setVisibility(View.VISIBLE);
                            //dialog.dismiss();

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
