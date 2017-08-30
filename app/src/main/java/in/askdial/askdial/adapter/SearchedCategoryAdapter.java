package in.askdial.askdial.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.fragments.categories.Listing_Category_DetailsFragment;
import in.askdial.askdial.fragments.search.SearchFragment;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * Created by Admin on 09-Aug-17.
 */

public class SearchedCategoryAdapter extends RecyclerView.Adapter<SearchedCategoryAdapter.MasterFragmentViewHolder> {
    ArrayList<POJOValue> arrayList = new ArrayList<>();
    SearchFragment searchFragment;
    String ContextView;
    Context context;
    Activity activity;
    String SendCategory_listing_id;
    String LISTINGS_URL = DataApi.LISTINGS_DETAILS_URL;

    FunctionCalls functionCalls = new FunctionCalls();

    String lisiting_id, tv_comapny_name, tv_company_area, tv_company_city, tv_company_contact_person, tv_company_mobile1, tv_company_mobile2, tv_company_pincode,
            tv_company_address, tv_company_email, tv_company_website, tv_company_landline, tv_company_fax, tv_company_toll_free, tv_company_landmark;


    public SearchedCategoryAdapter(ArrayList<POJOValue> arrayList, String contextView, Context context, SearchFragment SearchFragment) {
        this.arrayList = arrayList;
        this.context = context;
        this.ContextView = contextView;
        activity = (Activity) context;
        this.searchFragment = SearchFragment;
    }

    @Override
    public MasterFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        MasterFragmentViewHolder viewHolder = new MasterFragmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MasterFragmentViewHolder holder, int position) {
        POJOValue pojoValue = arrayList.get(position);
        holder.textView_company_name.setText(pojoValue.getCompany_name());
        holder.textView_company_area.setText(pojoValue.getCompany_area());
        holder.textView_company_mobile.setText(pojoValue.getCompany_mobile1());
        holder.textView_company_email.setText(pojoValue.getCompany_email());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MasterFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_company_name, textView_company_area, textView_company_mobile, textView_company_email;

        public MasterFragmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView_company_name = (TextView) itemView.findViewById(R.id.textview_company_name);
            textView_company_area = (TextView) itemView.findViewById(R.id.textview_company_area);
            textView_company_mobile = (TextView) itemView.findViewById(R.id.textview_company_mobile);
            textView_company_email = (TextView) itemView.findViewById(R.id.textview_company_email);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            POJOValue content = arrayList.get(pos);
            lisiting_id = content.getCompany_lisiting_id();
            //Parsing listingID to get Listing Details
            //new GetListings(lisiting_id, content).execute();

            String lisiting_category_name = content.getCompany_category_name();
            tv_comapny_name=content.getCompany_name();
            tv_company_email=content.getCompany_area();
            tv_company_mobile1=content.getCompany_mobile1();
            tv_company_email=content.getCompany_email();
            arrayList.clear();
            Listing_Category_DetailsFragment fragment = new Listing_Category_DetailsFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = searchFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("listing_id", lisiting_id);
            bundle.putString("listing_category_name", lisiting_category_name);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();

        }
    }
}
