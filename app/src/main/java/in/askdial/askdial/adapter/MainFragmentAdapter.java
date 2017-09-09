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
import in.askdial.askdial.fragments.viewmoreCategories.MainFragment;
import in.askdial.askdial.fragments.viewmoreCategories.ViewedCategoryFragment;
import in.askdial.askdial.values.POJOValue;

/**
 * Created by Admin on 29-Dec-16.
 */

public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>{
    ArrayList<POJOValue> arrayList= new ArrayList<>();
    MainFragment mainFragment;
    String ContextView;
    Context context;
    Activity activity;


    public MainFragmentAdapter(ArrayList<POJOValue> arrayList,String contextView, Context context, MainFragment mainFragment) {
        this.arrayList = arrayList;
        this.context=context;
        this.ContextView=contextView;
        activity = (Activity)context;
        this.mainFragment=mainFragment;
    }

    @Override
    public MainFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view_more, parent, false);
        MainFragmentViewHolder viewHolder = new MainFragmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainFragmentViewHolder holder, int position) {
        POJOValue pojoValue = arrayList.get(position);
       // holder.categoryimage.setImageResource(pojoValue.getCategoryimages());
       // holder.tvhead.setText(pojoValue.getCategorynames());
        //holder.categoryimage.setImageResource(pojoValue.getFirst_Level_Category_Id());
        holder.tvhead.setText(pojoValue.getFirst_Level_Category_Name());

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
            tvhead = (TextView) itemView.findViewById(R.id.textView_cat_name);
            /*tvcontent = (TextView) itemView.findViewById(R.id.departmentcontent);*/
           //categoryimage = (ImageView) itemView.findViewById(R.id.cat_image);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            POJOValue pojoValue = arrayList.get(pos);
            String categoryID= pojoValue.getFirst_Level_Category_Id();
            String categoryName= pojoValue.getFirst_Level_Category_Name();
            arrayList.clear();
            ViewedCategoryFragment fragment=new ViewedCategoryFragment();
            Bundle bundle=new Bundle();
            FragmentTransaction fragmentTransaction=mainFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("category_id", categoryID);
            bundle.putString("category_category_name", categoryName);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();

        }
    }
}
