package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.UnseenDTO;
import com.arwandar.myseriesaddict.data.model.Unseen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 04/05/2016.
 */
public class UnseenConverter {

    public List<Unseen> convertDtoToUnseen(List<UnseenDTO> dtos) {
        if(dtos == null) dtos = new ArrayList<UnseenDTO>();
        List<Unseen> unseens = new ArrayList<>();
        for (UnseenDTO dto : dtos) {
            unseens.add(convertDtoToUnseen(dto));
        }
        return unseens;
    }

    public Unseen convertDtoToUnseen(UnseenDTO dto) {
        Unseen unseen = new Unseen();
        ShowsConverter showsConverter = new ShowsConverter();
        NotesConverter notesConverter = new NotesConverter();
        ShowsUserConverter showsUserConverter = new ShowsUserConverter();

        unseen.setmComments(dto.getmComments());
        unseen.setmCode(dto.getmCode());
        unseen.setmDate(dto.getmDate());
        unseen.setmDescription(dto.getmDescription());
        unseen.setmEpisode(dto.getmEpisode());
        unseen.setmId(dto.getmId());
        unseen.setmGlobal(dto.getmGlobal());
        unseen.setmNote(notesConverter.convertDtoToNotes(dto.getmNote()));
        unseen.setmSpecial(dto.getmSpecial());
        unseen.setmSubtitles(dto.getmSubtitles());
        unseen.setmThetvdbid(dto.getmThetvdbid());
        unseen.setmShow(showsConverter.convertDtoToShows(dto.getmShow()));
        unseen.setmYoutubeId(dto.getmYoutubeId());
        unseen.setmTitle(dto.getmTitle());
        unseen.setmSeason(dto.getmSeason());
        unseen.setmUser(showsUserConverter.convertDtoToShowsUser(dto.getmUser()));

        return unseen;
    }
}
