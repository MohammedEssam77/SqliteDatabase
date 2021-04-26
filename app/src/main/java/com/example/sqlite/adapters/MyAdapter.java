package com.example.sqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.database.DBSQLiteHelper;
import com.example.sqlite.R;
import com.example.sqlite.models.DataModel;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<DataModel> notes;
    private Context context;

    public MyAdapter(Context context, List<DataModel> notes) {
        this.context = context;
        this.notes = notes;
    }

    public void setNotes(List<DataModel> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DataModel model = notes.get(position);
        holder.name.setText(model.getName());
        holder.email.setText(model.getMail());
        holder.number.setText(model.getNumber());
        holder.itemView.setClickable(true);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DBSQLiteHelper database = new DBSQLiteHelper(context);
                database.deleteEntry(model.getId());
                notes.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, notes.size());
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, number;

        ViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            number = itemView.findViewById(R.id.number);
            itemView.setClickable(true);
        }
    }
}
