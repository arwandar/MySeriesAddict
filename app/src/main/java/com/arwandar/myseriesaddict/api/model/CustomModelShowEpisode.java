package com.arwandar.myseriesaddict.api.model;

/**
 * Created by olivi on 09/05/2016.
 */
public class CustomModelShowEpisode {

    private Shows mShow;
    private Unseen mUnseen;

    public Shows getmShow() {
        return mShow;
    }

    public void setmShow(Shows mShow) {
        this.mShow = mShow;
    }

    public Unseen getmUnseen() {
        return mUnseen;
    }

    public void setmUnseen(Unseen mUnseen) {
        this.mUnseen = mUnseen;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CustomModelShowEpisode) {
            return mShow.getmId().equals(((CustomModelShowEpisode) o).getmShow().getmId());
        }
        return false;
    }
}
