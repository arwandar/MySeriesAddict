package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.UsersDTO;
import com.arwandar.myseriesaddict.api.model.Users;

/**
 * Created by olivi on 08/04/2016.
 */
public class UsersConverter {

    public Users convertDtoToUsers(UsersDTO dto) {
        Users users = new Users();
        UserConverter userConverter = new UserConverter();

        users.setUsers(userConverter.convertDtoToUsers(dto.getUsers()));
        users.setErrors(dto.getErrors());
        return users;
    }
}
