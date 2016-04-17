package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.UsersDTO;
import com.arwandar.myseriesaddict.data.model.Users;

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
