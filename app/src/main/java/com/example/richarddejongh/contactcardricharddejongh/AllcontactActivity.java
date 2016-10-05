package com.example.richarddejongh.contactcardricharddejongh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllcontactActivity extends AppCompatActivity{

    private TextView name;
    private TextView age;
    private TextView email;
    private CircleImageView circle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contact);

        name = (TextView) findViewById(R.id.introduction);
        age = (TextView) findViewById(R.id.age);
        email = (TextView) findViewById(R.id.email);
        circle = (CircleImageView) findViewById(R.id.profile_image);

        Intent i = getIntent();

        Person p = (Person) i.getSerializableExtra("person");

        name.setText(name.getText() + " " + p.firstName);
        age.setText(p.dateOfBirth + " years old");
        email.setText(p.email);
        new DownloadImageTask(circle)
                .execute(p.getLargePictureUrl());
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

