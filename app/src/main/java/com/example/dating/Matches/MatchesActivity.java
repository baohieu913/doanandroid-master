package com.example.dating.Matches;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dating.MainActivity;
import com.example.dating.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {
    private final List<MatchesObject> resultsMatches = new ArrayList<>();
    List<MatchesObject> tempMatchesList = new ArrayList<>();


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mMatchesAdapter;
    private RecyclerView.LayoutManager mMatchesLayoutManager;

    private ImageButton mBack;
    private DatabaseReference current;
    private ValueEventListener listen;
    private HashMap<String, Integer> mList = new HashMap<>();
    private String currentUserId, mLastTimeStamp, mLastMessage, lastSeen;
    DatabaseReference mCurrUserIdInsideMatchConnections, mCheckLastSeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_matches);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mBack = findViewById(R.id.matchesBack);


        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        mMatchesLayoutManager = new LinearLayoutManager(MatchesActivity.this);
        mRecyclerView.setLayoutManager(mMatchesLayoutManager);
        mMatchesAdapter = new MatchesAdapter(getDataSetMatches(), MatchesActivity.this);
        mRecyclerView.setAdapter(mMatchesAdapter);



        mBack.setOnClickListener(v -> {
            Intent intent = new Intent(MatchesActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        getUserMatchId();
        mLastMessage = mLastTimeStamp = lastSeen = "";

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void getLastMessageInfo(DatabaseReference userDb) {
        mCurrUserIdInsideMatchConnections = userDb.child("connections").child("matches").child(currentUserId);
        mCurrUserIdInsideMatchConnections.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.child("lastMessage").getValue() != null && snapshot.child("lastTimeStamp").getValue() != null && snapshot.child("lastSeen").getValue() != null) {
                        mLastMessage = snapshot.child("lastMessage").getValue().toString();
                        mLastTimeStamp = snapshot.child("lastTimeStamp").getValue().toString();
                        lastSeen = snapshot.child("lastSeen").getValue().toString();
                    } else {
                        mLastMessage = "Start Chatting one";
                        mLastTimeStamp = " ";
                        lastSeen = "true";
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void  getUserMatchId() {
        Query sortedMatchesByLastTimeStamp = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId)
                .child("connections").child("matches")
                .orderByChild("lastTimeStamp");
        sortedMatchesByLastTimeStamp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot match : snapshot.getChildren()) {
                        FetchMatchInformation(match.getKey(), match.child("ChatId").toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void FetchMatchInformation(final String key, final String chatid) {
        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        getLastMessageInfo(userDb);

        userDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userId = snapshot.getKey();
                    String name = "";
                    String profileImageUrl = "";
                    String need = "";
                    String give = "";
                    String budget = "";
                    final String lastMessage = "";
                    String lastTimeStamp = "";

                    if (snapshot.child("name").getValue() != null) {
                        name = snapshot.child("name").getValue().toString();
                    }

                    if (snapshot.child("profileImageUrl").getValue() != null) {
                        profileImageUrl = snapshot.child("profileImageUrl").getValue().toString();
                    }

                    if (snapshot.child("need").getValue() != null) {
                        need = snapshot.child("need").getValue().toString();
                    }

                    if (snapshot.child("give").getValue() != null) {
                        give = snapshot.child("give").getValue().toString();
                    }

                    if (snapshot.child("budget").getValue() != null) {
                        budget = snapshot.child("budget").getValue().toString();
                    }

                    if (snapshot.child("lastMessage").getValue() != null) {
                        mLastMessage = snapshot.child("lastMessage").getValue().toString();
                    }

                    if (snapshot.child("lastSeen").getValue() != null) {
                        lastSeen = snapshot.child("lastSeen").getValue().toString();

                    }

                    if (snapshot.child("lastTimeStamp").getValue() != null) {
                        mLastTimeStamp = snapshot.child("lastTimeStamp").getValue().toString();
                    }

                    String milliSec = mLastTimeStamp;
                    long now;

                    try {
                        now = Long.parseLong(milliSec);
                        lastTimeStamp = convertMilliToRelative(now);
                        String[] arrOfStr = lastTimeStamp.split(",");
                        mLastTimeStamp = arrOfStr[0];
                    } catch (Exception e) {

                    }
                    MatchesObject obj = new MatchesObject(userId, name, profileImageUrl, need, give, budget, mLastMessage, mLastTimeStamp, lastSeen, chatid);

                    boolean exists = false;
                    for (int i = 0; i < resultsMatches.size(); i++) {
                        if (resultsMatches.get(i).getChildId().equals(chatid)) {
                            resultsMatches.set(i, obj);
                            obj = resultsMatches.remove(i);
                            resultsMatches.add(0, obj);
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                        resultsMatches.add(0, obj);

                    }
                    Log.d("lastSeen",""+lastSeen);

                    mMatchesAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private String convertMilliToRelative(long now) {
        String time = DateUtils.getRelativeDateTimeString(this, now, DateUtils.SECOND_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL).toString();
        return time;
    }

    private List<MatchesObject> getDataSetMatches() {
        return resultsMatches;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MatchesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}