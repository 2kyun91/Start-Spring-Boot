package com.start.springboot.domain.board;

import com.start.springboot.domain.board.dto.BoardDto;
import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.navigation.NavBarRepositoryTests;
import com.start.springboot.domain.navigation.dto.NavBarDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Commit;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@SpringBootTest
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
    public BoardDto testGetBoard(Long boardId) {
        BoardDto boardDto = new BoardDto();
        Board board = boardService.getBoard(boardId);
        if (ObjectUtils.isEmpty(board)) {
            boardDto = testCreateBoard();
        } else {
            boardDto = board.toDto(board);
        }
        return boardDto;
    }

    /*
    * LazyInitializationException 발생하는 이유 : Service 바깥단에서 호출 결과 받을 때 영속 컨텍스트도 종료되기 때문
    * 해결 방법 : 해당하는 메소드에 @Transactional 선언, 또는 엔티티의 Fetch 전략을 Lazy에서 Eager로 변경 (후자 보다는 전자가 낫다.)
    * */
    @Test
    @Transactional
    @Commit
    public void testGetBoard2() {
        BoardDto boardDto = new BoardDto();
        Board board = boardService.getBoard(2L);
        if (!ObjectUtils.isEmpty(board)) {
            boardDto = board.toDto(board);
            System.out.println(boardDto);
        } else {
            boardDto = testCreateBoard();
            System.out.println(boardDto);
        }
    }

    @Test
    public void testGetBoardReturnDto() {
        boardService.getBoardReturnDto(1L);
    }

    @Test
    public BoardDto testCreateBoard() {
        NavBarDto navBarDto = new NavBarDto();
        navBarDto.setNavBarGnb("Dev_Log");
        navBarDto = navBarRepositoryTests.testGetNavBar(navBarDto);

        BoardDto boardDto = new BoardDto();
        boardDto.setNavBar(navBarDto.toEntity());
        boardDto.setBoardName("임시 게시판");
        boardDto.setBoardCreateUser("자바");

        Board board = boardService.createBoard(boardDto);
        boardDto = board.toDto(board);
        return boardDto;
    }

    @Test
    public void testUpdateBoard() {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardId(1L);
        boardDto.setBoardName("테스트 게시판 수정2");
        boardDto.setBoardUpdateUser("스프링");
        boardDto.setBoardUpdateDate(new Timestamp(System.currentTimeMillis()));
        boardService.updateBoard(boardDto);
    }
}
