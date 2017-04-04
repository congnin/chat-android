package com.congnin.chat.app.core.users.getall;

import android.text.TextUtils;

import com.congnin.chat.app.models.User;
import com.congnin.chat.app.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by congnc on 4/4/17.
 */

public class GetUsersInteractor implements GetUsersContract.Interactor {
    private static final String TAG = GetUsersInteractor.class.getSimpleName();

    private GetUsersContract.OnGetAllUsersListener mOnGetAllUsersListener;

    public GetUsersInteractor(GetUsersContract.OnGetAllUsersListener onGetAllUsersListener) {
        this.mOnGetAllUsersListener = onGetAllUsersListener;
    }

    @Override
    public void getAllUsersFromFirebase() {
        FirebaseDatabase.getInstance().getReference().child(Constants.ARG_USERS)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                        List<User> users = new ArrayList<User>();
                        while (dataSnapshots.hasNext()) {
                            DataSnapshot dataSnapshotChild = dataSnapshots.next();
                            User user = dataSnapshotChild.getValue(User.class);
                            if (!TextUtils.equals(user.getUid(), FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                users.add(user);
                            }
                        }
                        mOnGetAllUsersListener.onGetAllUsersSuccess(users);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        mOnGetAllUsersListener.onGetAllUsersFailure(databaseError.getMessage());
                    }
                });
    }

    @Override
    public void getChatUsersFromFirebase() {

    }
}
