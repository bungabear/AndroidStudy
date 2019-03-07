package com.bungabear.androidstudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bungabear.androidstudy.R;

import java.util.ArrayList;

public class MainRecyclerFragment extends Fragment {

    private View root = null;
    private RecyclerView rvMain;
    public SimpleTextRecyclerAdapter adapter = new SimpleTextRecyclerAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.fragment_main_recycler, container, false);
            rvMain = root.findViewById(R.id.rv_main_fragment_recyclerview);
            rvMain.setAdapter(adapter);
            RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rvMain.setLayoutManager(lm);
            rvMain.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            adapter.notifyDataSetChanged();
        }
        return root;
    }


    public class SimpleTextRecyclerAdapter extends RecyclerView.Adapter<SimpleTextRecyclerAdapter.ViewHolder> {

        private ArrayList<Class> activities = new ArrayList<Class>();
        private ArrayList<String> itemText = new ArrayList<String>();

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_item_main_fragment_recycler, parent, false);
            ViewHolder vh = new ViewHolder(mView);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.itemText.setText(itemText.get(position));
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activities.get(position) != null){
                        startActivity(new Intent(getContext(), activities.get(position)));
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return activities.size();
        }

        public void addListItem(Class className, String text){
            activities.add(className);
            itemText.add(text);
            notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView itemText;
            private View root;

            public ViewHolder(View itemView) {
                super(itemView);
                root = itemView;
                itemText = itemView.findViewById(R.id.tv_main_fragment_recycler_item);
            }
        }
    }

}
