package com.example.finalproject.ui.home;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.fragment.app.Fragment;

import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void> {
    Fragment one;
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    String[] players = new String[10];

    public fetchData(Fragment one)
    {this.one = one;}

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.myjson.com/bins/uixzi");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i =0 ;i <JA.length(); i++){
                JSONObject jija = (JSONObject) JA.get(i);

                singleParsed = "NAME:" + jija.get("name") + "\n"+
                        "Age:" + jija.get("age") + "\n"+
                        "Nation:" + jija.get("national_side") + "\n"+
                        "Ranking:" + jija.get("batting_rank") + "\n"+
                        "Man of the match:" + jija.get("man_of_the_match") + "\n";
                players[i] = singleParsed;
                dataParsed = dataParsed + singleParsed +"\n" ;
//                dataParsed = players[0];
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        HomeFragment.data.setText(this.dataParsed);

       ArrayAdapter adapter = new ArrayAdapter<String>(one.getActivity() , R.layout.list_item,R.id.tv, players);
       HomeFragment.list.setAdapter(adapter);
    }
}