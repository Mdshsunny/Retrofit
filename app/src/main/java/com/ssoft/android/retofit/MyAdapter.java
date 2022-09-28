package com.ssoft.android.retofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private List<Todo> list;

    public MyAdapter(Context context, List<Todo> list) {
        this.context=context;
        this.list= list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = list.get(position);

        String hasCompleted;
        if (todo.isCompleted()) {
            hasCompleted = "True";
        } else {
            hasCompleted = "False";
        }

        String detail = todo.getUserId() +" . "+
                todo.getId() +" . "+ hasCompleted;
        String title = todo.getTitle();

        holder.detailText.setText(detail);
        holder.titleText.setText(title);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView detailText;
        TextView titleText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            detailText = itemView.findViewById(R.id.detail);
            titleText = itemView.findViewById(R.id.title);
        }
    }
}
