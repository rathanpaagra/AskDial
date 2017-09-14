package in.askdial.askdial.fragments.classifieds;


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

import com.wang.avi.AVLoadingIndicatorView;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifiedsCat_List_Details extends Fragment {

    String list_id, list_Category_Name;
    Button button_back,button_call,button_location;
    TextView tv_classified_name,tv_classifieds_description,tv_classified_category_name,tv_classifieds_amount,tv_company_area,tv_company_city, tv_cls_contact_person_name, tv_cls_person_mobile,tv_company_mobile2,tv_company_pincode,
            tv_classifieds_area, tv_classified_person_email,tv_company_website,tv_company_landline,tv_company_fax,tv_company_toll_free,tv_company_landmark;

    String s_classified_name, s_classified_area,s_classified_description,s_company_city, s_classified_contact_person, s_classified_person_mobile,s_classified_amount,s_company_mobile2,s_company_pincode,
            s_company_address, s_classified_person_email,s_company_website,s_company_landline,s_company_fax,s_company_toll_free,s_company_landmark;

    LinearLayout linearLayout_classifieds_area,linearLayout_classified_name,linear_Layout_description,linearLayout_classifieds_amount,linearLayout_company_city,linearLayout__company_contact_details, linearLayout_classifieds_contact_person,
            linearLayout_classifieds_mobile, linearLayout_company_mobile1, linearLayout_company_mobile2,linearLayout_company_adress, linearLayout_classifieds_email,linearLayout_company_website,linearLayout_company_landline,
            linearLayout_company_fax,linearLayout_company_tollfree,linearLayout_company_landmark,linearLayout_company_pincode;
    LinearLayout linearlayout_loading_bar, linear_layout_product_details,linear_layout_product_name,linear_layout_comapny_details;
    RelativeLayout relative_layout_logo;
    View style1,style2,style3,style4,style5,style6,style7,style8,style9;

    FunctionCalls functionCalls = new FunctionCalls();
    String LISTINGS_URL = DataApi.LISTINGS_DETAILS_URL;
    Thread mythread;
    POJOValue pojoValue;
    ConnectingTask task;
    static ProgressDialog dialog = null;
    private AVLoadingIndicatorView progressBar;

    public ClassifiedsCat_List_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_classifieds_cat__list__details, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        list_id=bundle.getString("listing_id");
        list_Category_Name=bundle.getString("listing_category_name");
        task = new ConnectingTask();
        pojoValue=new POJOValue();
        progressBar = (AVLoadingIndicatorView) view.findViewById(R.id.loading_bar);

        linearlayout_loading_bar= (LinearLayout) view.findViewById(R.id.cls_linearlayout_loading_bar);
        relative_layout_logo = (RelativeLayout) view.findViewById(R.id.cls_linear_layout_logo);
        linear_layout_product_name= (LinearLayout) view.findViewById(R.id.linear_layout_product_name);
        linear_layout_product_details = (LinearLayout) view.findViewById(R.id.linear_layout_product_details);

        //Initialization
        tv_classified_category_name=(TextView) view.findViewById(R.id.tv_cls_category_name);
        tv_classified_name = (TextView) view.findViewById(R.id.tv_classifieds_name);
        tv_classified_person_email = (TextView) view.findViewById(R.id.tv_contact_person_email);
        tv_classifieds_description=(TextView) view.findViewById(R.id.tv_classifieds_description);
        tv_cls_contact_person_name = (TextView) view.findViewById(R.id.tv_cls_contact_person);
        tv_cls_person_mobile = (TextView) view.findViewById(R.id.tv_contact_person_mobile);
        tv_classifieds_area = (TextView) view.findViewById(R.id.tv_classifieds_area);
        tv_classifieds_amount= (TextView) view.findViewById(R.id.tv_classifieds_amount);

        linearLayout_classified_name=(LinearLayout) view.findViewById(R.id.ll_classified_name);
        linear_Layout_description= (LinearLayout) view.findViewById(R.id.ll_classifieds_description);
        linearLayout_classifieds_amount= (LinearLayout) view.findViewById(R.id.ll_classifieds_amount);
        linearLayout_classifieds_contact_person = (LinearLayout) view.findViewById(R.id.ll_cls_contact_person);
        linearLayout_classifieds_mobile = (LinearLayout) view.findViewById(R.id.ll_contact_person_mobile);
        linearLayout_classifieds_area = (LinearLayout) view.findViewById(R.id.ll_classifieds_area);
        linearLayout_classifieds_email = (LinearLayout) view.findViewById(R.id.ll_contact_person_email);


        //divider Initialization
        style1=view.findViewById(R.id.div1);
        style2=view.findViewById(R.id.div2);
        style3=view.findViewById(R.id.div3);
        style4=view.findViewById(R.id.div4);

        ConnectingTask.GetClassifieds_Listings login = task.new GetClassifieds_Listings(list_id, pojoValue);
        login.execute();
        linearlayout_loading_bar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        relative_layout_logo.setVisibility(View.GONE);
        linear_layout_product_name.setVisibility(View.GONE);
        linear_layout_product_details.setVisibility(View.GONE);
        //dialog = ProgressDialog.show(getActivity(), "", "Please Wait...", true);
        //dialog.setCancelable(true);

        ListThread();
        return view;

         // region for other fields initialization


        //tv_company_area= (TextView) view.findViewById(R.id.tv_listing_company_area);
        //tv_company_city= (TextView) view.findViewById(R.id.tv_listing_company_city);
        // tv_company_mobile2= (TextView) view.findViewById(R.id.tv_contact_person_mobile2);
        //tv_company_pincode= (TextView) view.findViewById(R.id.tv_company_pincode);
        //tv_company_website= (TextView) view.findViewById(R.id.tv_company_website);
        //tv_company_landline= (TextView) view.findViewById(R.id.tv_company_landline);
        //tv_company_fax= (TextView) view.findViewById(R.id.tv_company_fax);
        //tv_company_toll_free= (TextView) view.findViewById(R.id.tv_company_tollfree);
        //tv_company_landmark= (TextView) view.findViewById(R.id.tv_company_landmark);

        //linearLayout__company_contact_details= (LinearLayout) view.findViewById(R.id.ll_company_contact_details);
        //linearLayout_company_mobile1= (LinearLayout) view.findViewById(R.id.ll_contact_mobile1);
        // linearLayout_company_city= (LinearLayout) view.findViewById(R.id.ll_company_city);
        //linearLayout_company_mobile2= (LinearLayout) view.findViewById(R.id.ll_contact_mobile2);
        //linearLayout_company_website= (LinearLayout) view.findViewById(R.id.ll_listing_website);
        //linearLayout_company_landline= (LinearLayout) view.findViewById(R.id.ll_listing_landline);
        //linearLayout_company_fax= (LinearLayout) view.findViewById(R.id.ll_listing_fax);
        //linearLayout_company_adress= (LinearLayout) view.findViewById(R.id.ll_listing_address);
        //linearLayout_company_tollfree= (LinearLayout) view.findViewById(R.id.ll_listing_tollfree);
        //linearLayout_company_landmark= (LinearLayout) view.findViewById(R.id.ll_listing_landmark);
        //linearLayout_company_pincode= (LinearLayout) view.findViewById(R.id.ll_listing_pincode);

        //style5=view.findViewById(R.id.d5);
        //style6=view.findViewById(R.id.d6);
        //style7=view.findViewById(R.id.d7);
        //style8=view.findViewById(R.id.d8);
        //style9=view.findViewById(R.id.d9);
        //endregion

        // new GetListings(list_id).execute();

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
                    if (pojoValue.isClassified_ListingbyIdRecivedSuccess()) {
                        pojoValue.setClassified_ListingbyIdRecivedSuccess(false);
                        linearlayout_loading_bar.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        relative_layout_logo.setVisibility(View.VISIBLE);
                        linear_layout_product_name.setVisibility(View.VISIBLE);
                        linear_layout_product_details.setVisibility(View.VISIBLE);
                        //dialog.dismiss();
                         Successview();
                        mythread.interrupt();
                    }
                    if (pojoValue.isClassified_ListingbyIdRecivedFailure()) {
                        pojoValue.setClassified_ListingbyIdRecivedFailure(false);
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

        s_classified_name =pojoValue.getClassifieds_name();
        if(!s_classified_name.equals("")){
            linearLayout_classified_name.setVisibility(View.VISIBLE);
            tv_classified_name.setText(s_classified_name);
        }


        if(!list_Category_Name.equals("")){
            tv_classified_category_name.setText(list_Category_Name);
        }

        s_classified_description=pojoValue.getClassifieds_description();
        if(!s_classified_description.equals("")){
            linear_Layout_description.setVisibility(View.VISIBLE);
            tv_classifieds_description.setText(s_classified_description);
        }

        s_classified_amount=pojoValue.getClassifieds_amount();
        if(!s_classified_amount.equals("")){
            linearLayout_classifieds_amount.setVisibility(View.VISIBLE);
            tv_classifieds_amount.setText(s_classified_amount);
        }
        s_classified_contact_person =pojoValue.getClassifieds_contact_person_Name();
        if(!s_classified_contact_person.equals("")){
            linearLayout_classifieds_contact_person.setVisibility(View.VISIBLE);
            tv_cls_contact_person_name.setText(s_classified_contact_person);
            style1.setVisibility(View.VISIBLE);
        }

        s_classified_person_mobile =pojoValue.getClassifieds_contact_person_mobile();
        if(!s_classified_person_mobile.equals("")) {
            linearLayout_classifieds_mobile.setVisibility(View.VISIBLE);
            tv_cls_person_mobile.setText(s_classified_person_mobile);
            style2.setVisibility(View.VISIBLE);
        }

        s_classified_area =pojoValue.getClassifieds_area();
        if(!s_classified_area.equals("")){
            linearLayout_classifieds_area.setVisibility(View.VISIBLE);
            tv_classifieds_area.setText(s_classified_area);
            style3.setVisibility(View.VISIBLE);
        }

        s_classified_person_email =pojoValue.getClassified_Contact_person_email();
        if(!s_classified_person_email.equals("")){
            linearLayout_classifieds_email.setVisibility(View.VISIBLE);
            tv_classified_person_email.setText(s_classified_person_email);
            style4.setVisibility(View.VISIBLE);
        }

        /*s_company_website=pojoValue.getCompany_website();
        if(!s_company_website.equals("")){
            linearLayout_company_website.setVisibility(View.VISIBLE);
            tv_company_website.setText(s_company_website);
            style5.setVisibility(View.VISIBLE);
        }

        s_company_landline=pojoValue.getCompany_landline();
        if(!s_company_landline.equals("")){
            linearLayout_company_landline.setVisibility(View.VISIBLE);
            tv_company_landline.setText(s_company_landline);
            style6.setVisibility(View.VISIBLE);
        }

        s_company_fax=pojoValue.getCompany_fax();
        if(!s_company_fax.equals("")){
            linearLayout_company_fax.setVisibility(View.VISIBLE);
            tv_company_fax.setText(s_company_fax);
            style7.setVisibility(View.VISIBLE);
        }


        s_company_toll_free=pojoValue.getCompany_toll_free();
        if(!s_company_toll_free.equals("")){
            linearLayout_company_tollfree.setVisibility(View.VISIBLE);
            tv_company_toll_free.setText(s_company_toll_free);
            style8.setVisibility(View.VISIBLE);
        }

        s_company_landmark=pojoValue.getCompany_landmark();
        if(!s_company_landmark.equals("")){
            linearLayout_company_landmark.setVisibility(View.VISIBLE);
            tv_company_landmark.setText(s_company_landmark);
            style9.setVisibility(View.VISIBLE);
        }

        s_company_pincode=pojoValue.getCompany_pincode();
        if(!s_company_pincode.equals("")){
            linearLayout_company_pincode.setVisibility(View.VISIBLE);
            tv_company_pincode.setText(s_company_pincode);
        }*/


    }
}
