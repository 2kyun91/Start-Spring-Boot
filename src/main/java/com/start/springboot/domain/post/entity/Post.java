package com.start.springboot.domain.post.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 게시글 엔티티
 */
@Getter
@Setter
@ToString
@Entity
@DynamicInsert
@Table(name = "tb_post")
@SequenceGenerator(name = "POST_SEQ_GENERATOR", sequenceName = "post_seq", initialValue = 1, allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="POST_SEQ_GENERATOR")
    private Long postId; /* 게시글 Id */

    @NotNull
    private Long boardId; /* 게시판 Id (외래키 설정 필요)*/

    @NotNull
    private String postTitle; /* 게시글 제목 */

    @NotNull
    private String postContent; /* 게시글 내용 */

    @ColumnDefault("'Y'")
    private String postShowYN; /* 게시글 노출여부 */

    @NotNull
    private String postWriter; /* 게시글 작성자 */

    @ColumnDefault("0")
    private int postViewCount; /* 게시글 조회수 */

    @CreationTimestamp
    private Timestamp postCreateDate; /* 게시글 생성일 */

    @UpdateTimestamp
    private Timestamp postUpdateDate; /* 게시글 수정일 */

    /* 게시글 댓글수(수정추가필요) */
//    private List<Reply> postReply;
//    private Optional<Reply> postReply;

    /* 게시글 첨부파일(수정추가필요) */
    //private AttachFile postAttachFile;
}
