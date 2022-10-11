package com.ll.exam.app__2022_10_11.app.member.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.app__2022_10_11.app.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private boolean emailVerified;
    private long restCash;

    public String getName() {
        return username;
    }

    public Member(long id) {
        super(id);
    }
}
