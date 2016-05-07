package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.ShowsUserDTO;
import com.arwandar.myseriesaddict.api.model.ShowsUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsUserConverter {

    public List<ShowsUser> convertDtoToShowsUser(List<ShowsUserDTO> dtos) {
        if (dtos == null) dtos = new ArrayList<ShowsUserDTO>();
        List<ShowsUser> showsUsers = new ArrayList<>();
        for (ShowsUserDTO dto : dtos) {
            showsUsers.add(convertDtoToShowsUser(dto));
        }
        return showsUsers;
    }

    public ShowsUser convertDtoToShowsUser(ShowsUserDTO dto) {
        if (dto == null) return null;
        ShowsUser showsUser = new ShowsUser();
        showsUser.setmArchived(dto.getmArchived());
        showsUser.setmFavorited(dto.getmFavorited());
        showsUser.setmLast(dto.getmLast());
        showsUser.setmRemaining(dto.getmRemaining());
        showsUser.setmStatus(dto.getmStatus());
        showsUser.setmTags(dto.getmTags());
        showsUser.setmSeen(dto.getmSeen());
        showsUser.setmDownloaded(dto.getmDownloaded());
        return showsUser;
    }
}
