package com.example.otus_paddign_task.common.base;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {
    protected List<T> items = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        holder.bind(getItem(position));
    }

    private T getItem(int position){
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addAllNotify(List<T> items){
        this.items = items;
        notifyDataSetChanged();
    }
}
