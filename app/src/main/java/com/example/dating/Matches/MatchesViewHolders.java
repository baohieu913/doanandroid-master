package com.example.dating.Matches;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dating.Chat.ChatActivity;
import com.example.dating.R;

import java.io.ByteArrayOutputStream;

public class MatchesViewHolders extends RecyclerView.ViewHolder  {

    public TextView mMatchId, mMatchName, mLastTimeStamp, mLastMessage, mNeed, mGive, mBudget, mProfile;
    public ImageView mNotificationDot, mMatchImage;

    public MatchesViewHolders(@NonNull View itemView) {
        super(itemView);


        mMatchId = (TextView) itemView.findViewById(R.id.Matchid);
        mMatchName = (TextView) itemView.findViewById(R.id.matchName);
        mLastMessage = (TextView) itemView.findViewById(R.id.lastMessage);
        mLastTimeStamp = (TextView) itemView.findViewById(R.id.lastTimeStamp);

        mNeed = (TextView) itemView.findViewById(R.id.needid);
        mGive = (TextView) itemView.findViewById(R.id.giveid);
        mBudget = (TextView) itemView.findViewById(R.id.budgetid);
        mMatchImage = (ImageView) itemView.findViewById(R.id.MatchImage);
        mProfile = (TextView) itemView.findViewById(R.id.profileid);
        mNotificationDot = (ImageView) itemView.findViewById(R.id.notification_dot);

    }


}
