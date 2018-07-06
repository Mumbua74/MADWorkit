package com.example.faithmumbua.workit.activities.session;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.faithmumbua.workit.R;
import com.example.faithmumbua.workit.models.Session;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionHolder>
{
    private List<Session> sessionList;


    public class SessionHolder extends RecyclerView.ViewHolder
    {
        public TextView exercise_name,rep_number,location_name,date;

        public SessionHolder(View view)
        {
            super(view);
            exercise_name =  view.findViewById(R.id.exercise_name);
            rep_number = view.findViewById(R.id.rep_number);
            location_name = view.findViewById(R.id.location_name);
            date =  view.findViewById(R.id.date);
        }
    }

    public SessionAdapter(List<Session> sessionList)
    {
        this.sessionList = sessionList;
    }

    @Override
    public SessionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_session, parent, false);

        return new SessionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SessionHolder holder, int position)
    {
        Session session = sessionList.get(position);
        holder.exercise_name.setText(session.getExercise_name());
        holder.rep_number.setText(session.getRep_number());
        holder.location_name.setText(session.getLocation_name());
        holder.date.setText(session.getDate());
    }

    @Override
    public int getItemCount() {
        return sessionList.size();
    }
}
