package com.arwandar.myseriesaddict.api.converter;

import com.arwandar.myseriesaddict.api.dto.ImagesDTO;
import com.arwandar.myseriesaddict.api.model.Images;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ImagesConverter {

    public List<Images> convertDtoToImages(List<ImagesDTO> dtos) {
        if (dtos == null) dtos = new ArrayList<ImagesDTO>();
        List<Images> images = new ArrayList<>();
        for (ImagesDTO dto : dtos) {
            images.add(convertDtoToImages(dto));
        }
        return images;
    }


    public Images convertDtoToImages(ImagesDTO dto) {
        if (dto == null) return null;
        Images images = new Images();
        images.setmBanner(dto.getmBanner());
        images.setmBox(dto.getmBox());
        images.setmShow(dto.getmShow());
        return images;
    }
}
