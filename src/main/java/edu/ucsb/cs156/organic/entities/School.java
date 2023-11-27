package edu.ucsb.cs156.organic.entities;

import javax.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name = "schools")
public class School {
    @Id
    private String abbrev;
    private String name;
    private String termRegex;
    private String termDescription;
    private String termError;
}
