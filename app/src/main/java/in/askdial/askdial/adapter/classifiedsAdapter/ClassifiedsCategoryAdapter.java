package in.askdial.askdial.adapter.classifiedsAdapter;

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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.fragments.classifieds.ClassifiedsCat_Listings;
import in.askdial.askdial.fragments.classifieds.ClassifiedsCategory;
import in.askdial.askdial.fragments.viewmoreCategories.MainFragment;
import in.askdial.askdial.fragments.viewmoreCategories.ViewedCategoryFragment;
import in.askdial.askdial.services.CustomVolleyRequest;
import in.askdial.askdial.values.POJOValue;

/**
 * Created by Admin on 29-Dec-16.
 */

public class ClassifiedsCategoryAdapter extends RecyclerView.Adapter<ClassifiedsCategoryAdapter.MainFragmentViewHolder>{
    ArrayList<POJOValue> arrayList= new ArrayList<>();
    ClassifiedsCategory mainFragment;
    String ContextView;
    Context context;
    Activity activity;


    public ClassifiedsCategoryAdapter(ArrayList<POJOValue> arrayList, String contextView, Context context, ClassifiedsCategory mainFragment) {
        this.arrayList = arrayList;
        this.context=context;
        this.ContextView=contextView;
        activity = (Activity)context;
        this.mainFragment=mainFragment;
    }

    @Override
    public MainFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_classifieds, parent, false);
        MainFragmentViewHolder viewHolder = new MainFragmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainFragmentViewHolder holder, int position) {
        POJOValue pojoValue = arrayList.get(position);
        holder.imageLoader.get(pojoValue.getClassified_image(), ImageLoader.getImageListener(holder.Classified_Image,
                R.drawable.no_preview_300_150, R.drawable.no_preview_300_150));
        holder.Classified_Image.setImageUrl(pojoValue.getClassified_image(), holder.imageLoader);

        // holder.categoryimage.setImageResource(pojoValue.getCategoryimages());
       // holder.tvhead.setText(pojoValue.getCategorynames());
        //holder.categoryimage.setImageResource(pojoValue.getFirst_Level_Category_Id());
        holder.tvhead.setText(pojoValue.getClassifieds_Category_Name());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MainFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvhead/*, tvcontent*/;
        NetworkImageView Classified_Image;
        ImageLoader imageLoader;

        ImageView categoryimage;

        public MainFragmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Classified_Image = (NetworkImageView) itemView.findViewById(R.id.clasiified_Category_image);
            imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
            tvhead = (TextView) itemView.findViewById(R.id.textView_cls_cat_name);
            /*tvcontent = (TextView) itemView.findViewById(R.id.departmentcontent);*/
           //categoryimage = (ImageView) itemView.findViewById(R.id.cat_image);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            POJOValue pojoValue = arrayList.get(pos);
            String categoryID= pojoValue.getClassifieds_Category_ID();
            String categoryName= pojoValue.getClassifieds_Category_Name();
            arrayList.clear();
            ClassifiedsCat_Listings fragment=new ClassifiedsCat_Listings();
            Bundle bundle=new Bundle();
            FragmentTransaction fragmentTransaction=mainFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("category_id", categoryID);
            bundle.putString("category_category_name", categoryName);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();

        }
    }
}
