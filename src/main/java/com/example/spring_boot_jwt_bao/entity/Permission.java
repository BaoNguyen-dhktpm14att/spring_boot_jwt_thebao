package com.example.spring_boot_jwt_bao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
public class Permission extends BaseEntity {
    private String permissionName, permissionKey;
}
