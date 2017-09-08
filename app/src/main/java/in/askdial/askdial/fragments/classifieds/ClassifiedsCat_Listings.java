package in.askdial.askdial.fragments.classifieds;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.askdial.askdial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifiedsCat_Listings extends Fragment {


    public ClassifiedsCat_Listings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classifieds_cat__listings, container, false);
    }

}
