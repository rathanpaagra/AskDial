package in.askdial.askdial.fragments.classifieds;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.adapter.MainFragmentAdapter;
import in.askdial.askdial.adapter.classifiedsAdapter.ClassifiedsCategoryAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.fragments.viewmoreCategories.MainFragment;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifiedsCategory extends Fragment {


    public static final int VISITORS_DLG = 3;
    RecyclerView recyclerView;
    View view;
    Activity activity;

    ConnectingTask task =new ConnectingTask();
    POJOValue pojoValue= new POJOValue();
    String contextview;
    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView.LayoutManager layoutManager;
    ClassifiedsCategoryAdapter mainFragmentAdapter;
    Context context;
    Thread categoryhead;
    static ProgressDialog dialog = null;

    private AVLoadingIndicatorView progressBar;

    public ClassifiedsCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_classifieds_category, container, false);
        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.classifieds_loading_bar2);
        recyclerView = (RecyclerView) view.findViewById(R.id.classified_recyclerview_title);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mainFragmentAdapter = new ClassifiedsCategoryAdapter(arrayList,contextview, getActivity(), ClassifiedsCategory.this);

        ConnectingTask.ClassifiedsCategoryFields checkVisitors = task.new ClassifiedsCategoryFields(arrayList, mainFragmentAdapter, pojoValue, getActivity(),progressBar);
        checkVisitors.execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mainFragmentAdapter);



        return view;
    }

}
