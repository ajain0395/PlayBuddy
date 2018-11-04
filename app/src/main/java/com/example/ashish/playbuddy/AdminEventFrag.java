package com.example.ashish.playbuddy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class AdminEventFrag extends Fragment {
    Button save,cancel,remove;
    EditText title,description;
    TextView eventdate,starttime,endtime;
    int mYear,mMonth,mDay,mHour,mMinute;
    EventDatabase db;



    private AdminEventFrag.OnFragmentInteractionListener mListener;

    public AdminEventFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=new EventDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_admin_event, container, false);

        //find elements on view.
        title=rootview.findViewById(R.id.eventtitle);
        description=rootview.findViewById(R.id.eventdescription);
        save=rootview.findViewById(R.id.eventsave);
        cancel=rootview.findViewById(R.id.eventcancel);
        remove=rootview.findViewById(R.id.eventremove);
        starttime = rootview.findViewById(R.id.eventstarttime);
        endtime = rootview.findViewById(R.id.eventendtime);
        eventdate = rootview.findViewById(R.id.eventdate);



        if(AdminEventRecyclerViewFrag.selectedEvent !=null)
        {
            //  remove.setVisibility(View.VISIBLE);
            //save.setVisibility(View.INVISIBLE);
            remove.setVisibility(View.VISIBLE);
            title.setText(AdminEventRecyclerViewFrag.selectedEvent.getEventTitle());
            description.setText(AdminEventRecyclerViewFrag.selectedEvent.getEventTitle());
            starttime.setText(AdminEventRecyclerViewFrag.selectedEvent.getEventStartTime());
            endtime.setText(AdminEventRecyclerViewFrag.selectedEvent.getEventEndTime());
            mDay = AdminEventRecyclerViewFrag.selectedEvent.getEventDate().getDay();
            mYear = AdminEventRecyclerViewFrag.selectedEvent.getEventDate().getYear();
            mMonth = AdminEventRecyclerViewFrag.selectedEvent.getEventDate().getMonth();
            eventdate.setText("Event Date\n" + mDay + "-"
            + mMonth + "-"
                    +mYear
            );
            // Toast.makeText(getActivity(), ""+NewsAdminRecyclerViewFrag.selectedNews.getNews_id(), Toast.LENGTH_SHORT).show();
            //fill here
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(AdminEventRecyclerViewFrag.selectedEvent!=null)
                {
                    String updatedDesc=description.getText().toString();
                    String updatedTitle=title.getText().toString();
                    AdminEventRecyclerViewFrag.selectedEvent.setEventDescription(updatedDesc);
                    AdminEventRecyclerViewFrag.selectedEvent.setEventTitle(updatedTitle);
                    AdminEventRecyclerViewFrag.selectedEvent.setEventStartTime(starttime.getText().toString());
                    AdminEventRecyclerViewFrag.selectedEvent.setEventEndTime(endtime.getText().toString());
                    AdminEventRecyclerViewFrag.selectedEvent.setSportId("");
                    Date saveDate = new Date();
                    saveDate.setDate(mDay);
                    saveDate.setMonth(mMonth);
                    saveDate.setYear(mYear);
                    AdminEventRecyclerViewFrag.selectedEvent.setEventDate(saveDate);
                    AdminEventRecyclerViewFrag.selectedEvent.setVenueId("");
                    //AdminEventRecyclerViewFrag.selectedEvent.setEventDescription(new Date());
                    db.updateEvent(AdminEventRecyclerViewFrag.selectedEvent);
                    Toast.makeText(getActivity(), "Event Updated Successfully!!"+AdminEventRecyclerViewFrag.selectedEvent.getEventId(), Toast.LENGTH_SHORT).show();
                    AdminEventRecyclerViewFrag.selectedEvent = null;
                    callEventAdminRecyclerViewFrag();
                }


                else {
                    String heading = title.getText().toString();
                    String desc = description.getText().toString();
                    if (heading.length() == 0 || desc.length() == 0) {
                        Toast.makeText(getActivity(), "Please fill the fields!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Event event = new Event();
                        AdminEventRecyclerViewFrag.selectedEvent = event;
                        AdminEventRecyclerViewFrag.selectedEvent.setEventDescription(desc);
                        AdminEventRecyclerViewFrag.selectedEvent.setEventTitle(heading);
                        AdminEventRecyclerViewFrag.selectedEvent.setEventStartTime(starttime.getText().toString());
                        AdminEventRecyclerViewFrag.selectedEvent.setEventEndTime(endtime.getText().toString());
                        AdminEventRecyclerViewFrag.selectedEvent.setSportId("");
                        Date saveDate = new Date();
                        saveDate.setDate(mDay);
                        saveDate.setMonth(mMonth);
                        saveDate.setYear(mYear);
                        AdminEventRecyclerViewFrag.selectedEvent.setEventDate(saveDate);                        AdminEventRecyclerViewFrag.selectedEvent.setVenueId("");
                        db.write(event, "event");
                        AdminEventRecyclerViewFrag.selectedEvent=null;
                        callEventAdminRecyclerViewFrag();
                    }
                }

            }
        });

        eventdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                eventdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                starttime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();

            }
        });
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                endtime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.remove(AdminEventRecyclerViewFrag.selectedEvent.getEventId());
                Toast.makeText(getActivity(), "Event Removed Successfully!!", Toast.LENGTH_SHORT).show();
                AdminEventRecyclerViewFrag.selectedEvent=null;
                callEventAdminRecyclerViewFrag();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText(null);
                description.setText(null);
                AdminEventRecyclerViewFrag.selectedEvent=null;
                callEventAdminRecyclerViewFrag();

            }
        });

        return rootview;
    }

    void callEventAdminRecyclerViewFrag()
    {
        AdminEventRecyclerViewFrag fr=new AdminEventRecyclerViewFrag();

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
        if (context instanceof AdminEventFrag.OnFragmentInteractionListener) {
            mListener = (AdminEventFrag.OnFragmentInteractionListener) context;
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
