package com.example.mareklaskowski.cbc_xml;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

        //fill these placeholders later for more functionality
        //called by the framework, periodically, to provide updates on how the task is progressing
        protected void onProgressUpdate(Integer... progress){
            System.out.println("progress: "+ progress[0]);
        }
        //called by the framework once the task finishes
        protected void onPostExecute(Long result){
            //once finished all we do here is print out the headlines
            //you will extend this to refresh any views (if necessary)
            System.out.println("Downloaded "+ result + " files");
            for(String headline: headlines){ //for each headline in headlines
                System.out.println("Headline: " + headline);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare a URL object used to point to the xml resource
        URL url = null;
        try{
            url = new URL("http://www.cbc.ca/cmlink/rss-topstories");
        }catch (Exception e){
            Log.d("An exception happened", e.getMessage());
        }

        //use a custom AsyncTask to download and process the xml resource
        new DownloadFilesTask().execute(url);

    }

    public long downloadFile(URL url){

        //download the file in a try-catch block
        try{
            //create a new http url connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //do something with the data
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            //print out the xml for debugging purposes
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine = null;
            //java one-liner to read a line and make sure it's not null (EOF)
            while((inputLine = bufferedReader.readLine()) != null){
                Log.d("headlines", inputLine);

            }
        }catch (Exception e){
            Log.d("An exception happened", e.getMessage());
        }
        return 1;
    }
}
