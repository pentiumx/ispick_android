package com.example.pentiumx.ispickandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    TextView mainTextView;
    Button mainButton;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Fetch images using JSON API
        new RetrieveImagesTask().execute("");


        // 1. Access the TextView definedz in layout XML
        // and then set its text
        mainTextView = (TextView) findViewById(R.id.main_textview);


        // 2. Access the Button defined in layout XML
        // and listen for it here
        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);

        // 4. Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);

        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        // 5. Set this activity to react to list items being pressed
        mainListView.setOnItemClickListener(this);
    }

    private class RetrieveImagesTask extends AsyncTask<String, Void, String> {
        private Exception exception;

        protected String doInBackground(String... urls) {
            String parsedString = "";

            try {

                URL url = new URL("http://ispicks.com/users/rss.json?site=pixiv&site_name=pixiv&p=1");
                URLConnection conn = url.openConnection();

                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                InputStream is = httpConn.getInputStream();
                //parsedString = convertinputStreamToString(is);
                parsedString = slurp(is, 2048);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return parsedString;
        }

        private String slurp(final InputStream is, final int bufferSize) {
            final char[] buffer = new char[bufferSize];
            final StringBuilder out = new StringBuilder();
            try {
                Reader in = new InputStreamReader(is, "UTF-8");
                for (; ; ) {
                    int rsz = in.read(buffer, 0, buffer.length);
                    if (rsz < 0)
                        break;
                    out.append(buffer, 0, rsz);
                }
            } catch (UnsupportedEncodingException ex) {
            } catch (IOException ex) {
            }
            return out.toString();
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed

            try {
                TextView mainTextView = (TextView) findViewById(R.id.main_textview);
                mainTextView.setText(result);
            } catch (Exception e) {

            }
            super.onPostExecute(result);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // Test the Button
        mainTextView.setText("Button pressed!");

        // Also add that value to the list shown in the ListView
        mNameList.add(nextSessionId().toString());
        mArrayAdapter.notifyDataSetChanged();
    }

    private String nextSessionId() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        // Log the item's position and contents
        // to the console in Debug
        // Only logs when in debugger mode!
        Log.d("omg android", position + ": " + mNameList.get(position));
    }
}
