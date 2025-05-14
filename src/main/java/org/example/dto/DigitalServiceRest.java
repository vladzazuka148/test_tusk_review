package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.entity.DigitalService;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DigitalServiceRest {

    private UUID id;
    private String name;

    public DigitalServiceRest(DigitalService digitalService) {
        this.id = digitalService.getId();
        this.name = digitalService.getName();
    }
}
