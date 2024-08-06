package org.example.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
@JsonDeserialize
public class Customer {

    private int id;
    private String name;
    private String email;
    private String contactNumber;

}
