package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.ShowsComplexDTO;
import com.arwandar.myseriesaddict.data.model.ShowsComplex;

/**
 * Created by olivi on 17/04/2016.
 */
public class ShowsComplexConverter {

    public ShowsComplex convertDtoToShowsComplex(ShowsComplexDTO dto) {
        ShowsComplex showsComplex = new ShowsComplex();
        ShowsConverter showsConverter = new ShowsConverter();

        showsComplex.setmShows(showsConverter.convertDtoToShows(dto.getmShows()));
        showsComplex.setmErrors(dto.getmErrors());
        return showsComplex;
    }
}
