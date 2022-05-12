package org.example.objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    String email;
    String password;
    String job;
    String name;
}
