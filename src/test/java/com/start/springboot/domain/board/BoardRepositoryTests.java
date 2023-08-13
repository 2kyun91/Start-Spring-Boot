package com.start.springboot.domain.board;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.navigation.NavBarRepositoryTests;
import com.start.springboot.domain.navigation.entity.NavBar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.util.ObjectUtils;

@SpringBootTest
@TestConfiguration
@Import({NavBarRepositoryTests.class})
public class BoardRepositoryTests {
    private final BoardService boardService;
    private final NavBarRepositoryTests navBarRepositoryTests;

    @Autowired
    public BoardRepositoryTests(BoardService boardService, NavBarRepositoryTests navBarRepositoryTests) {
        this.boardService = boardService;
        this.navBarRepositoryTests = navBarRepositoryTests;
    }

    @Test
    public void inspect() {
//        Class<?> clz = boardRepository.getClass();
//        System.out.println(clz.getName());
//        System.out.println("----------------------------");
//
//        Class<?>[] interfaces = clz.getInterfaces();
//        Stream.of(interfaces).forEach(itf -> System.out.println(itf.getName()));
//        System.out.println("----------------------------");
//
//        Class<?> superClasses = clz.getSuperclass();
//        System.out.println(superClasses.getName());
    }

    @Test
    public Board testGetBoard(Long boardId) {
        Board board = boardService.getBoard(boardId);
        if (ObjectUtils.isEmpty(board)) {
            board = testCreateBoard();
        }
        return board;
    }

    @Test
    public Board testCreateBoard() {
        NavBar navBar = navBarRepositoryTests.getNavBar("테스트", "");
        Board board = new Board();
        board.setBoard_NavBarId(navBar.getNavBarId());
        board.setBoardName("임시 게시판");
        board.setBoardCreateUser("자바");

        board = boardService.createBoard(board);
        System.out.println(board);
        return board;
    }
}
