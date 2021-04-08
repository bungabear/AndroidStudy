package com.bungabear.androidstudy.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bungabear.androidstudy.model.DatabindingRecyclerItem;

import java.util.ArrayList;

public class DatabindingTestAdapter extends RecyclerView.Adapter<DatabindingTestAdapter.DatabindingTestViewHolder> {

    private ArrayList<DatabindingRecyclerItem> items = new ArrayList<DatabindingRecyclerItem>();

    @NonNull
    @Override
    public DatabindingTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DatabindingTestViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DatabindingTestViewHolder extends  RecyclerView.ViewHolder{

        public DatabindingTestViewHolder(View itemView) {
            super(itemView);
        }
    }
}
