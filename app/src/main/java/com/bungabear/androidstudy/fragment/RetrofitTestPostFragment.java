package com.bungabear.androidstudy.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bungabear.androidstudy.R;
import com.bungabear.androidstudy.recycler.SamplePostRecyclerAdapter;
import com.bungabear.androidstudy.util.Singleton;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Minjae Son on 2018-07-29.
 */
public class RetrofitTestPostFragment extends Fragment implements SamplePostRecyclerAdapter.PostItemClickListener {

    private Context context = null;
    private View root = null;
    private RecyclerView recyclerView = null;
    private SamplePostRecyclerAdapter adapter = new SamplePostRecyclerAdapter(this);

    public static RetrofitTestPostFragment create(Context context){
        RetrofitTestPostFragment fragment = new RetrofitTestPostFragment();
        fragment.context = context;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        if(root == null){
            root = inflater.inflate(R.layout.empty_recycler, container, false);
            recyclerView = root.findViewById(R.id.empty_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            Singleton.getRetrofit().getPosts().enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    JsonArray posts = response.body();
                    for(int i = 0; i < posts.size(); i++){
                        adapter.addItem(new SamplePostRecyclerAdapter.PostItem(posts.get(i).getAsJsonObject()));
                        adapter.notifyItemInserted(adapter.getItemCount()-1);
                    }
                }
                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) { }
            });
            recyclerView.setAdapter(adapter);
        }
        return root;
    }
    @Override
    public void onPostClickListener(SamplePostRecyclerAdapter.PostItem item, ImageView sharedImageView, int position) {
        Transition defaultTransition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move);
        RetrofitTestPostDetailFragment detailFragment= RetrofitTestPostDetailFragment.create(getContext(), item, position);
        detailFragment.setSharedElementEnterTransition(defaultTransition);
//        Fragment thisFragment = getFragmentManager().findFragmentById(R.id.placeholder);
//        thisFragment.setSharedElementReturnTransition(defaultTransition);

        getFragmentManager()
                .beginTransaction()
                .addSharedElement(sharedImageView , "POST_TRANSITION_"+position)
                .addToBackStack(null)
                .replace(R.id.placeholder, detailFragment)
                .commit();
    }
}
