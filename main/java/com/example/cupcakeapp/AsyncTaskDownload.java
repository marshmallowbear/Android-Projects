package com.example.cupcakeapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class AsyncTaskDownload extends AsyncTask<String, Object, Void> {
   private WeakReference<Activity> activityWR = null;
   private TaskCompleted in;
   int[] imgViewIds;

   public AsyncTaskDownload(Activity a, TaskCompleted i, int[] ivIds){
       activityWR = new WeakReference<>(a);
       in = i;
       imgViewIds = ivIds;
   }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(activityWR != null){
            Activity a = activityWR.get();
            ProgressBar bar = a.findViewById(R.id.progressBar);
            bar.setVisibility(View.VISIBLE);
            TextView downloadTextView = a.findViewById(R.id.downloadTextView);
            downloadTextView.setText("fetching....");
        }

    }

    @Override
    protected Void doInBackground(String... strings) {
       //call method to get urls
        String[] urls = getImageUrls(strings[0]);

       for(int i=0; i<urls.length; i++){
           try{
               //convert string to URL first
               URL url = new URL(urls[i]);
               //get a http conn object
               HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
               conn.setDoInput(true);
               //conn.open
               conn.connect();
/*               try {
                   Thread.sleep(15000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }*/
               //start download; collect as input stream
               InputStream input = conn.getInputStream();
               Bitmap result = BitmapFactory.decodeStream(input);
               if(!isCancelled()){
                   publishProgress(i+1, result);
               }else{
                   return null;
               }
               //return result;
           }
           catch(Exception e){
               System.out.println("Exception caught in downloading!");
           }

       }
        return null;
    }

    private String[] getImageUrls(String string) {
        String htmlDoc = null;
        String[] results = new String[20];
        int count = 0;

        try {
            URL source = new URL(string);
            HttpURLConnection conn = (HttpURLConnection) source.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            conn.addRequestProperty("User-Agent", "Mozilla/4.76");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                htmlDoc += line;
            }
            in.close();

            String regex = "https?:/(?:/[^/]+)+\\.(?:jpg)";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(htmlDoc);
            while(matcher.find() && count <20) {
                String str_matched = htmlDoc.substring(matcher.start(), matcher.end());

                results[count] = str_matched;
                count++;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return results;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        if(activityWR != null){

            Activity a = activityWR.get();
            ImageView iv = a.findViewById(imgViewIds[(int)values[0]-1]);
            int dimension = iv.getHeight();
            Bitmap bm = (Bitmap)values[1];
            //float factor = 130/(float)bm.getWidth();
            Bitmap resized = scaleToFill(bm, dimension);
                    //Bitmap.createScaledBitmap(bm, 130, (int)(bm.getHeight()*factor),false);
            iv.setImageBitmap(resized);
            ProgressBar bar = a.findViewById(R.id.progressBar);
            bar.setProgress((Integer)values[0]*5);
            TextView downloadTextView = a.findViewById(R.id.downloadTextView);
            downloadTextView.setText("Downloading " + values[0].toString() + " of 20 images" );

        }
    }

    private Bitmap scaleToFill(Bitmap b, int dimension){
        float factorH = 150 / (float) b.getHeight();
        float factorW = 150 / (float) b.getWidth();
        float factorToUse = (factorH < factorW) ? factorW : factorH;
        return Bitmap.createScaledBitmap(b, (int) (b.getWidth() * factorToUse),
                (int) (b.getHeight() * factorToUse), false);
    }

    @Override
    protected void onPostExecute(Void v) {
        if(activityWR != null){
            Activity a = activityWR.get();
            ProgressBar bar = a.findViewById(R.id.progressBar);
            bar.setVisibility(View.GONE);
            TextView downloadTextView = a.findViewById(R.id.downloadTextView);
            downloadTextView.setText("");
            //enable on click listeners to images
            in.updateComplete();
        }
    }
}
