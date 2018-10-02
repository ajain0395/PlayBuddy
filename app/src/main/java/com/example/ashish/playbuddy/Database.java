package com.example.ashish.playbuddy;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Database {

    private DatabaseReference myDatabase;
    String id;

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

    public void readNews()
    {
        myDatabase.child("news").child(id).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                News news=dataSnapshot.getValue(News.class);



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