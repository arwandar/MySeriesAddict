package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.UserDTO;
import com.arwandar.myseriesaddict.data.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 08/04/2016.
 */
public class UserConverter {

    public List<User> convertDtoToUsers(List<UserDTO> dtos) {
        if(dtos == null) dtos = new ArrayList<UserDTO>();
        List<User> users = new ArrayList<>();
        for (UserDTO dto : dtos) {
            users.add(convertDtoToUser(dto));
        }
        return users;
    }

    public User convertDtoToUser(UserDTO dto) {
        User user = new User();
        user.setmId(dto.getmId());
        user.setmLogin(dto.getmLogin());
        user.setmInAccount(dto.ismInAccount());
        return user;
    }

}
