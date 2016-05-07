package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.ErrorsComplexDTO;
import com.arwandar.myseriesaddict.api.model.ErrorsComplex;

/**
 * Created by olivi on 07/05/2016.
 */
public class ErrorsComplexConverter {

    public ErrorsComplex convertDtoToErrorsComplex(ErrorsComplexDTO dto) {
        ErrorsComplex errorsComplex = new ErrorsComplex();

        try {
            errorsComplex.setmErrors(dto.getmErrors());
        } catch (Exception e) {
            System.out.println(e);
        }
        return errorsComplex;
    }
}
