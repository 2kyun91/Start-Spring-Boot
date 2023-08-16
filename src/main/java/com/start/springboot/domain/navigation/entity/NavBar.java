package com.start.springboot.domain.navigation.entity;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.navigation.dto.NavBarDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 네비게이션바 엔티티
 */
@Getter
//@Setter
@ToString
@NoArgsConstructor
@Entity
@Data
@Table(name = "tb_nav_bar")
@SequenceGenerator(name = "NAVBAR_SEQ_GENERATOR", sequenceName = "navBar_seq", initialValue = 1, allocationSize = 1)
public class NavBar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NAVBAR_SEQ_GENERATOR")
    private Long navBarId;

    @NotNull
    private String navBarGnb; /* Gnb */

    private String navBarLnb; /* Lnb */

    @ToString.Exclude
    @OneToMany(mappedBy = "navBar", cascade = CascadeType.ALL)
    private Set<Board> boards = new LinkedHashSet<>();

    @Builder(toBuilder = true)
    public NavBar(Long navBarId, String navBarGnb, String navBarLnb, /*@Singular*/ Set<Board> boards) {
        this.navBarId = navBarId;
        this.navBarGnb = navBarGnb;
        this.navBarLnb = navBarLnb;
        this.boards = boards;
    }

    public NavBarDto toDto(NavBar navBar) {
        NavBarDto navBarDto = new NavBarDto();
        navBarDto.setNavBarId(navBar.getNavBarId());
        navBarDto.setNavBarGnb(navBar.getNavBarGnb());
        navBarDto.setNavBarLnb(navBar.getNavBarLnb());
        navBarDto.setBoards(navBar.getBoards());
        return navBarDto;
    }
}
