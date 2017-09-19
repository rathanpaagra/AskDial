package in.askdial.askdial.fragments.events;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.adapter.classifiedsAdapter.ClassifiedCategory_ListAdapter;
import in.askdial.askdial.adapter.eventsAdapter.All_EventsAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class All_Events extends Fragment {

    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    All_EventsAdapter alleventsAdapter;
    ConnectingTask task = new ConnectingTask();
    POJOValue pojoValue = new POJOValue();
    String contextview;
    Context context;
    String Category_ID,Category_Name;
    private AVLoadingIndicatorView progressBar;

    private Parcelable listState;

    public All_Events() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_events, container, false);

       /* Bundle bundle = new Bundle();
        bundle = getArguments();
        Category_ID=bundle.getString("category_id");
        Category_Name=bundle.getString("category_category_name");*/

        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.all_events_loading_bar);
        recyclerView= (RecyclerView) view.findViewById(R.id.all_events_recyclerview);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        alleventsAdapter = new All_EventsAdapter(arrayList, contextview, getActivity(),All_Events.this);
        ConnectingTask.AllEventsList checkVisitors = task.new AllEventsList(arrayList, alleventsAdapter, pojoValue, getActivity(),progressBar);
        checkVisitors.execute();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(alleventsAdapter);

        return view;
    }

}
