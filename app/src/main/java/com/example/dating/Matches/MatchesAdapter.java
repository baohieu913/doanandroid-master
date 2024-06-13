package com.example.dating.Matches;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dating.Chat.ChatActivity;
import com.example.dating.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolders> {

    private List<MatchesObject> matchesList;
    private Context context;

    public MatchesAdapter(List<MatchesObject> matchesList, Context context) {
        this.matchesList = matchesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchesViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        MatchesViewHolders rcv = new MatchesViewHolders(layoutView);


        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolders holder, int position) {
        holder.mMatchId.setText(matchesList.get(position).getUserId());
        holder.mBudget.setText(matchesList.get(position).getBudget());
        holder.mGive.setText(matchesList.get(position).getGive());
        holder.mProfile.setText(matchesList.get(position).getProfileImageUrl());
        holder.mNeed.setText(matchesList.get(position).getNeed());
        holder.mMatchName.setText(matchesList.get(position).getName());
        holder.mLastMessage.setText(matchesList.get(position).getLastMessage());
        String lastSeen = "";
        lastSeen = matchesList.get(position).getLastSeen();

        if (lastSeen.equals("true"))
            holder.mNotificationDot.setVisibility(View.VISIBLE);
        else
            holder.mNotificationDot.setVisibility(View.INVISIBLE);
        holder.mLastTimeStamp.setText(matchesList.get(position).getLastTimeStamp());
        if (!matchesList.get(position).getProfileImageUrl().equals("default")) {
            Glide.with(context).load(matchesList.get(position).getProfileImageUrl()).into(holder.mMatchImage);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);
            Bundle b = new Bundle();

            b.putString("matchId", matchesList.get(position).getUserId());
            b.putString("matchName", matchesList.get(position).getName());
            b.putString("lastMessage", matchesList.get(position).getLastMessage());
            b.putString("lastTimeStamp", matchesList.get(position).getLastTimeStamp());
            b.putString("budget", matchesList.get(position).getBudget());
            b.putString("need", matchesList.get(position).getNeed());
            b.putString("give", matchesList.get(position).getGive());
            b.putString("profile", matchesList.get(position).getProfileImageUrl());

//            holder.mMatchImage.setDrawingCacheEnabled(true);
//            holder.mMatchImage.buildDrawingCache();
//            Bitmap bitmap = ((BitmapDrawable) holder.mMatchImage.getDrawable()).getBitmap();
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            b.putByteArray("image", byteArray);

            intent.putExtras(b);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}
