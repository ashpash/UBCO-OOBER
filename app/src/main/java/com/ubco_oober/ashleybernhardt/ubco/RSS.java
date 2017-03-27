package com.ubco_oober.ashleybernhardt.ubco;

/**
 * Created by Ashley on 3/19/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.renderscript.Allocation;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.data;

public class RSS extends Activity {


    ListView listview;
    ArrayAdapter<String> adapter;
    final String address = "https://ubco-oober.000webhostapp.com/populateRSS.php";
    InputStream Is = null;
    String line = null;
    String result = null;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_rss);

        listview = (ListView) findViewById(R.id.ListView);

        ///Part not normally in main activity
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        getInfo();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get selected item text
                String item = (String) adapterView.getItemAtPosition(i);
                String studentEmail = item.substring(item.indexOf("(") + 1, item.indexOf(")"));
                Intent intent = new Intent(RSS.this, RideInfo.class);
                intent.putExtra("studentEmail", studentEmail);
                RSS.this.startActivity(intent);

                // Display the selected item
                //Toast.makeText(mContext,"Selected : " + item,Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getInfo() {
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            Is = new BufferedInputStream(con.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(Is));
            StringBuilder str = new StringBuilder();

            while ((line = br.readLine()) != null) {
                str.append(line + "\n");
            }
            Is.close();
            result = str.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            JSONArray js = new JSONArray(result);
            JSONObject jo = null;

            data = new String[js.length()];

            for (int x = 0; x < js.length(); x++) {
                jo = js.getJSONObject(x);
                data[x] = "Email:("+jo.getString("studentEmail")+")"+"\n"+jo.getString("destination") + "\n" + jo.getString("dateForm") + "\n" + jo.getString("timeForm") + "\n" + jo.getInt("space");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
