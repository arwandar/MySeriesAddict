package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.model.MemberComplex;

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
