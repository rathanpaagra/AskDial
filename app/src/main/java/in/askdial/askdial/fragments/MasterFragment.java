package in.askdial.askdial.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.askdial.askdial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    TextView comapanyName, companyArea,companyEmail, companyMobile;
    Toolbar toolbar;
    View view;
    int poition;
    String categoryId="";

    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_master, container, false);
        //footer
        //footer = (Toolbar) view.findViewById(R.id.footer);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        Bundle bundle = new Bundle();
        bundle = getArguments();
       // image = bundle.getInt("image");
        if (bundle.getString("id") != null) {
            categoryId = bundle.getString("id");
        }
        comapanyName = (TextView) view.findViewById(R.id.company_name_txt);
        companyArea= (TextView) view.findViewById(R.id.company_area_address);
        companyEmail= (TextView) view.findViewById(R.id.company_email_address);
        companyMobile= (TextView) view.findViewById(R.id.company_mobile_no);

       // tv_detailscontent = (TextView) view.findViewById(R.id.content_txt);

                       return view;
    }

}
