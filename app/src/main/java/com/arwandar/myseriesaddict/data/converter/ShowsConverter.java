package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.ShowsDTO;
import com.arwandar.myseriesaddict.data.model.Shows;
import com.arwandar.myseriesaddict.data.model.ShowsUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsConverter {
    public List<Shows> convertDtoToShows(List<ShowsDTO> dtos) {
        if(dtos == null) dtos = new ArrayList<ShowsDTO>();
        List<Shows> shows = new ArrayList<>();
        for (ShowsDTO dto : dtos) {
            shows.add(convertDtoToShows(dto));
        }
        return shows;
    }

    public Shows convertDtoToShows(ShowsDTO dto) {
        Shows shows = new Shows();
        SeasonsDetailsConverter seasonsDetailsConverter = new SeasonsDetailsConverter();
        ImagesConverter imagesConverter = new ImagesConverter();
        NotesConverter notesConverter = new NotesConverter();
        ShowsUserConverter showsUserConverter = new ShowsUserConverter();
        UnseenConverter unseenConverter = new UnseenConverter();


        shows.setmCreation(dto.getmCreation());
        shows.setmGenres(dto.getmGenres());
        shows.setmThetvdbid(dto.getmThetvdbid());
        shows.setmSeasonsDetails(seasonsDetailsConverter.convertDtoToSeasonsDetails(dto.getmSeasonsDetails()));
        shows.setmEpisodes(dto.getmEpisodes());
        shows.setmAliases(dto.getmAliases());
        shows.setmNetwork(dto.getmNetwork());
        shows.setmId(dto.getmId());
        shows.setmFollowers(dto.getmFollowers());
        shows.setmTitle(dto.getmTitle());
        shows.setmInAccount(dto.getmInAccount());
        shows.setmDescription(dto.getmDescription());
        shows.setmLength(dto.getmLength());
        shows.setmStatus(dto.getmStatus());
        shows.setmSimilars(dto.getmSimilars());
        shows.setmSeasons(dto.getmSeasons());
        shows.setmImdbid(dto.getmImdbid());
        shows.setmImages(imagesConverter.convertDtoToImages(dto.getmImages()));
        shows.setmRating(dto.getmRating());
        shows.setmLanguage(dto.getmLanguage());
        shows.setmResourceurl(dto.getmResourceurl());
        shows.setmNotes(notesConverter.convertDtoToNotes(dto.getmNotes()));
        shows.setmUser(showsUserConverter.convertDtoToShowsUser(dto.getmUser()));
        shows.setmCharacters(dto.getmCharacters());
        shows.setmComments(dto.getmComments());
        shows.setmRemaining(dto.getmRemaining());
        shows.setmUnseen(unseenConverter.convertDtoToUnseen(dto.getmUnseen()));

        return shows;
    }
}
