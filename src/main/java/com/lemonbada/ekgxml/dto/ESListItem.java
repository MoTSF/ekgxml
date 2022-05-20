package com.lemonbada.ekgxml.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.MessageFormat;

@Data
@AllArgsConstructor
public class ESListItem {
    private String key;
    private String value;
    @Override
    public String toString(){
        return MessageFormat.format("{0}:{1}", key, value);
    }
}
