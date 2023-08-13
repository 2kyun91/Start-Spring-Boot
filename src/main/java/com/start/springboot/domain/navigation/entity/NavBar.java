package com.start.springboot.domain.navigation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 네비게이션바 엔티티
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_nav_bar")
@SequenceGenerator(name = "NAVBAR_SEQ_GENERATOR", sequenceName = "navBar_seq", initialValue = 1, allocationSize = 1)
public class NavBar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NAVBAR_SEQ_GENERATOR")
    private Long navBarId; /* 네비게이션바 Id */

    @NotNull
    private String navBarGnb; /* Gnb(유니크 설정 필요) */

    private String nabBarLnb; /* Lnb */
}
