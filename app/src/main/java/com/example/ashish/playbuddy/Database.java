package com.example.ashish.playbuddy;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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



    public void updateNews(String id,String description,String title)
    {
        Map<String,Object> taskMap = new HashMap<String, Object>();
        taskMap.put("newsDescription", description);
        taskMap.put("newsTitle",title);
        if(id!=null) {
            myDatabase.child("news").child(id).updateChildren(taskMap);
        }
        else
        {
            Log.e("indus","Data not updated as id is null");
        }
    }

    public void remove(String id)
    {

        if(id!=null) {
            myDatabase.child("news").child(id).removeValue();
        }
        else
        {
            Log.e("indus","Data not updated as id is null");
        }
    }


}