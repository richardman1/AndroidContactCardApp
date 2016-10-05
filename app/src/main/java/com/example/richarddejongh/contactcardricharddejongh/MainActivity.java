package com.example.richarddejongh.contactcardricharddejongh;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements FragmentA.OnFragmentInteractionListener {
//    View fragment_b;
//    View fragment_a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        fragment_b = (View) findViewById(R.id.fragment_b);
//        fragment_b.setVisibility(View.GONE);
//
//        fragment_a = (View) findViewById(R.id.fragment_a);
    }

    @Override
    public void onFragmentInteraction(Person p) {
//        Log.i("onFragmentInteraction", p.name);
        FragmentB details = (FragmentB) getFragmentManager().findFragmentById(R.id.fragment_b);
        if(details != null) {
            details.updateDetails(p);
        }
        else {
            FragmentB frag_b = new FragmentB();
            Bundle args = new Bundle();
            args.putSerializable("person", p);
            frag_b.setArguments(args);

//            FragmentA fragment_a = (FragmentA) getFragmentManager().findFragmentById(R.id.fragment_a);

            //
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.remove(fragment_a);
//            transaction.detach(fragment_a);
            transaction.replace(R.id.fragment_holder, frag_b);
            transaction.addToBackStack(null);

            // Commit
            transaction.commit();
//            fragment_b.setVisibility(View.VISIBLE);
//            fragment_a.setVisibility(View.GONE);

//            fragment_a.setLayoutParams(new TableLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, 1f));
        }
    }
}
