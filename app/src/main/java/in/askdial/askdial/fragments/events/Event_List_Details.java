package in.askdial.askdial.fragments.events;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.wang.avi.AVLoadingIndicatorView;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.services.CustomVolleyRequest;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;
import in.askdial.askdial.values.TouchImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Event_List_Details extends Fragment {

    String list_id, list_Category_Name;

    String string_events_name,string_events_description,sting_events_image,string_events_start_date,string_events_end_date,
            string_events_location,string_added_on;

    LinearLayout linearlayout_loading_bar, linear_layout_Event_heading,ll_events_name,ll_events_description,
            ll_events_start_date,ll_events_end_date,ll_events_location,ll_added_on;
    RelativeLayout relative_layout_events_image;

    TextView tv_events_name,tv_events_description,tv_events_start_date,tv_events_end_date,tv_events_location,tv_events_added_on;

    Thread mythread;
    POJOValue pojoValue;
    ConnectingTask task;
    static ProgressDialog dialog = null;
    private AVLoadingIndicatorView progressBar;
    ImageLoader imageLoader;
    NetworkImageView imageView;

    public Event_List_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_event_list__details, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        list_id=bundle.getString("listing_id");
        list_Category_Name=bundle.getString("listing_category_name");
        task = new ConnectingTask();
        pojoValue=new POJOValue();
        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.event_details_loading_bar);

        linearlayout_loading_bar= (LinearLayout) view.findViewById(R.id.event_linearlayout_loading_bar);

        linear_layout_Event_heading= (LinearLayout) view.findViewById(R.id.linear_layout_Event_heading);

        //Initialization
        imageView = (NetworkImageView) view.findViewById(R.id.imageview_events_image);
        imageLoader = CustomVolleyRequest.getInstance(getActivity()).getImageLoader();

        tv_events_name=(TextView) view.findViewById(R.id.tv_events_name);
        tv_events_description = (TextView) view.findViewById(R.id.tv_events_description);
        tv_events_start_date = (TextView) view.findViewById(R.id.tv_events_start_date);
        tv_events_end_date=(TextView) view.findViewById(R.id.tv_events_end_date);
        tv_events_location = (TextView) view.findViewById(R.id.tv_events_location);
        tv_events_added_on = (TextView) view.findViewById(R.id.tv_events_added_on);

        relative_layout_events_image = (RelativeLayout) view.findViewById(R.id.relative_layout_events_image);
        ll_events_name=(LinearLayout) view.findViewById(R.id.ll_events_name);
        ll_events_description= (LinearLayout) view.findViewById(R.id.ll_events_description);
        ll_events_start_date= (LinearLayout) view.findViewById(R.id.ll_events_start_date);
        ll_events_end_date = (LinearLayout) view.findViewById(R.id.ll_events_end_date);
        ll_events_location = (LinearLayout) view.findViewById(R.id.ll_events_location);
        ll_added_on = (LinearLayout) view.findViewById(R.id.ll_added_on);

        ConnectingTask.Event_Listings login = task.new Event_Listings(list_id, pojoValue);
        login.execute();
        linearlayout_loading_bar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        linear_layout_Event_heading.setVisibility(View.GONE);
        relative_layout_events_image.setVisibility(View.GONE);

        //dialog = ProgressDialog.show(getActivity(), "", "Please Wait...", true);
        //dialog.setCancelable(true);

        ListThread();
        return view;

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
        if(getActivity() == null)
            return;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (pojoValue.isEvent_ListingbyIdRecivedSuccess()) {
                        pojoValue.setEvent_ListingbyIdRecivedSuccess(false);
                        linearlayout_loading_bar.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        linear_layout_Event_heading.setVisibility(View.VISIBLE);
                        relative_layout_events_image.setVisibility(View.VISIBLE);
                        //dialog.dismiss();
                         Successview();
                        mythread.interrupt();
                    }
                    if (pojoValue.isEvent_ListingbyIdRecivedFailure()) {
                        pojoValue.setEvent_ListingbyIdRecivedFailure(false);
                        //dialog.dismiss();
                        mythread.interrupt();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void Successview() {

        string_events_name =pojoValue.getEvents_name();
        if(!string_events_name.equals("")){
            ll_events_name.setVisibility(View.VISIBLE);
            tv_events_name.setText(string_events_name);
        }

        string_events_description=pojoValue.getEvents_image();
        imageLoader.get(string_events_description, ImageLoader.getImageListener(imageView, R.drawable.no_preview_300_150,
                R.drawable.no_preview_300_150));
        imageView.setImageUrl(string_events_description, imageLoader);

        string_events_description=pojoValue.getEvents_description();
        if(!string_events_description.equals("")){
            ll_events_description.setVisibility(View.VISIBLE);
            tv_events_description.setText(string_events_description);
        }

        string_events_start_date=pojoValue.getEvents_start_date();
        if(!string_events_start_date.equals("")){
            ll_events_start_date.setVisibility(View.VISIBLE);
            tv_events_start_date.setText(string_events_start_date);
        }
        string_events_end_date =pojoValue.getEvents_end_date();
        if(!string_events_end_date.equals("")){
            ll_events_end_date.setVisibility(View.VISIBLE);
            tv_events_end_date.setText(string_events_end_date);
        }

        string_events_location =pojoValue.getEvents_location();
        if(!string_events_location.equals("")) {
            ll_events_location.setVisibility(View.VISIBLE);
            tv_events_location.setText(string_events_location);

        }

        string_added_on =pojoValue.getAdded_on();
        if(!string_added_on.equals("")){
            ll_added_on.setVisibility(View.VISIBLE);
            tv_events_added_on.setText(string_added_on);

        }

    }
}
