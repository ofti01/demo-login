package org.lotfi.demologin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue
    private int userId;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))

    private List<GroupEntity> grouplist;

}
