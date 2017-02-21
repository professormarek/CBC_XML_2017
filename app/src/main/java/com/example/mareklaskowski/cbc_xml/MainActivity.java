package com.example.mareklaskowski.cbc_xml;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.URL;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    //for now declare a Vector of strings to hold headlines
    //think about where to put this in your actual workshop
    Vector<String> headlines = new Vector<String>();

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected Long doInBackground(URL... params) {
            int count = params.length;//how many urls we have to process
            long totalSize = 0; //count files downloaded
            //loop to download each file
            for (int i = 0; i < count; i++){
                //download the file
                //call a function to download the file
                totalSize += downloadFile(params[i]);
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare a URL object used to point to the xml resource
        URL url = null;
        try{
            url = new URL("http://www.cbc.ca/cmlink/rss-topstories.xml");
        }catch (Exception e){
            Log.d("An exception happened", e.getMessage());
        }

        //use a custom AsyncTask to download and process the xml resource
        new DownloadFilesTask().execute(url);
        
    }
    //TODO: finish this method!
    public long downloadFile(URL url){
        return 0;
    }
}
