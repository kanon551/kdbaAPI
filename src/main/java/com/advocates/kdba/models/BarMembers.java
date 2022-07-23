package com.advocates.kdba.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Component
@Document(collection = "kdbaMembers")
public class BarMembers {

    @Id
    @Schema(hidden = true)
    private String _id;

    private String enrollmentNo;
    private String enrollmentDate;

    private String firstname;

    private String dob;
    private String gender;
    //private boolean admin;
    private String mobile;


    private String lfNumber;
    private String admissionDate;

    private boolean cop;
    private String copNumber;

    //private String password;
    private Binary image;

}
