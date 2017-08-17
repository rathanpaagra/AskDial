package in.askdial.askdial.fragments;


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
import android.widget.Toast;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.adapter.MainFragmentAdapter;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public static final int VISITORS_DLG = 3;
    RecyclerView recyclerView;
    View view;
    Activity activity;

    ConnectingTask task =new ConnectingTask();
    POJOValue pojoValue= new POJOValue();
    String contextview;
    ArrayList<POJOValue> arrayList = new ArrayList<POJOValue>();
    RecyclerView.LayoutManager layoutManager;
    MainFragmentAdapter mainFragmentAdapter;

    Context context;
    Thread categoryhead;
    static ProgressDialog dialog = null;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Home Page");

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        if (getArguments() != null) {
            Toast.makeText(getActivity(), getArguments().getString("message"), Toast.LENGTH_SHORT).show();
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_title);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mainFragmentAdapter = new MainFragmentAdapter(arrayList,contextview, getActivity());

        /*ConnectingTask.CategoryFields checkVisitors = task.new CategoryFields(arrayList, mainFragmentAdapter, pojoValue, context);
        checkVisitors.execute();*/
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mainFragmentAdapter);

        return view;
    }


}
