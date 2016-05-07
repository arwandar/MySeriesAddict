package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.ErrorsDTO;
import com.arwandar.myseriesaddict.api.model.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 07/05/2016.
 */
public class ErrorsConverter {

    public List<Errors> convertDtoToErrors(List<ErrorsDTO> dtos) {
        if (dtos == null) dtos = new ArrayList<ErrorsDTO>();
        List<Errors> errors = new ArrayList<>();
        for (ErrorsDTO dto : dtos) {
            errors.add(convertDtoToErrors(dto));
        }
        return errors;
    }

    public Errors convertDtoToErrors(ErrorsDTO dto) {
        if (dto == null) return null;
        Errors errors = new Errors();
        errors.setmCode(dto.getmCode());
        errors.setmText(dto.getmText());
        return errors;
    }
}
