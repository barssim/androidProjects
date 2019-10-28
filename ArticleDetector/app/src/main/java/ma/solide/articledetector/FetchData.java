package ma.solide.articledetector;

import android.os.AsyncTask;

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

public class FetchData extends AsyncTask<Void,Void,Void> {
    String data = "";
    String singelParsed = "";
    String dataParsed = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://192.168.178.24:8080/articleDiscover/articles/123456");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line !=  null)
            {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject jo = new JSONObject(data);

                singelParsed ="artNo: " + "\n" + jo.get("artNo") + "\n"  +"\n"  +
                        "artName: " + "\n" + jo.get("artName") + "\n"  + "\n"  +
                        "artDestination: " + "\n" + jo.get("artDestination") + "\n"  +"\n"  +
                        "artPrice: " + "\n" + jo.get("artPrice") + "\n"  +"\n"  +
                        "artTransportCost: " + "\n" + jo.get("artTransportCost") + "\n"  ;

               dataParsed = dataParsed + singelParsed ;


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
        MainActivity.data.setText(this.dataParsed);
        }
}
