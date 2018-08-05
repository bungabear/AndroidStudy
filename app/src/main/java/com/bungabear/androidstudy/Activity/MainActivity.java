package com.bungabear.androidstudy.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.bungabear.androidstudy.Fragment.MainListFragment;
import com.bungabear.androidstudy.Fragment.MainRecyclerFragment;
import com.bungabear.androidstudy.R;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Button btn1, btn2, btn3, btn4, btn5;
    private FragmentManager fm;
    private MainListFragment listFragment;
    private MainRecyclerFragment recyclerFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFragment = new MainListFragment();
        recyclerFragment = new MainRecyclerFragment();
        fm = getSupportFragmentManager();
        fm
                .beginTransaction()
                .replace(R.id.main_fragment_placeholder,listFragment)
                .commit();
        setActivities();
    }

    private void setActivities(){
        addToActicityList(BasicViewTest.class, "View Basic");
        addToActicityList(BasicViewTicTaeToe.class, "Tic-Tac-Toe");
        addToActicityList(BasicViewTicTaeToeDynamic.class, "Tic-Tac-Toe Dynamic View Add");
        addToActicityList(BasicViewLogin.class, "Login Activity");
        addToActicityList(ResultRequestActivity.class, "Login Request Activity");
        addToActicityList(RetrofitTest.class, "Retrofit Test");
        addToActicityList(DatabindingBasic.class, "DatabindingBasic Test");
    }

    private void addToActicityList(Class activity, String name ){
        MainRecyclerFragment.SimpleTextRecyclerAdapter adapter = ((MainRecyclerFragment.SimpleTextRecyclerAdapter)recyclerFragment.adapter);
        adapter.addListItem(activity, name);
        listFragment.addListItem(activity, name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        Switch swListType = menu.findItem(R.id.menu_main_list_switch_item).getActionView().findViewById(R.id.sw_main_menu_listtype);
        swListType.setOnCheckedChangeListener(this);
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.sw_main_menu_listtype){
            Fragment fragment;
            if(isChecked){
                fragment = recyclerFragment;
            }
            else {
                fragment = listFragment;
            }
            fm
                    .beginTransaction()
                    .replace(R.id.main_fragment_placeholder, fragment)
                    .commit();
        }
    }
}
