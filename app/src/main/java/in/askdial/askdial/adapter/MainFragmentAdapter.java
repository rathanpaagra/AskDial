package in.askdial.askdial.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.fragments.MainFragment;
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


    public MainFragmentAdapter(ArrayList<POJOValue> arrayList,String contextView, Context context) {
        this.arrayList = arrayList;
        this.context=context;
        this.ContextView=contextView;
        activity = (Activity)context;
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
        holder.tvhead.setText(pojoValue.getFirst_Level_Category_Name());
        /*holder.tvcontent.setText(content.getDepartmentcontent());*/
      //  holder.categoryimage.setImageResource(pojoValue.getFirst_Level_Category_Id());
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
            /*tvcontent = (TextView) itemView.findViewById(R.id.departmentcontent);*/
           // categoryimage = (ImageView) itemView.findViewById(R.id.image_row);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();

          /*  POJOValue pojoValue = arrayList.get(pos);
          //  int image = pojoValue.getDepartmentimgs();
            //String categoryHead = pojoValue.getFirst_Level_Category_Name();
            String categoryID= pojoValue.getFirst_Level_Category_Id();
            MasterFragment fragment = new MasterFragment();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = mainFragment.getActivity().getSupportFragmentManager().beginTransaction();
          //  bundle.putInt("image", image);
            bundle.putInt("pos", pos);
            bundle.putString("id",categoryID);
            //bundle.putString("name", categoryHead);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();*/
        }
    }
}
