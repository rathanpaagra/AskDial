package in.askdial.askdial.adapter.classifiedsAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.fragments.categories.Listing_Category_DetailsFragment;
import in.askdial.askdial.fragments.classifieds.ClassifiedsCat_List_Details;
import in.askdial.askdial.fragments.classifieds.ClassifiedsCat_Listings;
import in.askdial.askdial.services.CustomVolleyRequest;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 09-Aug-17.
 */

public class ClassifiedCategory_ListAdapter extends RecyclerView.Adapter<ClassifiedCategory_ListAdapter.MasterFragmentViewHolder> {
    ArrayList<POJOValue> arrayList = new ArrayList<>();
    ClassifiedsCat_Listings viewedtgFragment;
    String ContextView;
    Context context;
    Activity activity;

    String SendCategory_listing_id;
    String LISTINGS_URL = DataApi.LISTINGS_DETAILS_URL;

    FunctionCalls functionCalls = new FunctionCalls();

    String Classified_id, cls_comapny_name, cls_company_area, tv_company_city, cls_company_contact_person, tv_company_mobile1, tv_company_mobile2, tv_company_pincode,
            tv_company_address, cls_classifieds_description, tv_company_website, tv_company_landline, tv_company_fax, tv_company_toll_free, tv_company_landmark;


    public ClassifiedCategory_ListAdapter(ArrayList<POJOValue> arrayList, String contextView, Context context, ClassifiedsCat_Listings visited_catgFragment) {
        this.arrayList = arrayList;
        this.context = context;
        this.ContextView = contextView;
        activity = (Activity) context;
        this.viewedtgFragment = visited_catgFragment;
    }

    @Override
    public MasterFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_classfieds, parent, false);
        MasterFragmentViewHolder viewHolder = new MasterFragmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MasterFragmentViewHolder holder, int position) {
        POJOValue pojoValue = arrayList.get(position);

        /*cls_comapny_name=pojoValue.getClassifieds_name();
        if(cls_comapny_name != ""){
            holder.textView_classified_name.setVisibility(View.VISIBLE);
            holder.textView_classified_name.setText(cls_comapny_name);
        }*/
        holder.imageLoader.get(pojoValue.getClassified_image(), ImageLoader.getImageListener(holder.Classified_Image,
                R.drawable.no_preview_300_150, R.drawable.no_preview_300_150));
        holder.Classified_Image.setImageUrl(pojoValue.getClassified_image(), holder.imageLoader);

        cls_company_area=pojoValue.getClassifieds_area();

        if(!cls_company_area.equals("") ){
            holder.textView_classifieds_area.setVisibility(View.VISIBLE);
            holder.textView_classifieds_area.setText(cls_company_area);
        }else{
            holder.textView_classifieds_area.setVisibility(View.GONE);
        }

        cls_company_contact_person=pojoValue.getClassifieds_contact_person_mobile();
        if(!cls_company_contact_person.equals("")){
            holder.textView_cls_person_mobile.setVisibility(View.VISIBLE);
            holder.textView_cls_person_mobile.setText(cls_company_contact_person);
        }else{
            holder.textView_cls_person_mobile.setVisibility(View.GONE);
        }

        cls_classifieds_description=pojoValue.getClassifieds_description();
       // if(cls_classifieds_description != null){
        if(!cls_classifieds_description.equals("")){
            holder.textView_classified_description.setVisibility(View.VISIBLE);
            holder.textView_classified_description.setText(cls_classifieds_description);
        }else{
            holder.textView_classified_description.setVisibility(View.GONE);
        }

        holder.textView_classified_name.setText(pojoValue.getClassifieds_name());
        //holder.textView_classifieds_area.setText(pojoValue.getClassifieds_area());
        //holder.textView_cls_person_mobile.setText(pojoValue.getClassifieds_contact_person_mobile());
        //older.textView_classified_description.setText(pojoValue.getClassifieds_description());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MasterFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_classified_name, textView_classifieds_area, textView_cls_person_mobile, textView_classified_description;
        NetworkImageView Classified_Image;
        ImageLoader imageLoader;

        public MasterFragmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Classified_Image = (NetworkImageView) itemView.findViewById(R.id.clasiified_image);
            imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
            textView_classified_name = (TextView) itemView.findViewById(R.id.cls_textview_company_name);
            textView_classifieds_area = (TextView) itemView.findViewById(R.id.cls_textview_company_area);
            textView_cls_person_mobile = (TextView) itemView.findViewById(R.id.cls_textview_company_mobile);
            textView_classified_description = (TextView) itemView.findViewById(R.id.cls_textview_company_email);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            // check if item still exists

            POJOValue content = arrayList.get(pos);
            Classified_id = content.getClassifieds_id();

            //Parsing listingID to get Listing Details
            //new GetListings(lisiting_id, content).execute();

            String lisiting_category_name = content.getClassifieds_Category_Name();
            cls_comapny_name=content.getClassifieds_name();
            cls_classifieds_description=content.getClassifieds_description();
            cls_company_contact_person=content.getClassifieds_contact_person_mobile();
            cls_company_area=content.getClassifieds_area();

            ClassifiedsCat_List_Details fragment = new ClassifiedsCat_List_Details();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = viewedtgFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("listing_id", Classified_id);
            bundle.putString("listing_category_name", lisiting_category_name);
            fragment.setArguments(bundle);
            arrayList.clear();
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();

            //region intenting all fields (right now not using it)
            //details to intent
           /* bundle.putString("company_name", tv_comapny_name);
            bundle.putString("company_email", tv_company_email);
            bundle.putString("category_website", tv_company_website);
            bundle.putString("category_mobile1", tv_company_mobile1);
            bundle.putString("category_mobile2", tv_company_mobile2);
            bundle.putString("category_contact_person", tv_company_contact_person);
            bundle.putString("category_landline", tv_company_landline);
            bundle.putString("category_fax", tv_company_fax);
            bundle.putString("category_tollfree", tv_company_toll_free);
            bundle.putString("company_area", tv_company_area);
            bundle.putString("city_name", tv_company_city);
            bundle.putString("company_landmark", tv_company_landmark);
            bundle.putString("category_pincode", tv_company_pincode);
            bundle.putString("company_address", tv_company_address);*/
            //endregion

        }
    }


}
