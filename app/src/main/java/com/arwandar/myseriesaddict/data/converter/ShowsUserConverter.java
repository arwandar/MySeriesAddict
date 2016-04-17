package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.ShowsUserDTO;
import com.arwandar.myseriesaddict.data.model.ShowsUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsUserConverter {

    public List<ShowsUser> convertDtoToShowsUser(List<ShowsUserDTO> dtos) {
        List<ShowsUser> showsUsers = new ArrayList<>();
        for (ShowsUserDTO dto : dtos) {
            showsUsers.add(convertDtoToShowsUser(dto));
        }
        return showsUsers;
    }

    public ShowsUser convertDtoToShowsUser(ShowsUserDTO dto) {
        ShowsUser showsUser = new ShowsUser();
        showsUser.setmArchived(dto.getmArchived());
        showsUser.setmFavorited(dto.getmFavorited());
        showsUser.setmLast(dto.getmLast());
        showsUser.setmRemaining(dto.getmRemaining());
        showsUser.setmStatus(dto.getmStatus());
        showsUser.setmTags(dto.getmTags());
        return showsUser;
    }
}
