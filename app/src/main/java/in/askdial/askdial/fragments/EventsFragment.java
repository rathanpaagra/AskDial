package in.askdial.askdial.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import in.askdial.askdial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment  implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    SliderLayout sliderLayout;
    HashMap<String,Integer> file_maps = new HashMap<String, Integer>();

    View view;
    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Events");

        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_events, container, false);
        if (getArguments() != null) {

            Toast.makeText(getActivity(), getArguments().getString("message"), Toast.LENGTH_SHORT).show();
        }


        file_maps= new HashMap<String, Integer>();
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);

        file_maps.put("MRR Campus",R.drawable.app_ico);
        file_maps.put("MRR Accomadation",R.drawable.app_ico2);
        file_maps.put("MRR Gate",R.drawable.app_ico3);




        for(String name : file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(1500);
        sliderLayout.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) this);
        return view;
    }

    @Override
    public void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    public void onSliderClick(BaseSliderView slider) {

        slider.getBundle().getStringArrayList(String.valueOf(R.drawable.app_ico6));

        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}