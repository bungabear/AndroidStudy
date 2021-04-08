package com.bungabear.androidstudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bungabear.androidstudy.R;

import java.util.ArrayList;

public class MainListFragment extends Fragment {

    private View root = null;
    private ListView lvMain;
    private ArrayList<Class> activities = new ArrayList<Class>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> preAdapter = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.fragment_main_listview, container, false);
            lvMain = root.findViewById(R.id.lv_main_fragment_listview);
            adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1);
            adapter.addAll(preAdapter);
            lvMain.setAdapter(adapter);
            lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(activities.get(position) != null){
                        startActivity(new Intent(getContext(), activities.get(position)));
                    }
                }
            });
        }
        return root;
    }

    public void addListItem(Class className, String text){
        activities.add(className);
        if(adapter == null){
            preAdapter.add(text);
        }
        else {
            adapter.add(text);
        }
    }
}
