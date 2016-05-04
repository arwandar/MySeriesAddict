package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.EpisodeDTO;
import com.arwandar.myseriesaddict.data.model.Episode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 04/05/2016.
 */
public class EpisodeConverter {
    public List<Episode> convertDtoToEpisode(List<EpisodeDTO> dtos) {
        if(dtos == null) dtos = new ArrayList<EpisodeDTO>();
        List<Episode> episodes = new ArrayList<>();
        for (EpisodeDTO dto : dtos) {
            episodes.add(convertDtoToEpisode(dto));
        }
        return episodes;
    }

    public Episode convertDtoToEpisode(EpisodeDTO dto) {
        Episode episode = new Episode();
        ShowsConverter showsConverter = new ShowsConverter();
        NotesConverter notesConverter = new NotesConverter();
        ShowsUserConverter showsUserConverter = new ShowsUserConverter();

        episode.setmComments(dto.getmComments());
        episode.setmCode(dto.getmCode());
        episode.setmDate(dto.getmDate());
        episode.setmDescription(dto.getmDescription());
        episode.setmEpisode(dto.getmEpisode());
        episode.setmId(dto.getmId());
        episode.setmGlobal(dto.getmGlobal());
        episode.setmNote(notesConverter.convertDtoToNotes(dto.getmNote()));
        episode.setmSpecial(dto.getmSpecial());
        episode.setmSubtitles(dto.getmSubtitles());
        episode.setmThetvdbid(dto.getmThetvdbid());
        episode.setmShow(showsConverter.convertDtoToShows(dto.getmShow()));
        episode.setmYoutubeId(dto.getmYoutubeId());
        episode.setmTitle(dto.getmTitle());
        episode.setmSeason(dto.getmSeason());
        episode.setmUser(showsUserConverter.convertDtoToShowsUser(dto.getmUser()));

        return episode;
    }
}
