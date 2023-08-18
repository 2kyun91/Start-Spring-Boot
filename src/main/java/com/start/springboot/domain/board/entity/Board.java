package com.start.springboot.domain.board.entity;

import com.start.springboot.domain.board.dto.BoardDto;
import com.start.springboot.domain.navigation.entity.NavBar;
import com.start.springboot.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 게시판 엔티티 
 */
@Getter
//@Setter
@ToString
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "tb_board")
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "board_seq", initialValue = 1, allocationSize = 1)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOARD_SEQ_GENERATOR")
    private Long boardId; /* 게시판 Id */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_navBar_id") // navBarId 변수 만들고 이 변수를 외래키로 사용하게 바꿔보기
    private NavBar navBar; /* 게시판 구분 */

    @ToString.Exclude
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private Set<Post> posts; /* 게시판 내 게시글 */

    @NotNull
    private String boardName; /* 게시판 이름 */

    @ColumnDefault("'Y'")
    private String boardUseYn; /* 게시판 사용여부 */

    @NotNull
    private String boardCreateUser; /* 게시판 생성자 */

    private String boardUpdateUser; /* 게시판 수정자 */

    @CreationTimestamp
    private Timestamp boardCreateDate; /* 게시판 생성일 */

    @UpdateTimestamp
    private Timestamp boardUpdateDate; /* 게시판 수정일 */

    @Builder(toBuilder = true)
    public Board(Long boardId, NavBar navBar, /*@Singular*/ Set<Post> posts, String boardName, String boardUseYn, String boardCreateUser, String boardUpdateUser, Timestamp boardCreateDate, Timestamp boardUpdateDate) {
        this.boardId = boardId;
        this.navBar = navBar;
        this.posts = posts;
        this.boardName = boardName;
        this.boardUseYn = boardUseYn;
        this.boardCreateUser = boardCreateUser;
        this.boardUpdateUser = boardUpdateUser;
        this.boardCreateDate = boardCreateDate;
        this.boardUpdateDate = boardUpdateDate;
    }

    public BoardDto toDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardId(board.getBoardId());
        boardDto.setNavBar(board.getNavBar());
        boardDto.setPosts(board.getPosts());
        boardDto.setBoardName(board.getBoardName());
        boardDto.setBoardUseYn(board.getBoardUseYn());
        boardDto.setBoardCreateUser(board.getBoardCreateUser());
        boardDto.setBoardUpdateUser(board.getBoardUpdateUser());
        boardDto.setBoardCreateDate(board.getBoardCreateDate());
        boardDto.setBoardUpdateDate(board.getBoardUpdateDate());
        return boardDto;
    }
}
