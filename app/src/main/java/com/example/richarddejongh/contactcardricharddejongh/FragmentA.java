package com.example.richarddejongh.contactcardricharddejongh;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentA.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment implements ListView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView;
    ArrayList<Person> persons = new ArrayList<Person>();
    PersonAdapter arrayAdapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentA.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentA newInstance(String param1, String param2) {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentA() {
        // Required empty public constructor
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

        Log.i("onCreateView()", "");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        listView = (ListView) view.findViewById(R.id.personListView);

//        Person p = new Person();
//        p.name = "Richard de Jongh"; p.age = 21; p.email = "richarddejongh1995@gmail.com";
//        persons.add(p);
//
//        p = new Person();
//        p.name = "Frits westie"; p.age = 47; p.email = "frits@gmail.com";
//        persons.add(p);
//
//        p = new Person();
//        p.name = "Opa Henk"; p.age = 58; p.email = "henkieipenkie@gmail.com";
//        persons.add(p);
//
//        p = new Person();
//        p.name = "Opa Henk"; p.age = 58; p.email = "henkieipenkie@gmail.com";
//        persons.add(p);
//
//        p = new Person();
//        p.name = "Opa Henk"; p.age = 58; p.email = "henkieipenkie@gmail.com";
//        persons.add(p);
//
//        p = new Person();
//        p.name = "Opa Henk"; p.age = 58; p.email = "henkieipenkie@gmail.com";
//        persons.add(p);
//
//        p = new Person();
//        p.name = "Opa Henk"; p.age = 58; p.email = "henkieipenkie@gmail.com";
//        persons.add(p);
//
//        p = new Person();
//        p.name = "Opa Henk"; p.age = 58; p.email = "henkieipenkie@gmail.com";
//        persons.add(p);

//        arrayAdapter  = new PersonAdapter(this, inflater, persons);
        listView.setAdapter(arrayAdapter);

        arrayAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener ...");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("onItemClick()", "");
        if(mListener != null){
            mListener.onFragmentInteraction(persons.get(position));
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Person p);
    }

}
