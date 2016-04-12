package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.UserDTO;
import com.arwandar.myseriesaddict.data.dto.UsersDTO;
import com.arwandar.myseriesaddict.model.User;
import com.arwandar.myseriesaddict.model.Users;

import java.util.ArrayList;
import java.util.List;

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
