package com.start.springboot.domain.navigation.dto;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.navigation.entity.NavBar;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NavBarDto {
    private Long navBarId;

    private String navBarGnb;

    private String navBarLnb;

    private List<Board> boards;

    public NavBar toEntity() {
        return NavBar.builder()
                .navBarId(navBarId)
                .navBarGnb(navBarGnb)
                .navBarLnb(navBarLnb)
                .boards(boards)
                .build();
    }
}
