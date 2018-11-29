package com.example.hoangduy.japanese4you.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.ScheduleActivity;
import com.example.hoangduy.japanese4you.models.Time;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 18/01/2017.
 */
public class AlarmAdapter  extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    public InteractionListener mListener;
    private ArrayList<Time> mTimes;
    private Context mContext;

    public AlarmAdapter(ArrayList<Time> times, Context context) {
        mTimes = times;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_alarm, parent, false);
        mListener = (ScheduleActivity) parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String s = "";
        String strings[] = mTimes.get(position).getDayofweek().split(" ");
        for (int i = 0; i < strings.length; i++) {
            switch (strings[i]) {
                case "Monday":
                    s += "M ";
                    break;
                case "Tuesday":
                    s += "TU ";
                    break;
                case "Wednesday":
                    s += "W ";
                    break;
                case "Thursday":
                    s += "TH ";
                    break;
                case "Friday":
                    s += "F ";
                    break;
                case "Satusday":
                    s += "SA ";
                    break;
                case "Sunday":
                    s += "SU ";
                    break;
            }
        }
        holder.tvDayofWeek.setText(s);
        holder.tvTime.setText(mTimes.get(position).getHour() + ":" + mTimes.get(position).getMinute());
        if (mTimes.get(position).getFlag() == 1) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    mListener.onFragmentInteraction(mTimes.get(position).getId(), 0);
                } else {
                    mListener.onFragmentInteraction(mTimes.get(position).getId(), 1);
                }
            }
        });
        holder.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onEditInteraction(mTimes.get(position).getId());
                Log.i("posi",mTimes.get(position).getId()+"");
            }
        });


    }

    @Override
    public int getItemCount() {
        return mTimes.size();
    }

    public interface InteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position, int flag);

        void onEditInteraction(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private TextView tvDayofWeek;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDayofWeek = (TextView) itemView.findViewById(R.id.tvDayofWeek);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }
}
