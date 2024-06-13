package com.example.dating.Chat;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dating.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mMessage;
    public LinearLayout mContainer;
    public CircleImageView mImageProfile;

    public ChatViewHolders(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mMessage = itemView.findViewById(R.id.message);
        mContainer = itemView.findViewById(R.id.container);
        mImageProfile = itemView.findViewById(R.id.profile_image);


    }

    @Override
    public void onClick(View v) {

    }
}
