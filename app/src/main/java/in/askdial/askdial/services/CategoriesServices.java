package in.askdial.askdial.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashSet;

import in.askdial.askdial.dataposting.ConnectingTask;
import in.askdial.askdial.values.POJOValue;

/**
 * Created by Admin on 12-Jun-17.
 */

public class CategoriesServices extends Service{
    //public static final String PREFS_NAME = "MyPrefsFile";
    POJOValue detailsValue;
    ConnectingTask task;
    Thread searchthread;
    SharedPreferences settings;
    String search="search";
    public static HashSet<String> categoriesSearchset;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        detailsValue = new POJOValue();
        task = new ConnectingTask();
        categoriesSearchset = new HashSet<String>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("debug", "Staff Service Started");
        ConnectingTask.GetCategoriesSearchServices searchFeching = task.new GetCategoriesSearchServices(search,detailsValue, categoriesSearchset);
        searchFeching.execute();
        searchthread = null;
        Runnable runnable = new StaffData();
        searchthread = new Thread(runnable);
        searchthread.start();

        return Service.START_STICKY;
    }

    class StaffData implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Fetchsearch();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }

    private void Fetchsearch() {
        if (detailsValue.isCategoryAutosuggestList_Success()) {
            detailsValue.setCategoryAutosuggestList_Success(false);
            searchthread.interrupt();
            this.stopSelf();
        }
        if (detailsValue.isCategoryAutosuggestList_Failure()) {
            detailsValue.setCategoryAutosuggestList_Failure(false);
            searchthread.interrupt();
            this.stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        if (searchthread.isAlive()) {
            searchthread.interrupt();
            this.stopSelf();
        }
    }

}
