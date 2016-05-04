package com.arwandar.myseriesaddict.data.converter;

import com.arwandar.myseriesaddict.data.dto.NotesDTO;
import com.arwandar.myseriesaddict.data.model.Notes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivi on 17/04/2016.
 */
public class NotesConverter {

    public List<Notes> convertDtoToNotes(List<NotesDTO> dtos) {
        if(dtos == null) dtos = new ArrayList<NotesDTO>();
        List<Notes> notes = new ArrayList<>();
        for(NotesDTO dto : dtos){
            notes.add(convertDtoToNotes(dto));
        }
        return notes;
    }

    public Notes convertDtoToNotes(NotesDTO dto){
        if(dto == null) return null;
        Notes notes = new Notes();
        notes.setmMean(dto.getmMean());
        notes.setmTotal(dto.getmTotal());
        notes.setmUser(dto.getmUser());
        return notes;
    }
}
