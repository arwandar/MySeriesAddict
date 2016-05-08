package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.UserDTO;
import com.arwandar.myseriesaddict.api.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 08/04/2016.
 */
public class UserConverter {

    public List<User> convertDtoToUsers(List<UserDTO> dtos) {
        if (dtos == null) dtos = new ArrayList<UserDTO>();
        List<User> users = new ArrayList<>();
        for (UserDTO dto : dtos) {
            users.add(convertDtoToUser(dto));
        }
        return users;
    }

    public User convertDtoToUser(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        StatsConverter statsConverter = new StatsConverter();
        OptionsConverter optionsConverter = new OptionsConverter();
        ShowsConverter showsConverter = new ShowsConverter();

        user.setmId(dto.getmId());
        user.setmLogin(dto.getmLogin());
        user.setmInAccount(dto.ismInAccount());
        user.setmCached(dto.getmCached());
        user.setmStats(statsConverter.convertDtoToStats(dto.getmStats()));
        user.setmXp(dto.getmXp());
        user.setMProfileBbanner(dto.getMProfileBbanner());
        user.setmAvatar(dto.getmAvatar());
        user.setmOptions(optionsConverter.convertDtoToOptions(dto.getmOptions()));
        user.setmFavorites(showsConverter.convertDtoToShows(dto.getmFavorites()));
        user.setmShows(showsConverter.convertDtoToShows(dto.getmShows()));

        return user;
    }

}
