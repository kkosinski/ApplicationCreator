package com.wintermute.applicationcreator.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Language
{
    private String language;
    private int rating;
    private String levelDesc;
}
