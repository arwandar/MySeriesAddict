package com.arwandar.myseriesaddict.factory;

import com.arwandar.myseriesaddict.model.Series;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Arwandar on 22/02/2016.
 */
public class SeriesFactory {

    public static List<Series> getSeriesList() {
        List<Series> seriesList = new ArrayList<>();

        seriesList.add(new Series("Elementary", "Sherlock Holmes des temps modernes après sevrage", true));
        seriesList.add(new Series("Parks and Recreation", "la vie du service parks and recreation de la ville  de Pawnee (Indiania)", false));


        return seriesList;
    }

    public static List<Series> getSeriesEnded() {
        List<Series> seriesList = getSeriesList();
        Iterator<Series> iterator = seriesList.iterator();
        while (iterator.hasNext()) {
            Series series = iterator.next();
            if (!series.isEnded()) {
                iterator.remove();
            }
        }
        return seriesList;
    }
}
