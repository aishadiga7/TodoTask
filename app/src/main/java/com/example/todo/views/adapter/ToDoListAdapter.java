package com.example.todo.views.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.databinding.ItemViewBinding;
import com.example.todo.views.uimodel.TodoUIModel;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private List<TodoUIModel> list = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_view, parent, false);
        return new ViewHolder(binding);
    }

    public void setList(List<TodoUIModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ItemViewBinding itemViewBinding;

        public ViewHolder(@NonNull ItemViewBinding itemView) {
            super(itemView.getRoot());
            itemViewBinding = itemView;
        }

        public void bind(TodoUIModel list) {
            itemViewBinding.tvTaskDescription.setText(list.getTaskDescription());
        }
    }
}
