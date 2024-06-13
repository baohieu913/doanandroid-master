package com.example.dating.Chat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dating.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolders> {

    private List<ChatObject> chatList;
    private Context context;

    public ChatAdapter(List<ChatObject> chatList, Context context) {
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        ChatViewHolders rcv = new ChatViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolders holder, int position) {
        holder.mMessage.setText(chatList.get(position).getMessages());
        if (chatList.get(position).getCurrentUser()) {
            GradientDrawable shape = new GradientDrawable();
            shape.setCornerRadius(20);
            shape.setCornerRadii(new float[] {25, 25, 3, 25, 25, 25, 25, 25});
            shape.setColor(Color.parseColor("#5fc9f8"));
            holder.mContainer.setGravity(Gravity.END);
            holder.mMessage.setBackground(shape);
            holder.mMessage.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            GradientDrawable shape = new GradientDrawable();
            shape.setCornerRadius(20);
            shape.setCornerRadii(new float[] {3, 25, 25, 25, 25, 25, 25, 25});
            shape.setColor(Color.parseColor("#53d769"));
            holder.mContainer.setGravity(Gravity.START);
            holder.mMessage.setBackground(shape);
            holder.mMessage.setTextColor(Color.parseColor("#FFFFFF"));
            if (!chatList.get(position).getImageURL().equals("default")) {
                Glide.with(context).load(chatList.get(position).getImageURL()).into(holder.mImageProfile);
            }
            holder.mImageProfile.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return this.chatList.size();
    }
}
