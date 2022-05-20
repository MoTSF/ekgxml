package com.lemonbada.ekgxml.util;

import com.lemonbada.ekgxml.dto.ESListItem;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String listToESString(List<ESListItem> list){
        return list.stream()
                .filter(listItem -> StringUtils.isNotBlank(listItem.getValue()))
                .map(ESListItem::toString)
                .collect(Collectors.joining("$$"));
    }
}
