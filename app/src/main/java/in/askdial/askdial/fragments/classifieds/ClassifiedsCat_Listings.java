package in.askdial.askdial.fragments.classifieds;


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
import in.askdial.askdial.adapter.ViewdCategoryAdapter;
import in.askdial.askdial.adapter.classifiedsAdapter.ClassifiedCategory_ListAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.fragments.viewmoreCategories.ViewedCategoryFragment;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifiedsCat_Listings extends Fragment {

    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ClassifiedCategory_ListAdapter viewdCategoryAdapter;
    ConnectingTask task = new ConnectingTask();
    POJOValue pojoValue = new POJOValue();
    String contextview;
    Context context;
    String Category_ID,Category_Name;
    private AVLoadingIndicatorView progressBar;


    public ClassifiedsCat_Listings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_classifieds_cat__listings, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        Category_ID=bundle.getString("category_id");
        Category_Name=bundle.getString("category_category_name");

        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.classified_cat_list_loading_bar);
        recyclerView= (RecyclerView) view.findViewById(R.id.classified_cat_list_recyclerview);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        viewdCategoryAdapter = new ClassifiedCategory_ListAdapter(arrayList, contextview, getActivity(),ClassifiedsCat_Listings.this);
        ConnectingTask.ClassifiedCat_Listings checkVisitors = task.new ClassifiedCat_Listings(arrayList, Category_ID,viewdCategoryAdapter, pojoValue, getActivity(),progressBar);
        checkVisitors.execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewdCategoryAdapter);
        return view;
    }

}
