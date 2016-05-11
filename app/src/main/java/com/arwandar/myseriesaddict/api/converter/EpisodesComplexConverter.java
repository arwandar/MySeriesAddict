package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.EpisodesComplexDTO;
import com.arwandar.myseriesaddict.api.model.EpisodesComplex;

/**
 * Created by olivi on 11/05/2016.
 */
public class EpisodesComplexConverter {
    public EpisodesComplex convertDtoToEpisodesComplex(EpisodesComplexDTO dto) {
        EpisodesComplex episodesComplex = new EpisodesComplex();
        EpisodeConverter episodeConverter = new EpisodeConverter();

        try {
            episodesComplex.setmEpisodes(episodeConverter.convertDtoToEpisode(dto.getmEpisodes()));
            episodesComplex.setmErrors(dto.getmErrors());
        } catch (Exception e) {
            System.out.println(e);
        }
        return episodesComplex;
    }

}
