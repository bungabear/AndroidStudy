package com.bungabear.androidstudy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bungabear.androidstudy.R;
import com.bungabear.androidstudy.recycler.SamplePostRecyclerAdapter;

/**
 * Created by Minjae Son on 2018-07-29.
 */
public class RetrofitTestPostDetailFragment extends Fragment {

    private View root = null;
    private Context context = null;
    private SamplePostRecyclerAdapter.PostItem item = null;
    public TextView tvPostId, tvUserId, tvTitle,tvBody;
    private ImageView ivIcon;
    private int position;

    public static RetrofitTestPostDetailFragment create(Context context, SamplePostRecyclerAdapter.PostItem item, int position) {
        RetrofitTestPostDetailFragment fragment = new RetrofitTestPostDetailFragment();
        fragment.context = context;
        fragment.item = item;
        fragment.position = position;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null) {
            root = inflater.inflate(R.layout.fragment_retrofit_test_post_detail, container, false);
            tvPostId = root.findViewById(R.id.tv_postid);
            tvUserId = root.findViewById(R.id.tv_userid);
            tvTitle = root.findViewById(R.id.tv_title);
            tvBody = root.findViewById(R.id.tv_body);
            ivIcon = root.findViewById(R.id.iv_post_detail_icon);
            tvPostId.setText(item.id + "");
            tvUserId.setText(item.userId + "");
            tvTitle.setText(item.title );
            tvBody.setText(item.body);
            ivIcon.setTransitionName("POST_TRANSITION_" + position);
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));

        }
        return root;
    }
}
