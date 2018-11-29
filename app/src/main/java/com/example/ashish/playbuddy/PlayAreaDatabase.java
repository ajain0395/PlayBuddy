package com.example.ashish.playbuddy;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlayAreaDatabase {


    private DatabaseReference myDatabase;
    String playAreaId;
   // public static ArrayList<Sport> sports;

    public PlayAreaDatabase() {

        myDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void write(Object obj,String databaseName)
    {
        playAreaId=myDatabase.push().getKey();

        if(databaseName=="playarea")
        {
            PlayArea playArea = (PlayArea) obj;
            playArea.setPlayAreaId(playAreaId);
        }
        myDatabase.child(databaseName).child(playAreaId).setValue(obj);
    }

 /*   public void update(String sportId,String sportName)
    {
        Map<String,Object> taskMap = new HashMap<String, Object>();
        taskMap.put("sportName", sportName);

        if(sportId!=null) {
            myDatabase.child("sports").child(sportId).updateChildren(taskMap);
        }
        else
        {
            Log.e("indus","Data not updated as id is null");
        }
    }*/

    public void remove(String playAreaId)
    {

        if(playAreaId!=null) {
            myDatabase.child("playarea").child(playAreaId).removeValue();
        }
        else
        {
            Log.e("indus","Data not updated as id is null");
        }
    }

}
