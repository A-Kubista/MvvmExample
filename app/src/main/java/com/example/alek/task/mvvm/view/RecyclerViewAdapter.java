package com.example.alek.task.mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alek.task.R;
import com.example.alek.task.mvvm.model.UserModel;
import com.example.alek.task.mvvm.view.ui.UserDetailActivity;
import com.example.alek.task.mvvm.view.ui.UserDetailFragment;
import com.example.alek.task.mvvm.view.ui.UserListActivity;
import java.util.List;

/**
 * Created by alek on 14/09/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private View.OnLongClickListener longClickListener;

    private final UserListActivity mParentActivity;
    private List<UserModel> userModelList;
    private final boolean mTwoPane;

    public RecyclerViewAdapter(UserListActivity parent,
                               boolean twoPane) {
    //    userModelList = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_content, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        UserModel model = userModelList.get(position);
        if(model != null) {
            holder.mIdView.setText(model.getId());
            holder.mContentView.setText(model.getName());

            holder.itemView.setTag(userModelList.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public void addItems(List<UserModel> userModelList) {
        this.userModelList = userModelList;
        notifyDataSetChanged();
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           UserModel user = (UserModel) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(UserDetailFragment.ARG_USER_ID, user.getId());
                arguments.putString(UserDetailFragment.ARG_USER_NAME, user.getName());
                arguments.putBoolean(UserDetailFragment.ARG_TWO_PANE, mTwoPane);
                UserDetailFragment fragment = new UserDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_up)
                        .replace(R.id.user_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra(UserDetailFragment.ARG_USER_ID, user.getId());
                intent.putExtra(UserDetailFragment.ARG_USER_NAME, user.getName());
                intent.putExtra(UserDetailFragment.ARG_TWO_PANE, mTwoPane);
                mParentActivity.onUserSelected(intent);
            }
        }
    };


    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}



