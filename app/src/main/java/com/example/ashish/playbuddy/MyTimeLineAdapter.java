package com.example.ashish.playbuddy;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyTimeLineAdapter extends RecyclerView.Adapter<MyTimeLineAdapter.MyViewHolder>  {

    private List<TimeLine> timeLineList;
    private ClickListener listener;


  /*  void iconTextViewOnClick(View v, int position);
    void iconImageViewOnClick(View v, int position);*/

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView starttime;
        public TextView endtime;
        public TextView count;
        public Button plus;
        public Button minus;
        Date date;
        private WeakReference<ClickListener> listenerRef;



        public MyViewHolder(View view, ClickListener listener) {
            super(view);

            listenerRef = new WeakReference<>(listener);
            count =view.findViewById(R.id.timelineslotcount);
            plus = view.findViewById(R.id.timelineplus);
            minus = view.findViewById(R.id.timelineminus);
            starttime = view.findViewById(R.id.timelineslotstarttime);
            endtime = view.findViewById(R.id.timelineslotendtime);
            date = new Date();

            view.setOnClickListener(this);
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);

            //data to be displayed in tile
            //    add if any
       /*     plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.pl(v, getAdapterPosition());
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.iconImageViewOnClick(v, getAdapterPosition());
                }
            });/*/
        }




        public void onClick(View v) {

            if (v.getId() == minus.getId()) {
                Toast.makeText(v.getContext(), "minus pressed on " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            } else if(v.getId() == plus.getId()) {
                Toast.makeText(v.getContext(), "plus PRESSED on " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }

            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }


    public MyTimeLineAdapter(List<TimeLine> time) {
        this.timeLineList = time;
    }
    public MyTimeLineAdapter(List<TimeLine> time, ClickListener listener) {
        this.timeLineList = time;
        this.listener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timelinetile, parent, false);

        return new MyViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TimeLine timeLine = timeLineList.get(position);
        holder.starttime.setText("Start Time: " + timeLine.getStartTime());
        holder.endtime.setText("End Time: " + timeLine.getEndTime());
        holder.count.setText("Players Count: 0");
        holder.minus.setEnabled(false);
        holder.plus.setEnabled(false);

        String []endhrmin = timeLine.getEndTime().split(":");
        String []starthrmin = timeLine.getStartTime().split(":");
        int endhour = Integer.parseInt(endhrmin[0]);
        int endmin = Integer.parseInt(endhrmin[1]);
        int starthour = Integer.parseInt(starthrmin[0]);
        int startmin = Integer.parseInt(starthrmin[1]);


        if(starthour > holder.date.getHours())
        {
            holder.minus.setEnabled(true);
            holder.plus.setEnabled(true);

        }
        else if(starthour == endhour && endhour == holder.date.getHours())
        {
            holder.minus.setEnabled(true);
            holder.plus.setEnabled(true);

        }
        else if(starthour != endhour && starthour == holder.date.getHours() && 0<= holder.date.getMinutes() && holder.date.getMinutes() <=59)
        {
            holder.minus.setEnabled(true);
            holder.plus.setEnabled(true);

        }


      /*  if(starthour == endhour && starthour ==holder.date.getHours())
        {
            //if(holder.date.getMinutes() >=0 &&)
            if(30 >= holder.date.getMinutes() && holder.date.getMinutes() >=0)
            {
                holder.minus.setEnabled(true);
                holder.plus.setEnabled(true);
            }
        }
        else if(starthour != endhour && starthour >=holder.date.getHours())
        {
            holder.minus.setEnabled(true);
            holder.plus.setEnabled(true);
        }
        if(starthour == endhour && starthour > holder.date.getMinutes())
        {

        }*/

       /* holder.date.setText("Date: "+ event.getEventDate().getDay() + "-"
                            +event.getEventDate().getMonth() + "-"
                            +event.getEventDate().getYear() + "\n"
                            +"Time: "+ event.getEventStartTime());*/
        //add data to holder
        // holder.genre.setText(movie.getGenre());
        // holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return timeLineList.size();
    }


    public interface ClickListener {

        void onPositionClicked(int position);

      //  void onLongClicked(int position);
    }
}
