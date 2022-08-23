package com.advocates.kdba.models;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Component
@Document(collection = "kdbaAdmins")
public class BarAdmin {

    @Id
    @Schema(hidden = true)
    private String _id;

    private String email;
    private String password;
}
