package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.SeasonsDetailsDTO;
import com.arwandar.myseriesaddict.api.model.SeasonsDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class SeasonsDetailsConverter {

    public List<SeasonsDetails> convertDtoToSeasonsDetails(List<SeasonsDetailsDTO> dtos) {
        if (dtos == null) dtos = new ArrayList<SeasonsDetailsDTO>();
        List<SeasonsDetails> seasonsDetails = new ArrayList<>();
        for (SeasonsDetailsDTO dto : dtos) {
            seasonsDetails.add(convertDtoToSeasonsDetails(dto));
        }
        return seasonsDetails;
    }

    public SeasonsDetails convertDtoToSeasonsDetails(SeasonsDetailsDTO dto) {
        if (dto == null) return null;
        SeasonsDetails seasonsDetails = new SeasonsDetails();
        seasonsDetails.setmEpisodes(dto.getmEpisodes());
        seasonsDetails.setmNumber(dto.getmNumber());
        return seasonsDetails;
    }

}
