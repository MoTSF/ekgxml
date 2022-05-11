package com.lemonbada.ekgxml.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ProcessLog {
    Long id;
    String fileName;
    String parseYn;
    String esYn;

    public boolean isAllDone(){
        return "Y".equalsIgnoreCase(this.parseYn) && "Y".equalsIgnoreCase(this.esYn);
    }
}
