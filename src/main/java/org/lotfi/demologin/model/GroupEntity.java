package org.lotfi.demologin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Table
public class GroupEntity {

    @Id
    @GeneratedValue
    private String groupId;

    private String name;
}

