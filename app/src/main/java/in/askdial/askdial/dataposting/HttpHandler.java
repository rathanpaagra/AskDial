package in.askdial.askdial.dataposting;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Admin on 21-Dec-16.
 */
public class HttpHandler {
    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

   /* // Build the URL - STEP 1
    URL urlObject = new URL(url);

    // Open connection with the server - STEP 2
    HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

    // Open an input stream - STEP 3
    InputStream inputStream = connection.getInputStream();

    // Create an input stream reader - STEP 4
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

    // Create a buffered reader to read the data more efficiently - STEP 5
    BufferedReader reader = new BufferedReader(inputStreamReader);

    // Read through the buffered reader and create string builder - STEP 6
    String line = reader.readLine();
    StringBuffer stringBuffer = new StringBuffer();

    while (line != null){
        stringBuffer.append(line);
        line = reader.readLine();
    }

    // Disconnect - STEP 7
    connection.disconnect();*/


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
