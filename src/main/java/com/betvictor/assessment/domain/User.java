package com.betvictor.assessment.domain;


import com.betvictor.assessment.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Generated
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String userId;
    private String message;
    private Timestamp timestamp;


}
