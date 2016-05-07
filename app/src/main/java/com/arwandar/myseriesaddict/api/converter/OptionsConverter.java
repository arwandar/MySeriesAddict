package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.OptionsDTO;
import com.arwandar.myseriesaddict.api.model.Options;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 05/05/2016.
 */
public class OptionsConverter {

    public List<Options> convertDtoToOptions(List<OptionsDTO> dtos) {
        if (dtos == null) dtos = new ArrayList<OptionsDTO>();
        List<Options> options = new ArrayList<>();
        for (OptionsDTO dto : dtos) {
            options.add(convertDtoToOptions(dto));
        }
        return options;
    }

    public Options convertDtoToOptions(OptionsDTO dto) {
        if (dto == null) return null;
        Options options = new Options();
        options.setmTimelag(dto.getmTimelag());
        options.setmGlobal(dto.getmGlobal());
        options.setmFriendship(dto.getmFriendship());
        options.setmDownloaded(dto.getmDownloaded());
        options.setmNotation(dto.getmNotation());
        return options;
    }
}
