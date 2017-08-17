package in.askdial.askdial.fragments.categories;


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
import in.askdial.askdial.adapter.MasterFragmentAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class Visited_CatgFragment extends Fragment {

    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MasterFragmentAdapter masterFragmentAdapter;
    ConnectingTask task = new ConnectingTask();
    POJOValue pojoValue = new POJOValue();
    String contextview;
    Context context;
    String Category;
    private AVLoadingIndicatorView progressBar;

    public Visited_CatgFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_visted__catg, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        Category=bundle.getString("category");

        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.loading_bar);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview_category);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        masterFragmentAdapter = new MasterFragmentAdapter(arrayList, contextview, getActivity(),Visited_CatgFragment.this);

        ConnectingTask.SingleListingsFields checkVisitors = task.new SingleListingsFields(arrayList, Category,masterFragmentAdapter, pojoValue, getActivity(),progressBar);
        checkVisitors.execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(masterFragmentAdapter);
        return view;
    }

}
