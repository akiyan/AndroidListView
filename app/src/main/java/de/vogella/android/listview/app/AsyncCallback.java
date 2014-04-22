package de.vogella.android.listview.app;

/**
 * Created by akiyan on 2014/04/21.
 */
public interface AsyncCallback {
    void onPreExecute();
    void onPostExecute(String result);
    void onProgressUpdate(int progress);
    void onCancelled();
}
