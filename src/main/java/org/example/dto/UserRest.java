package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.entity.User;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserRest {

    private UUID id;
    private String name;
    private Integer age;

    public UserRest(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }
}
