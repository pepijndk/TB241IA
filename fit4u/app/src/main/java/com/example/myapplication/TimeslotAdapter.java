package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.List;

public class TimeslotAdapter extends RecyclerView.Adapter<TimeslotAdapter.MyViewHolder> {
    private List<Timeslot> mDataset;
    private RecyclerViewClickListener mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView textViewSport;
        public TextView textViewDatum;
        public TextView textViewDuur;
        private RecyclerViewClickListener mListener;
//        private Context context;

        public MyViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);
            this.textViewSport = (TextView) v.findViewById(R.id.item_trainer_sport);
            this.textViewDatum = (TextView) v.findViewById(R.id.item_trainer_datum);
            this.textViewDuur = (TextView) v.findViewById(R.id.item_trainer_duur);
            mListener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public TimeslotAdapter(List<Timeslot> myDataset, RecyclerViewClickListener listener) {
        mDataset = myDataset;
        mListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TimeslotAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.timeslot_view, parent, false);


        MyViewHolder vh = new MyViewHolder(contactView, mListener);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        /*
        *         public TextView textViewSport;
        public TextView textViewDatum;
        public TextView textViewDuur;
        *
        * */

        holder.textViewSport.setText(mDataset.get(position).getBeschrijving()); //mDataset.get(position).getNaam()
        holder.textViewDatum.setText(mDataset.get(position).getDatum());
        holder.textViewDuur.setText("Duur: " + mDataset.get(position).getDuration() + "h");

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#C20022"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#C61131"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}