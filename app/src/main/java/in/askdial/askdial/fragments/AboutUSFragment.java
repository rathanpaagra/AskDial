package in.askdial.askdial.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import in.askdial.askdial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUSFragment extends Fragment {

    View view;
    WebView webView9;
    String htmlText9;
    WebView webView1,webView2,webView3,webView4,webView5,webView6,webView7,webView8;
    public AboutUSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getActivity().setTitle("About US");
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_aboutus, container, false);
        if (getArguments() != null) {
            Toast.makeText(getActivity(), getArguments().getString("message"), Toast.LENGTH_SHORT).show();
        }
        String myData = "message";

        String htmlText1 = "<html><body style=\"text-align:justify\">ASK DIAL Limited is a leading firm that is a prominent service provider of services ranging from the web designing to search engine optimization services. With years of experience of working in the field of web design and development, we can come up to the expectations of our clients. Our web designers and professionals can turn the ideas of our clients in to reality of developing a strong an eye catching website. </body></Html>";
        String htmlText2 = "<html><body style=\"text-align:justify\">In this Digital era, the technology innovates itself to provide access to even small bit of information in every possible way and to have a successful growing business one must adapt to latest technologies so that your business reach out to maximum number of users . Current technology is changing rapidly with many devices coming in market with different screen sizes and resolutions so it is must have situation where online business adapt to latest technology quickly.</body></Html>";
        String htmlText3 = "<html><body style=\"text-align:justify\">ASK DIAL Limited is a leading firm that is a prominent service provider of services ranging from the web designing to search engine optimization services. With years of experience of working in the field of web design and development, we can come up to the expectations of our clients. Our web designers and professionals can turn the ideas of our clients in to reality of developing a strong an eye catching website.</body></Html>";
        String htmlText4 = "<html><body style=\"text-align:justify\">We are a web page design and development company with a proven track record of more than eight years producing exemplary digital design for a diverse clientele. We’re a passionate creative team who combine fierce creativity with an extensive understanding of the world of digital commerce. Our talented team is always abreast of the latest digital trends whilst maintain a well-grounded understanding of staple web development concepts which lead to a successful site.</body></Html>";
        String htmlText5 = "<html><body style=\"text-align:justify\">ASK DIAL Limited has a team of skilled professionals focused on providing quality work. Our expertise lies in a wide range of development services. We apply a holistic approach towards business and provide services at personalized level and complete projects through a combination of professionalism, passion and perfection.</body></Html>";
        String htmlText6 = "<html><body style=\"text-align:justify\">Our team takes a unique approach to each project they work on; instead of trying to force clients into a one size fits all templates, our web designers take the time to understand the client’s vision for their brand and the philosophy behind it.</body></Html>";
        String htmlText7 = "<html><body style=\"text-align:justify\">We understand the value and requirements for presentation to any industry especially in IT industry. Due to this, our web design and mobile application development services in this field give a one door solutions to all your needs.</body></Html>";
        String htmlText8 = "<html><body style=\"text-align:justify\">At ASK DIAL Limited, we’re dedicated to developing long term relationships with all of our clients. We have developed a loyal customer base who’ve grown as we have and spans both private and public sectors. We have worked to implement strategic planning so that as the business grows, their digital presence does too.</body></Html>";

        WebView webView1 = (WebView) view.findViewById(R.id.webView1);
        WebView webView2 = (WebView) view.findViewById(R.id.webView2);
        WebView webView3 = (WebView) view.findViewById(R.id.webView3);
        WebView webView4 = (WebView) view.findViewById(R.id.webView4);
        WebView webView5 = (WebView) view.findViewById(R.id.webView5);
        WebView webView6 = (WebView) view.findViewById(R.id.webView6);
        WebView webView7 = (WebView) view.findViewById(R.id.webView7);
        WebView webView8 = (WebView) view.findViewById(R.id.webView8);


        webView1.loadData(String.format(htmlText1, myData), "text/html", "utf-8");
        webView2.loadData(String.format(htmlText2, myData), "text/html", "utf-8");
        webView3.loadData(String.format(htmlText3, myData), "text/html", "utf-8");
        webView4.loadData(String.format(htmlText4, myData), "text/html", "utf-8");
        webView5.loadData(String.format(htmlText5, myData), "text/html", "utf-8");
        webView6.loadData(String.format(htmlText6, myData), "text/html", "utf-8");
        webView7.loadData(String.format(htmlText7, myData), "text/html", "utf-8");
        webView8.loadData(String.format(htmlText8, myData), "text/html", "utf-8");


        return view;

    }

}
