package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.ShowDisplayComplexDTO;
import com.arwandar.myseriesaddict.api.model.ShowDisplayComplex;

/**
 * Created by olivi on 07/05/2016.
 */
public class ShowDisplayComplexConverter {

    public ShowDisplayComplex convertDtoToShowDisplayComplex(ShowDisplayComplexDTO dto) {
        ShowDisplayComplex showDisplayComplex = new ShowDisplayComplex();
        ShowsConverter showsConverter = new ShowsConverter();

        try {
            showDisplayComplex.setmShow(showsConverter.convertDtoToShows(dto.getmShow()));
            showDisplayComplex.setmErrors(dto.getmErrors());
        } catch (Exception e) {
            System.out.println(e);
        }
        return showDisplayComplex;
    }
}
