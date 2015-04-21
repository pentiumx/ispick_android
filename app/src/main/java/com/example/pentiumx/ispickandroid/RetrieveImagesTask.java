package com.example.pentiumx.ispickandroid;

import android.os.AsyncTask;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by pentiumx on 4/20/15.
 */
/*public class RetrieveImagesTask extends AsyncTask<String, Void, String> {
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

    public static String slurp(final InputStream is, final int bufferSize)
    {
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        try {
            Reader in = new InputStreamReader(is, "UTF-8");
            for (;;) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
        }
        catch (UnsupportedEncodingException ex) {
        }
        catch (IOException ex) {
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
*/