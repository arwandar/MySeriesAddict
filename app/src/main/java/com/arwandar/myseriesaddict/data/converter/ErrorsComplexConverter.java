package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.ErrorsComplexDTO;
import com.arwandar.myseriesaddict.data.model.ErrorsComplex;

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
