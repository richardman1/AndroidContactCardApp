package com.example.richarddejongh.contactcardricharddejongh;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {

    private TextView name;
    private TextView age;
    private TextView email;
    private CircleImageView circle;

    private TextView infoTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentB.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentB newInstance(String param1, String param2) {
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentB() {
        // Required empty public constructor
        Log.i("FragmentB()", "Constructor");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        name = (TextView) view.findViewById(R.id.introduction);
        age = (TextView) view.findViewById(R.id.age);
        email = (TextView) view.findViewById(R.id.email);

        circle = (CircleImageView) view.findViewById(R.id.profile_image);
        Person p = new Person();
        if(savedInstanceState == null)
        {
//            p.name="Test";
//            p.age=20;
//            p.email="test@test.nl";
    }
        else
        {
            p = (Person) savedInstanceState.getSerializable("person");
        }



//        name.setText(name.getText() + " " + p.name);
//        age.setText(p.age + " years old");
//        email.setText(p.email);

        return view;
    }

    public void updateDetails(Person p)
    {
        if(p == null)
        {
            p = new Person();
//            p.name="test";
//            p.age=20;
            p.email="test@test.nl";
        }
        else
        {
//            Log.i("updateDetails()", p.name);
//            name.setText(name.getText() + " " + p.name);
//            age.setText(p.age + " years old");
            email.setText(p.email);
        }

    }
}
