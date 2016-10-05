package com.example.richarddejongh.contactcardricharddejongh;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Richard de Jongh on 27-9-2016.
 */
public class PersonAPIHelper extends AsyncTask<String, Void, String> {

    private static final String TAG = "AsyncCallRandomUser";

    private RandomPersonGenerated listener = null;

    public PersonAPIHelper(RandomPersonGenerated listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.i(TAG, "doInBackground");
        return this.getRandomPersonString();
    }

    @Override
    protected void onPostExecute(String result) {
//        Log.i(TAG, "onPostExecute");
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(result);
            JSONArray users = jsonObject.getJSONArray("results");
            for (int idx = 0; idx < users.length(); idx++) {
                JSONObject array = users.getJSONObject(idx);

                JSONObject name = array.getJSONObject("name");
                String title = name.getString("title");
                String firstName = name.getString("first");
                String lastName = name.getString("last");

                String gender = array.getString("gender");

                JSONObject picture = array.getJSONObject("picture");
                String largeImgUrl = picture.getString("large");
                String mediumImgUrl = picture.getString("medium");
                String thumbnailUrl = picture.getString("thumbnail");

                String email = array.getString("email");

                JSONObject location = array.getJSONObject("location");
                String street = location.getString("street");
                String city = location.getString("city");
                String zip = location.getString("postcode");

                String phone = array.getString("phone");
                String cell = array.getString("cell");

                JSONObject id = array.getJSONObject("id");
                String bsn = id.getString("value");

                String nationality = array.getString("nat");

                String dateOfBirth = array.getString("dob");

                Person person = new Person();
                person.setTitle(title);
                person.setFirstName(firstName);
                person.setLastName(lastName);

                person.setGender(gender);

                person.setLargePictureUrl(largeImgUrl);
                person.setMediumPictureUrl(mediumImgUrl);
                person.setThumbnailUrl(thumbnailUrl);

                person.setEmail(email);

                person.setDateOfBirth(dateOfBirth);

                person.setStreet(street);
                person.setCity(city);
                person.setZip(zip);

                person.setPhone(phone);
                person.setCell(cell);

                person.setBsn(bsn);

                person.setNationality(nationality);

                listener.randomPersonGenerated(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getRandomPersonString() {
        String response = "";
        try {
            InputStream inputStream = null;
            URL url = new URL("https://randomuser.me/api/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int result = urlConnection.getResponseCode();
            if (result == HttpURLConnection.HTTP_OK) {
                Log.i("http_ok", "");
                inputStream = urlConnection.getInputStream();
                response = this.getStringFromInputStream(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return response;
    }

    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public interface RandomPersonGenerated {
        void randomPersonGenerated(Person person);
    }
}

