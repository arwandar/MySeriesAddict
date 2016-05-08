package com.arwandar.myseriesaddict.api;

import com.arwandar.myseriesaddict.api.model.Shows;

/**
 * Created by Arwandar on 08/05/2016.
 */
public class AppContext {
    private static AppContext ourInstance = new AppContext();
    private static Shows showsSelected;

    private AppContext() {
    }

    public static AppContext getInstance() {
        return ourInstance;
    }

    public static Shows getShowsSelected() {
        return showsSelected;
    }

    public static void setShowsSelected(Shows pShowsSelected) {
        showsSelected = pShowsSelected;
    }
}
