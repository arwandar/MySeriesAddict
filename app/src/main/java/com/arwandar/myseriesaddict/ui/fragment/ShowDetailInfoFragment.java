package com.arwandar.myseriesaddict.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.converter.ShowDisplayComplexConverter;
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

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    String mShowId;
    Shows mShows;

    /**
     * flag pour interdire les appels parallèles
     */
    private boolean alreadyArchivedCall, alreadyFavoriteCall;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_detail_info, container, false);
        ButterKnife.bind(this, view);

        mShowId = ((ShowsDetailActivity) getActivity()).getShowId();
        ((ShowsDetailActivity) getActivity()).setSwipeLayout(mSwipeRefreshLayout);

        getContent();

        return view;
    }

    private void getContent() {
        ((ShowsDetailActivity) getActivity()).setRefreshing();
        CallManager.getShowDisplayAsync(mShowId, new Callback<ShowDisplayComplexDTO>() {
            @Override
            public void onResponse(Call<ShowDisplayComplexDTO> call,
                    Response<ShowDisplayComplexDTO> response) {
                if (response.isSuccessful()) {
                    ShowDisplayComplexConverter converter = new ShowDisplayComplexConverter();
                    mShows = converter.convertDtoToShowDisplayComplex(response.body()).getmShow();
                    ((ShowsDetailActivity) getActivity()).isDetailsLoaded(true);
                    ((ShowsDetailActivity) getActivity())
                            .onDataLoaded(mShows.getmImages().getmShow());
                    setUI();
                } else {
                    ((CustomActivity) getActivity()).showErrorLogin(response.code());
                }
            }

            @Override
            public void onFailure(Call<ShowDisplayComplexDTO> call, Throwable t) {
                ((CustomActivity) getActivity()).showError();
            }
        });
    }

    public void setUI() {
        alreadyArchivedCall = true;
        alreadyFavoriteCall = true;
        if (mShows != null) {
            mTitle.setText(mShows.getmTitle());
            String shows_detail_pending = mShows.getmStatus() + " ("
                    + mShows.getmUser().getmRemaining() + " non-vu(s) épisodes sur "
                    + mShows.getmEpisodes() + ")";
            mPending.setText(shows_detail_pending);
            mDescription
                    .setText(mShows.getmDescription());

            //Picasso.with(getContext()).load(mShows.getmImages().getmShow()).into(image);

            mSwitchArchived.setChecked(mShows.getmUser().getmArchived().equals("true"));
            mSwitchFavorite.setChecked(mShows.getmUser().getmFavorited().equals("true"));
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
            alreadyFavoriteCall = true;
            if (mSwitchFavorite.isChecked()) {
                markAsFavorite(mShows);
            } else {
                markAsNoFavorite(mShows);
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
            alreadyArchivedCall = true;
            if (mSwitchArchived.isChecked()) {
                markAsArchived(mShows);
            } else {
                markAsNoArchived(mShows);
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

