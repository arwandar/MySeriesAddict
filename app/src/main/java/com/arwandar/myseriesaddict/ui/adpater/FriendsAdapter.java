package com.arwandar.myseriesaddict.ui.adpater;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.MemberComplexConverter;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.model.User;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.activity.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Arwandar on 05/05/2016.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    private Activity mActivity;
    private List<User> mUsersList;

    public FriendsAdapter(Activity activity, List<User> pUsersList) {
        mActivity = activity;
        mUsersList = pUsersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_item_friend, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final User user = mUsersList.get(position);

        //Récupération des informations du membre

        CallManager.getFriendsInfosAsync(user.getmId(), new Callback<MemberComplexDTO>() {
            @Override
            public void onResponse(Call<MemberComplexDTO> call, Response<MemberComplexDTO> response) {
                if (response.isSuccessful()) {
                    MemberComplexConverter memberComplexConverter = new MemberComplexConverter();
                    User u = memberComplexConverter.convertDtoToMember(response.body()).getUser();

                    //TODO: rajouter des infos du genre xp
                    //TODO: gérer les comptes sans avatar (merci Val)
                    Picasso.with(mActivity).load(u.getmAvatar()).into(holder.mPictureView);
                    holder.mUserName.setText(user.getmLogin());
                } else {
                    //Todo: check si ça marche
                    if (response.code() == 400) {
                        Toast.makeText(mActivity, "Votre session a expiré, veuillez vous reconnecter.", Toast.LENGTH_SHORT).show();
                        SharedPrefsSingleton.setAccessToken("");
                        Intent intent = new Intent(mActivity, LoginActivity.class);
                        mActivity.getBaseContext().startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<MemberComplexDTO> call, Throwable t) {
                //Todo: check si ça marche
                Toast.makeText(mActivity, "Pas d'accès à internet, veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage(R.string.dialog_message_error)
                        .setTitle(R.string.dialog_title_error);
                builder.setNeutralButton(R.string.ok_error, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind((R.id.friend_name_textview))
        protected TextView mUserName;
        @Bind(R.id.friend_picture)
        protected ImageView mPictureView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
