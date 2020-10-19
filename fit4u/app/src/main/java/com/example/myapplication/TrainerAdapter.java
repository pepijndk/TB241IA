package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.MyViewHolder> {
    private List<Trainer> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewName;
        public TextView textViewLocation;
//        private Context context;

        public MyViewHolder(View v) {
            super(v);
            this.textViewName = (TextView) v.findViewById(R.id.item_trainer_name);
            this.textViewLocation = (TextView) v.findViewById(R.id.item_trainer_location);
//            this.context = context;
            //v.setOnClickListener(this);
        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public TrainerAdapter(List<Trainer> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrainerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.trainer_view, parent, false);


        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.trainer_view, parent, false);


        MyViewHolder vh = new MyViewHolder(contactView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.textViewName.setText(mDataset.get(position).getNaam()); //mDataset.get(position).getNaam()
        holder.textViewLocation.setText(mDataset.get(position).getAdres());

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
        holder.bind(mDataset.get(position), itemClickListener);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}