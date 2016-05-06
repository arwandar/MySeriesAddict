package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.data.dto.UsersDTO;
import com.arwandar.myseriesaddict.data.model.MemberComplex;
import com.arwandar.myseriesaddict.data.model.Users;

/**
 * Created by olivi on 06/05/2016.
 */
public class MemberComplexConverter {
    public MemberComplex convertDtoToMember(MemberComplexDTO dto) {
        MemberComplex memberComplex = new MemberComplex();
        UserConverter userConverter = new UserConverter();

        memberComplex.setUser(userConverter.convertDtoToUser(dto.getUser()));
        memberComplex.setErrors(dto.getErrors());
        return memberComplex;
    }
}
