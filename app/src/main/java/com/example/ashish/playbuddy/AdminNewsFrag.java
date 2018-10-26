package com.example.ashish.playbuddy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;


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

        //find elements on view.
        title=rootview.findViewById(R.id.title);
        description=rootview.findViewById(R.id.description);
        save=rootview.findViewById(R.id.save);
        cancel=rootview.findViewById(R.id.cancle);
        remove=rootview.findViewById(R.id.remove);


        if(NewsAdminRecyclerViewFrag.selectedNews !=null)
        {
          //  remove.setVisibility(View.VISIBLE);
            //save.setVisibility(View.INVISIBLE);
            remove.setVisibility(View.VISIBLE);
            title.setText(NewsAdminRecyclerViewFrag.selectedNews.getTitle());
            description.setText(NewsAdminRecyclerViewFrag.selectedNews.getDescription());
           // Toast.makeText(getActivity(), ""+NewsAdminRecyclerViewFrag.selectedNews.getNews_id(), Toast.LENGTH_SHORT).show();
            //fill here
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(NewsAdminRecyclerViewFrag.selectedNews!=null)
                {
                    String updatedDesc=description.getText().toString();
                    String updatedTitle=title.getText().toString();
                    db.updateNews(NewsAdminRecyclerViewFrag.selectedNews.getNews_id(),updatedDesc,updatedTitle);
                    Toast.makeText(getActivity(), "News Updated Successfully!!"+NewsAdminRecyclerViewFrag.selectedNews.getNews_id(), Toast.LENGTH_SHORT).show();
                    callNewsAdminRecyclerViewFrag();
                }


                else {
                    String heading = title.getText().toString();
                    String desc = description.getText().toString();

                    if (heading.length() == 0 && desc.length() == 0) {
                        Toast.makeText(getActivity(), "Please fill the fields!!", Toast.LENGTH_SHORT).show();
                    } else {
                        News news = new News(heading, desc, new Date());
                        db.write(news, "news");
                        callNewsAdminRecyclerViewFrag();
                    }
                }

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.remove(NewsAdminRecyclerViewFrag.selectedNews.getNews_id());
                Toast.makeText(getActivity(), "News Removed Successfully!!", Toast.LENGTH_SHORT).show();
                callNewsAdminRecyclerViewFrag();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText(null);
                description.setText(null);
                NewsAdminRecyclerViewFrag.selectedNews=null;
                callNewsAdminRecyclerViewFrag();

            }
        });

        return rootview;
    }

    void callNewsAdminRecyclerViewFrag()
    {
        NewsAdminRecyclerViewFrag fr=new NewsAdminRecyclerViewFrag();

        FragmentManager fm = getFragmentManager();

        fm.beginTransaction().replace(R.id.frame_container,fr).commit();

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
