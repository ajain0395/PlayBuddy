package com.example.ashish.playbuddy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.MyViewHolder> {

    private List<Event> eventList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.eventTileTitle);
            date = view.findViewById(R.id.eventTileDate);
            //data to be displayed in tile
            //    add if any
        }
    }


    public MyEventAdapter(List<Event> event) {
        this.eventList = event;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eventtile, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.title.setText(event.getEventTitle());
        holder.date.setText("Date: "+ event.getEventDate().getDay() + "-"
                            +event.getEventDate().getMonth() + "-"
                            +event.getEventDate().getYear() + "-\n"
                            +"Time: "+ event.getEventStartTime());
        //add data to holder
        // holder.genre.setText(movie.getGenre());
        // holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
