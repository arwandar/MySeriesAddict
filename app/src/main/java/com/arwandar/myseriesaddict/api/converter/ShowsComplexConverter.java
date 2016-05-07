package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.api.model.ShowsComplex;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsComplexConverter {

    public ShowsComplex convertDtoToShowsComplex(ShowsComplexDTO dto) {
        ShowsComplex showsComplex = new ShowsComplex();
        ShowsConverter showsConverter = new ShowsConverter();

        try {
            showsComplex.setmShows(showsConverter.convertDtoToShows(dto.getmShows()));
            showsComplex.setmErrors(dto.getmErrors());
        } catch (Exception e) {
            System.out.println(e);
        }
        return showsComplex;
    }
}
