package com.example.ashish.playbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdminNewsFrag extends Fragment {

    //xml elemets
    Button save,cancel,remove;
    EditText title,description;
    Database db;


    private OnFragmentInteractionListener mListener;

    public AdminNewsFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new Database();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_admin_news, container, false);
        title=rootview.findViewById(R.id.title);
       // title.setText(NewsAdminRecyclerViewFrag.title.toLowerCase());

        description=rootview.findViewById(R.id.description);
       // description.setText(NewsAdminRecyclerViewFrag.description);
        save=rootview.findViewById(R.id.save);
        cancel=rootview.findViewById(R.id.cancle);
        remove=rootview.findViewById(R.id.remove);

        if(!NewsAdminRecyclerViewFrag.title.equals(""))
        {
            remove.setVisibility(View.VISIBLE);
            //fill here
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String heading=title.getText().toString();
                String desc=description.getText().toString();

                if(heading.isEmpty() && desc.isEmpty()) {
                    Toast.makeText(getContext(),"Please fill the fields!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    News news=new News(heading,desc);
                    db.write(news, "news");

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText(null);
                description.setText(null);

            }
        });

        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
