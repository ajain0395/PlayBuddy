package com.example.ashish.playbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class NewsAdminRecyclerViewFrag extends Fragment {

  //  my code
  private DatabaseReference myDatabase;
    public  List<News>  newsList = null;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    FloatingActionButton addNews;
    public static News selectedNews;
    public static String title,description;
    Database db;

    public static final String LOGTAG = "indus";
    //////
    private OnFragmentInteractionListener mListener;

    public NewsAdminRecyclerViewFrag() {
        // Required empty public constructor
    }

    public static NewsAdminRecyclerViewFrag newInstance(String param1, String param2) {
        NewsAdminRecyclerViewFrag fragment = new NewsAdminRecyclerViewFrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAG","passed layout created");
        myDatabase = FirebaseDatabase.getInstance().getReference();


    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("TAG","passed layout 0");
        View mview = inflater.inflate(R.layout.fragment_news_admin_recycler_view, container, false);

       prepareNewsData();
        //st
    /*    myDatabase.child("news").addValueEventListener(new ValueEventListener() {
            ArrayList<News>   data;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // News news=dataSnapshot.getValue(News.class);
                data = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    News news = new News();
                    news.setTitle(ds.getValue(News.class).getTitle());
                    news.setDescription(ds.getValue(News.class).getDescription());


                    //data.add(news.getTitle());
                    data.add(news);
                    //               Log.i("data", data.get(0).getTitle());
                }
                newsList=data;
                mAdapter = new MyAdapter(newsList);
               // mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w("error", "Failed to read value.", databaseError.toException());

            }
        });*/
        //til
        recyclerView = mview.findViewById(R.id.recycler_view);
        addNews=mview.findViewById(R.id.addNews);

     //   mAdapter = new MyAdapter(newsList);
       // mAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.i("TAG","passed layout 1");
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

// set the adapter
       // recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new myRecyclerViewListner(getActivity(), recyclerView, new myRecyclerViewListner.ClickListener() {


            public void onClick(View view, int position) {
                selectedNews = newsList.get(position);



                AdminNewsFrag fr = new AdminNewsFrag();

                FragmentManager fm = getFragmentManager();

                fm.beginTransaction().replace(R.id.frame_container,fr).addToBackStack(fr.getClass().getName()).commit();


               // Toast.makeText(getActivity(), news + "", Toast.LENGTH_SHORT).show();
            }

            public void onLongClick(View view, int position) {

            }
        }));

        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = new String("");
                description = new String("");


                AdminNewsFrag fr = new AdminNewsFrag();

                FragmentManager fm = getFragmentManager();

                fm.beginTransaction().replace(R.id.frame_container,fr).addToBackStack(fr.getClass().getName()).commit();

                /* Intent intent=new Intent(getActivity(),AdminNewsFrag.class);
                intent.putExtra("title","");
                intent.putExtra("description","");
                intent.putExtra("flag",1);
                startActivity(intent);*/
                //getActivity().finish();
            }
        });


        return mview;
    }

    private void prepareNewsData() {

        myDatabase.child("news").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                newsList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    News news = new News();
                    try {


                        news.setTitle(ds.getValue(News.class).getTitle());
                        news.setDescription(ds.getValue(News.class).getDescription());

                    }
                    catch (Exception e)
                    {
                        indusLog("Exception in fetching from Db");
                        e.printStackTrace();
                    }


                    newsList.add(news);

                }

                mAdapter = new MyAdapter(newsList);
               
                recyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w("error", "Failed to read value.", databaseError.toException());

            }
        });
//        db=new Database();
  //      db.readNews();

        //edit code

                //till here


             //   if(Database.allNews != null && Database.allNews.size() >0)
        //newsList=Database.allNews;
      //  Log.i("news",newsList.get(2).title);

    /*if (mAdapter!= null)
        mAdapter.notifyDataSetChanged();*/


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

    public void indusLog(String message)
    {
        Log.i(LOGTAG,message);
    }

    public  void indusToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

        indusLog(message);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
