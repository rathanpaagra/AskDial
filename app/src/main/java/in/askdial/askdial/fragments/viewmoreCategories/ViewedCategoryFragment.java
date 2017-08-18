package in.askdial.askdial.fragments.viewmoreCategories;

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
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewedCategoryFragment extends Fragment {

    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ViewdCategoryAdapter viewdCategoryAdapter;
    ConnectingTask task = new ConnectingTask();
    POJOValue pojoValue = new POJOValue();
    String contextview;
    Context context;
    String Category_ID,Category_Name;
    private AVLoadingIndicatorView progressBar;

    public ViewedCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_viewed_category, container, false);
        Bundle bundle = new Bundle();
        bundle = getArguments();
        Category_ID=bundle.getString("category_id");
       Category_Name=bundle.getString("category_category_name");

        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.loading_bar1);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview_viewed_category);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        viewdCategoryAdapter = new ViewdCategoryAdapter(arrayList, contextview, getActivity(),ViewedCategoryFragment.this);
        ConnectingTask.ListingsFields checkVisitors = task.new ListingsFields(arrayList, Category_ID,viewdCategoryAdapter, pojoValue, getActivity(),progressBar);
        checkVisitors.execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewdCategoryAdapter);
        return view;
    }

}
