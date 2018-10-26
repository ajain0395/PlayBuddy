package com.example.ashish.playbuddy;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database {

    private DatabaseReference myDatabase;
    String id;
    public static ArrayList<News> allNews;

    public Database() {

        myDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void write(Object obj,String databaseName)
    {
        id=myDatabase.push().getKey();

        if(databaseName=="news")
        {
            News news=(News) obj;
            news.setNews_id(id);
        }

        myDatabase.child(databaseName).child(id).setValue(obj);

    }
    /*public void writeNews( String title, String description) {
       id=myDatabase.push().getKey();
        News news=new News(title,description);
        news.setNews_id(newsId);

        myDatabase.child("news").child(newsId).setValue(news);
    }*/

    /*public static void saveData(ArrayList<News> data)
    {
        NewsAdminRecyclerViewFrag.newsList=data;
    }*/

    public void readNews()
    {   //ArrayList<News> alldata;


        myDatabase.child("news").addValueEventListener(new ValueEventListener() {
         ArrayList<News>   data = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // News news=dataSnapshot.getValue(News.class);

                for (DataSnapshot ds:dataSnapshot.getChildren()) {

                    News news=new News();
                    news.setTitle(ds.getValue(News.class).getTitle());
                    news.setDescription(ds.getValue(News.class).getDescription());


                    //data.add(news.getTitle());
                       data.add(news);
        //               Log.i("data", data.get(0).getTitle());
                }
                allNews=data;
          //    Log.i("data", allNews.get(1).title);

                // alldata = data;
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w("error", "Failed to read value.", databaseError.toException());

            }
        });


    }

    public void updateNews(News news)
    {
        myDatabase.child("news").child(news.getNews_id()).setValue(news);
    }


}