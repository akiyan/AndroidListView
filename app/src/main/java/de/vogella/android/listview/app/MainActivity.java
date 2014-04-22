package de.vogella.android.listview.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncGet asyncGet = new AsyncGet(new AsyncCallback() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(String result) {
                Log.d("onPostExecute", result);
                try {
                    JSONObject rootObject = new JSONObject(result);
                    JSONArray recipes = rootObject.getJSONObject("response").getJSONArray("recipes");
                    Log.d("recipes", recipes.toString(4));

                    final MainActivity mActivity = MainActivity.this;

                    ArrayList<HashMap> list = new ArrayList<HashMap>();
                    ListView listView = (ListView) findViewById(R.id.listView);

                    for (int i = 0; i < recipes.length(); i++) {
                        HashMap item = new HashMap();
                        item.put("title", recipes.getJSONObject(i).getString("title"));
                        item.put("subtitle", recipes.getJSONObject(i).getString("body"));
                        item.put("url", recipes.getJSONObject(i).getString("url"));
                        list.add(item);
                    }
                    CustomListItemAdapter adapter = new CustomListItemAdapter(mActivity, list);

                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            HashMap item = (HashMap) parent.getItemAtPosition(position);
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((String) item.get("url"))));
//                            Toast.makeText(mActivity, (String) item.get("title"), Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (JSONException e) {
                    //
                    Log.d("exception", e.toString());
                }
            }

            @Override
            public void onProgressUpdate(int progress) {

            }

            @Override
            public void onCancelled() {

            }
        });
        asyncGet.execute("http://api.nanapi.jp/v1/recipeSearchDetails/?key=4b542e23e43f6&format=json");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
