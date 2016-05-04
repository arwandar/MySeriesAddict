package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.EpisodeComplexDTO;
import com.arwandar.myseriesaddict.data.model.EpisodeComplex;

/**
 * Created by olivi on 04/05/2016.
 */
public class EpisodeComplexConverter {

    public EpisodeComplex convertDtoToEpisodeComplex(EpisodeComplexDTO dto) {
        EpisodeComplex episodeComplex = new EpisodeComplex();
        EpisodeConverter episodeConverter = new EpisodeConverter();

        try {
            episodeComplex.setmEpisodes(episodeConverter.convertDtoToEpisode(dto.getmEpisode()));
            episodeComplex.setmErrors(dto.getmErrors());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return episodeComplex;
    }
}
