package com.example.ashish.playbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;


public class NewsAdminRecyclerViewFrag extends Fragment {

  //  my code
    private List<String> newsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    FloatingActionButton addNews;
    public static String title,description;

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

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("TAG","passed layout 0");
        View mview = inflater.inflate(R.layout.fragment_news_admin_recycler_view, container, false);


        recyclerView = mview.findViewById(R.id.recycler_view);
        addNews=mview.findViewById(R.id.addNews);

        mAdapter = new MyAdapter(newsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.i("TAG","passed layout 1");
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

// set the adapter
        recyclerView.setAdapter(mAdapter);

        prepareNewsData();

        recyclerView.addOnItemTouchListener(new myRecyclerViewListner(getActivity(), recyclerView, new myRecyclerViewListner.ClickListener() {


            public void onClick(View view, int position) {
                String news = newsList.get(position);

                title = new String(news);


                AdminNewsFrag fr = new AdminNewsFrag();

                FragmentManager fm = getFragmentManager();

                fm.beginTransaction().replace(R.id.frame_container,fr).addToBackStack(fr.getClass().getName()).commit();


                Toast.makeText(getActivity(), news + "", Toast.LENGTH_SHORT).show();
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

        newsList.add(new String("News 1"));
        newsList.add(new String("News 2"));
        newsList.add(new String("News 3"));
        newsList.add(new String("News 4"));
        newsList.add(new String("News 5"));
        newsList.add(new String("News 6"));
        newsList.add(new String("News 7"));
        newsList.add(new String("News 8"));
        newsList.add(new String("News 9"));
        newsList.add(new String("News 10"));
        newsList.add(new String("News 11"));
        newsList.add(new String("News 12"));
        newsList.add(new String("News 13"));
        newsList.add(new String("News 14"));
        newsList.add(new String("News 15"));
        newsList.add(new String("News 16"));
        newsList.add(new String("News 17"));
        newsList.add(new String("News 21"));
        newsList.add(new String("News 22"));
        newsList.add(new String("News 23"));
        newsList.add(new String("News 24"));
        newsList.add(new String("News 25"));
        newsList.add(new String("News 26"));
        newsList.add(new String("News 27"));
        newsList.add(new String("News 28"));
        newsList.add(new String("News 29"));
        newsList.add(new String("News 210"));
        newsList.add(new String("News 211"));
        newsList.add(new String("News 212"));
        newsList.add(new String("News 123"));
        newsList.add(new String("News 214"));
        newsList.add(new String("News 215"));
        newsList.add(new String("News 16"));
        newsList.add(new String("News 217"));

        mAdapter.notifyDataSetChanged();

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
