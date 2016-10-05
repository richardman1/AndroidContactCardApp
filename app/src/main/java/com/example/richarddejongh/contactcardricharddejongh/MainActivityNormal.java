package com.example.richarddejongh.contactcardricharddejongh;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.text.AllCapsTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivityNormal extends AppCompatActivity implements AdapterView.OnItemClickListener, PersonAPIHelper.RandomPersonGenerated{

    ListView mPersonListView;
    //ArrayAdapter mArrayAdapter;
    PersonAdapter mPersonAdapter;
    ArrayList mPersonList = new ArrayList();

    private PersonHelper personHelper;

    private PersonAPIHelper personAPIHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_normal);

        personHelper = new PersonHelper(getApplicationContext(), null, null, 1);

        mPersonList = personHelper.getPersons();


        if(mPersonList.size() < 20){
            int difference = 20 - mPersonList.size();
            for(int i = 0; i < difference; i++){
                personAPIHelper = new PersonAPIHelper(this);
                personAPIHelper.execute();
            }
        }
        Log.i("eindlijst", Integer.toString(mPersonList.size()));
        mPersonListView = (ListView) findViewById(R.id.personListView);

        // Koppel list aan
        mPersonAdapter = new PersonAdapter(this,
                getLayoutInflater(),
                mPersonList);
        mPersonListView.setAdapter(mPersonAdapter);

        // Activate adapter, kan dan ook in een button, of network update
        mPersonAdapter.notifyDataSetChanged();

        // Enable listener
        mPersonListView.setOnItemClickListener(this);
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

    //
    // Click on selected item in list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getApplicationContext(), AllcontactActivity.class);
        Person person = (Person) this.mPersonList.get(position);
        i.putExtra("person", person);
        startActivity(i);
    }

    @Override
    public void randomPersonGenerated(Person person) {
        mPersonList.add(person);
        mPersonAdapter.notifyDataSetChanged();
        Log.i("size", Integer.toString(mPersonList.size()));
        personHelper.addPerson(person);
    }
}
