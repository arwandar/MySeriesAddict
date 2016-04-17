package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.ImagesDTO;
import com.arwandar.myseriesaddict.data.model.Images;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class ImagesConverter {

    public List<Images> convertDtoToImages(List<ImagesDTO> dtos) {
        List<Images> images = new ArrayList<>();
        for (ImagesDTO dto : dtos) {
            images.add(convertDtoToImages(dto));
        }
        return images;
    }


    public Images convertDtoToImages(ImagesDTO dto) {
        Images images = new Images();
        images.setmBanner(dto.getmBanner());
        images.setmBox(dto.getmBox());
        images.setmShow(dto.getmShow());
        return images;
    }
}
