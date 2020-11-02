package com.fmohammadi.instagramjava.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fmohammadi.instagramjava.R;
import com.fmohammadi.instagramjava.model.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    private Context mContext;
    private List<User> mUsers;


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        public CircleImageView imageProfile;
        public TextView username;
        public TextView fullName;
        public Button btnFollow;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageProfile = itemView.findViewById(R.id.image_profile);
            username = itemView.findViewById(R.id.username);
            fullName = itemView.findViewById(R.id.fullName);
            btnFollow = itemView.findViewById(R.id.btn_follow);
        }
    }
}
