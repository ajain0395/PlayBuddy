package com.example.ashish.playbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AdminVenueFragment extends Fragment {

   // private OnFragmentInteractionListener mListener;
   //log declaration
   public static final String LOGTAG = "indus";

    //xml data
    private Button save,cancel,remove;
    private EditText venueEdit;
    private Spinner sportsSpinner;
    private VenueDatabase venueDatabase;
    private List<Sport> sportsList = null;
    private List<String> sportNameList=null;
    private DatabaseReference myDatabase;
    private ArrayAdapter<String> adapter;
    //private  SportDatabase sportDatabase;
    private int spinnerPosition;

    public AdminVenueFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        venueDatabase=new VenueDatabase();
        myDatabase = FirebaseDatabase.getInstance().getReference();
        //sportDatabase=new SportDatabase();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View venueView= inflater.inflate(R.layout.fragment_admin_venue, container, false);

        save=venueView.findViewById(R.id.venuesave);
        cancel=venueView.findViewById(R.id.venuecancle);
        venueEdit=venueView.findViewById(R.id.venueEdit);
        sportsSpinner=venueView.findViewById(R.id.venue_sportlist);

        //read data on spinner
        prepareSportsData();

        sportsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                indusLog("position : "+i);
                 spinnerPosition=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String venueName=venueEdit.getText().toString();
                String sportsId=sportsList.get(spinnerPosition).getSportId();

                if(venueName.length()==0)
                {      indusLog("venue not given");
                    Toast.makeText(getActivity(), "Please fill the Venue!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Venue venue=new Venue(venueName,sportsId);
                    venueDatabase.write(venue,"venue");
                    indusLog("venue database written successfully");

                }


            }
        });

        //cancel to go back
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return venueView;
    }


    //to read data from database and set it to recyclerView Adapter
    private void prepareSportsData() {

        myDatabase.child("sports").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sportsList = new ArrayList<>();
                sportNameList=new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Sport sport=new Sport();

                    try {

                        sport.setSportId(ds.getValue(Sport.class).getSportId());
                        sport.setSportName(ds.getValue(Sport.class).getSportName());


                    }
                    catch (Exception e)
                    {
                        indusLog("Exception in fetching from Db");
                        e.printStackTrace();
                    }

                    sportNameList.add(sport.getSportName());
                    sportsList.add(sport);


                }



                if(adapter!=null)
                {
                    //          indusToast(getActivity(),"new news added");
                }
                //spinner adapter
                adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,
                        sportNameList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);





                //set the adapter on the spinner
                sportsSpinner.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w("error", "Failed to read value.", databaseError.toException());

            }
        });

    }

    public void indusLog(String message)
    {
        Log.i(LOGTAG,message);
    }

    public  void indusToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        indusLog(message);
    }

   /* // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
