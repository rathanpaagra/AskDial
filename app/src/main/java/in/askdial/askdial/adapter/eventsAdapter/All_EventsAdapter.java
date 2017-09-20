package in.askdial.askdial.adapter.eventsAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import in.askdial.askdial.R;
import in.askdial.askdial.dataposting.DataApi;
import in.askdial.askdial.fragments.classifieds.ClassifiedsCat_List_Details;
import in.askdial.askdial.fragments.events.All_Events;
import in.askdial.askdial.fragments.events.Event_List_Details;
import in.askdial.askdial.services.CustomVolleyRequest;
import in.askdial.askdial.values.FunctionCalls;
import in.askdial.askdial.values.POJOValue;

/**
 * Created by Admin on 09-Aug-17.
 */

public class All_EventsAdapter extends RecyclerView.Adapter<All_EventsAdapter.MasterFragmentViewHolder> {
    ArrayList<POJOValue> arrayList = new ArrayList<>();
    All_Events allEventsFragment;
    String ContextView;
    Context context;
    Activity activity;




    String str_message,str_event_name,str_event_start,str_event_end, str_event_id;


    public All_EventsAdapter(ArrayList<POJOValue> arrayList, String contextView, Context context, All_Events visited_catgFragment) {
        this.arrayList = arrayList;
        this.context = context;
        this.ContextView = contextView;
        activity = (Activity) context;
        this.allEventsFragment = visited_catgFragment;
    }

    @Override
    public MasterFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_events, parent, false);
        MasterFragmentViewHolder viewHolder = new MasterFragmentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MasterFragmentViewHolder holder, int position) {
        POJOValue pojoValue = arrayList.get(position);

        holder.imageLoader.get(pojoValue.getEvents_image(), ImageLoader.getImageListener(holder.Events_Image,
        R.drawable.no_preview_300_150, R.drawable.no_preview_300_150));
        holder.Events_Image.setImageUrl(pojoValue.getEvents_image(), holder.imageLoader);

        /*str_message=pojoValue.getEvents_message();
        if(!str_message.equals("")){
            holder.evnt_message.setVisibility(View.VISIBLE);
            holder.evnt_message.setText(str_message);
        }else{
            holder.evnt_message.setVisibility(View.GONE);
        }*/

        str_event_start=pojoValue.getEvents_start_date();
       // if(cls_classifieds_description != null){
        if(!str_event_start.equals("")){
            holder.event_start_date.setVisibility(View.VISIBLE);
            holder.event_start_date.setText(str_event_start);
        }else{
            holder.event_start_date.setVisibility(View.GONE);
        }
        str_event_end=pojoValue.getEvents_end_date();
        if(!str_event_end.equals("") ){
            holder.event_end_date.setVisibility(View.VISIBLE);
            holder.event_end_date.setText(str_event_end);
        }else{
            holder.event_end_date.setVisibility(View.GONE);
        }

        holder.event_name.setText(pojoValue.getEvents_name());
        //holder.textView_classifieds_area.setText(pojoValue.getClassifieds_area());
        //holder.textView_cls_person_mobile.setText(pojoValue.getClassifieds_contact_person_mobile());
        //older.textView_classified_description.setText(pojoValue.getClassifieds_description());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MasterFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView event_name,evnt_message,event_start_date,event_end_date;
        NetworkImageView Events_Image;
        ImageLoader imageLoader;

        public MasterFragmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Events_Image = (NetworkImageView) itemView.findViewById(R.id.events_image);
            imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();

            event_name = (TextView) itemView.findViewById(R.id.events_name_textview);
            //evnt_message = (TextView) itemView.findViewById(R.id.events_textview_message);
            event_start_date = (TextView) itemView.findViewById(R.id.events_start_date);
            event_end_date = (TextView) itemView.findViewById(R.id.events_end_date);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            // check if item still exists
            POJOValue content = arrayList.get(pos);
            str_event_id = content.getEvents_id();
            str_event_name=content.getEvents_name();
            Event_List_Details fragment = new Event_List_Details();
            Bundle bundle = new Bundle();
            FragmentTransaction fragmentTransaction = allEventsFragment.getActivity().getSupportFragmentManager().beginTransaction();
            bundle.putString("listing_id", str_event_id);
            bundle.putString("listing_category_name", str_event_name);
            fragment.setArguments(bundle);
            arrayList.clear();
            fragmentTransaction.replace(R.id.container_main, fragment).addToBackStack(null).commit();
        }
    }


}
