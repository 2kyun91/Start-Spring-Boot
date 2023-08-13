package com.start.springboot.domain.board.entity;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 게시판 엔티티 
 */
@Getter
@Setter
@ToString
@Entity
@DynamicInsert
@Table(name = "tb_board")
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "board_seq", initialValue = 1, allocationSize = 1)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOARD_SEQ_GENERATOR")
    private Long boardId; /* 게시판 Id */

    @NotNull
    private Long board_NavBarId; /* 게시판 구분 (외래키 설정 필요)*/

    @NotNull
    private String boardName; /* 게시판 이름 */

    @ColumnDefault("'Y'")
    private String boardUseYN; /* 게시판 사용여부 */

    @NotNull
    private String boardCreateUser; /* 게시판 생성자 */

    private String boardUpdateUser; /* 게시판 수정자 */

    @CreationTimestamp
    private Timestamp boardCreateDate; /* 게시판 생성일 */

    @UpdateTimestamp
    private Timestamp boardUpdateDate; /* 게시판 수정일 */
}
