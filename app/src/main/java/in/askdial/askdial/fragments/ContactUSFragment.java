package in.askdial.askdial.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.askdial.askdial.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUSFragment extends Fragment {

    WebView webView;
    String htmlText;
    View view;
    ImageView img;

    public ContactUSFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Contact US");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact_u, container, false);
        if (getArguments() != null) {
            Toast.makeText(getActivity(), getArguments().getString("message"), Toast.LENGTH_SHORT).show();
        }

        img = (ImageView) view.findViewById(R.id.google_map);

        img.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.co.in/maps/dir/''/askdial/@12.9568016,77.4884135,12z/data=!3m1!4b1!4m8!4m7!1m0!1m5!1m1!1s0x3bae3e0307b57501:0xb8057f608217df38!2m2!1d77.558454!2d12.956811");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                WebView webview = new WebView(getActivity());
                // Simplest usage: note that an exception will NOT be thrown
                // if there is an error loading this page (see below).
                webview.loadUrl("http://google.co.in/");

                // OR, you can also load from an HTML string:
                String summary = "<html><body>AskDial<b>192</b>Map</body></html>";
                webview.loadData(summary, "text/html", null);
                // ... although note that there are restrictions on what this HTML can do.
                // See the JavaDocs for loadData() and loadDataWithBaseURL() for more info.

            }
        });

        return view;
    }

    //region worked code
    //MAP
        // htmlText= "<iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://www.google.co.in/maps/dir/''/askdial/data=!4m5!4m4!1m0!1m2!1m1!1s0x3bae3e0307b57501:0xb8057f608217df38?sa=X&ved=0ahUKEwjpg_iCwsbRAhVJRo8KHbNUC1QQ9RcIYjAL\" ></iframe>";
        // webView = (WebView) view.findViewById(R.id.webView_map);

        /*webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadData(htmlText,"text/html",null);
            }
        });
*/
        /*webView = (WebView) view.findViewById(R.id.webView_map);
        webView.setBackgroundColor(Color.BLACK);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        webView.loadUrl("www.google.co.in/maps/dir/''/askdial/data=!4m5!4m4!1m0!1m2!1m1!1s0x3bae3e0307b57501:0xb8057f608217df38?sa=X&ved=0ahUKEwjpg_iCwsbRAhVJRo8KHbNUC1QQ9RcIYjAL");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg)
            {
                WebView newWebView = new WebView(view.getContext());
                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                transport.setWebView(newWebView);
                resultMsg.sendToTarget();
                return true;
            }
        });
*/
    //endregion

    private boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            /*new loadListView().execute();*/
            return true;
        } else {
            Toast.makeText(getActivity(), "Internet Connection Required", Toast.LENGTH_LONG).show();


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            getActivity();
                            //System.exit(0);
                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

        return false;
    }


}
