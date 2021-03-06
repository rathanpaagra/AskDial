package in.askdial.askdial.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.fragments.HomeFragment;
import in.askdial.askdial.fragments.categories.Visited_CatgFragment;
import in.askdial.askdial.values.POJOValue;

/**
 * Created by Admin on 29-Dec-16.
 */

public class FeaturedCategoryAdapter extends RecyclerView.Adapter<FeaturedCategoryAdapter.MainFragmentViewHolder>{
    ArrayList<POJOValue> arrayList= new ArrayList<>();
    HomeFragment homeFragment;
    String ContextView;
    Context context;
    Activity activity;


    public FeaturedCategoryAdapter(ArrayList<POJOValue> arrayList, String contextView, Context context, HomeFragment mainFragment) {
        this.arrayList = arrayList;
        this.context=context;
        this.ContextView=contextView;
        activity = (Activity)context;
        this.homeFragment=mainFragment;
    }

    @Override
    public MainFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MainFragmentViewHolder viewHolder = new MainFragmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainFragmentViewHolder holder, int position) {
        POJOValue pojoValue = arrayList.get(position);
        holder.categoryimage.setImageResource(pojoValue.getCategoryimages());
        holder.tvhead.setText(pojoValue.getCategorynames());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MainFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvhead/*, tvcontent*/;
        ImageView categoryimage;

        public MainFragmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvhead = (TextView) itemView.findViewById(R.id.tv_title);
            categoryimage = (ImageView) itemView.findViewById(R.id.cat_image);
            /*tvcontent = (TextView) itemView.findViewById(R.id.departmentcontent);*/

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            POJOValue pojoValue = arrayList.get(pos);
            String categoryName= pojoValue.getCategorynames();
            arrayList.clear();
            Visited_CatgFragment visited_catgFragment = new Visited_CatgFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = homeFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("category", categoryName);
            visited_catgFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, visited_catgFragment).addToBackStack(null).commit();
            /*int pos = getAdapterPosition();
            POJOValue pojoValue = arrayList.get(pos);
            String categoryID= pojoValue.getFirst_Level_Category_Id();
            String categoryName= pojoValue.getFirst_Level_Category_Name();
            ViewedCategoryFragment fragment=new ViewedCategoryFragment();
            Bundle bundle=new Bundle();
            FragmentTransaction fragmentTransaction=homeFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("category_id", categoryID);
            bundle.putString("category_category_name", categoryName);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();*/

        }
    }
}
