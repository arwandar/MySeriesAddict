package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.StatsDTO;
import com.arwandar.myseriesaddict.api.model.Stats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 05/05/2016.
 */
public class StatsConverter {

    public List<Stats> convertDtoToStats(List<StatsDTO> dtos) {
        if (dtos == null) dtos = new ArrayList<StatsDTO>();
        List<Stats> stats = new ArrayList<>();
        for (StatsDTO dto : dtos) {
            stats.add(convertDtoToStats(dto));
        }
        return stats;
    }

    public Stats convertDtoToStats(StatsDTO dto) {
        if (dto == null) return null;
        Stats stats = new Stats();
        stats.setmProgress(dto.getmProgress());
        stats.setmBadges(dto.getmBadges());
        stats.setmFriends(dto.getmFriends());
        stats.setmSeasons(dto.getmSeasons());
        stats.setmMovies(dto.getmMovies());
        stats.setmEpisodesToWatch(dto.getmEpisodesToWatch());
        stats.setmTimeOnTv(dto.getmTimeOnTv());
        stats.setmTimeToSpend(dto.getmTimeToSpend());
        stats.setmEpisodes(dto.getmEpisodes());
        stats.setmComments(dto.getmComments());
        stats.setmShows(dto.getmShows());

        return stats;
    }
}
