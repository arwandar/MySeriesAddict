package com.arwandar.myseriesaddict.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.model.Shows;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.arwandar.myseriesaddict.ui.activity.CustomActivity;
import com.arwandar.myseriesaddict.ui.activity.ShowsDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by olivi on 11/05/2016.
 */
public class ShowDetailInfoFragment extends Fragment {

    @Bind(R.id.shows_detail_title)
    TextView mTitle;
    @Bind(R.id.shows_detail_pending)
    TextView mPending;
    @Bind(R.id.shows_detail_description)
    TextView mDescription;

    @Bind(R.id.shows_detail_archived)
    Switch mSwitchArchived;
    @Bind(R.id.shows_detail_favorite)
    Switch mSwitchFavorite;

    /**
     * flag pour interdire les appels parallèles
     */
    private boolean alreadyArchivedCall = true, alreadyFavoriteCall = true;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_detail_info, container, false);
        ButterKnife.bind(this, view);

        setUI();

        return view;
    }

    public void setUI() {
        Shows shows = ((ShowsDetailActivity) getActivity()).getShows();
        alreadyArchivedCall = true;
        alreadyFavoriteCall = true;
        if (shows != null) {
            mTitle.setText(shows.getmTitle());
            String shows_detail_pending = shows.getmStatus() + " ("
                    + shows.getmUser().getmRemaining() + " non-vu(s) épisodes sur "
                    + shows.getmEpisodes() + ")";
            mPending.setText(shows_detail_pending);
            mDescription
                    .setText(shows.getmDescription());

            mSwitchArchived.setChecked(shows.getmUser().getmArchived().equals("true"));
            mSwitchFavorite.setChecked(shows.getmUser().getmFavorited().equals("true"));
            alreadyArchivedCall = false;
            alreadyFavoriteCall = false;
        }
    }

    /**
     * gestion du swipe favori
     */
    @OnCheckedChanged(R.id.shows_detail_favorite)
    public void setFavorite() {
        //gestion de l'unicité de l'appel
        if (!alreadyFavoriteCall) {
            Shows shows = ((ShowsDetailActivity) getActivity()).getShows();
            alreadyFavoriteCall = true;
            if (mSwitchFavorite.isChecked()) {
                markAsFavorite(shows);
            } else {
                markAsNoFavorite(shows);
            }
        }
    }

    /**
     * appel au WS pour marquer la série comme favorite
     *
     * @param pShows
     */
    private void markAsFavorite(Shows pShows) {
        CallManager.markShowAsFavoriteAsync(pShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                            Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    R.string.mark_favorite_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchFavorite.setChecked(true);
                            alreadyFavoriteCall = false;
                        } else {
                            ((CustomActivity) getActivity()).showErrorLogin(response.code());
                            alreadyFavoriteCall = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        ((CustomActivity) getActivity()).showError();
                        alreadyFavoriteCall = false;
                    }
                });
    }

    /**
     * appel au WS pour marquer la série comme non favorite
     *
     * @param pShows
     */
    private void markAsNoFavorite(Shows pShows) {
        CallManager.deleteShowFromFavoriteAsync(pShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                            Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    R.string.mark_no_favorite_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchFavorite.setChecked(false);
                            alreadyFavoriteCall = false;
                        } else {
                            ((CustomActivity) getActivity()).showErrorLogin(response.code());
                            alreadyFavoriteCall = false;
                        }
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        ((CustomActivity) getActivity()).showError();
                        alreadyFavoriteCall = false;
                    }
                });
    }

    /**
     * gestion du swipe archivé
     */
    @OnCheckedChanged(R.id.shows_detail_archived)
    public void setArchived() {
        //gestion de l'unicité de l'appel
        if (!alreadyArchivedCall) {
            Shows shows = ((ShowsDetailActivity) getActivity()).getShows();
            alreadyArchivedCall = true;
            if (mSwitchArchived.isChecked()) {
                markAsArchived(shows);
            } else {
                markAsNoArchived(shows);
            }
        }
    }

    /**
     * appel au WS pour marquer la série comme archivée
     *
     * @param pShows
     */
    private void markAsNoArchived(Shows pShows) {
        CallManager.deleteShowFromArchivedAsync(pShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                            Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    R.string.mark_no_archived_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchArchived.setChecked(false);
                        } else {
                            ((CustomActivity) getActivity()).showErrorLogin(response.code());
                        }
                        alreadyArchivedCall = false;
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        ((CustomActivity) getActivity()).showError();
                        alreadyArchivedCall = false;
                    }
                });
    }

    /**
     * appel au WS pour marquer la série comme non archivée
     *
     * @param pShows
     */
    private void markAsArchived(Shows pShows) {//si la série n'est pas encore archivée
        CallManager.markShowAsArchivedAsync(pShows.getmId(),
                new Callback<ShowDisplayComplexDTO>() {
                    @Override
                    public void onResponse(Call<ShowDisplayComplexDTO> call,
                            Response<ShowDisplayComplexDTO> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    R.string.mark_archived_ok_message,
                                    Toast.LENGTH_SHORT).show();
                            mSwitchArchived.setChecked(true);
                        } else {
                            ((CustomActivity) getActivity()).showErrorLogin(response.code());
                        }
                        alreadyArchivedCall = false;
                    }

                    @Override
                    public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                        ((CustomActivity) getActivity()).showError();
                        alreadyArchivedCall = false;
                    }
                });
    }
}

