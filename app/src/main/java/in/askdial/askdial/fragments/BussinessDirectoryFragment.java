package in.askdial.askdial.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import in.askdial.askdial.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BussinessDirectoryFragment extends Fragment {

 View view;
    public BussinessDirectoryFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Bussiness Directory");

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_bussiness_directory, container, false);
        if (getArguments() != null) {

            Toast.makeText(getActivity(), getArguments().getString("message"), Toast.LENGTH_SHORT).show();
        }

        return view;

    }

}
