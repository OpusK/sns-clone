package com.sns.snsclone.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class UserEntity {
    @Id
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    private String password;
}
