package com.start.springboot.domain.post.entity;

import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.reply.entity.Reply;
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
import java.util.List;

/**
 * 게시글 엔티티
 */
@Getter
@ToString
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "tb_post")
@SequenceGenerator(name = "POST_SEQ_GENERATOR", sequenceName = "post_seq", initialValue = 1, allocationSize = 1)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="POST_SEQ_GENERATOR")
    private Long postId; /* 게시글 Id */

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_board_id")
    private Board board; /* 게시판 Id */

    @ToString.Exclude
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Attach> attaches; /* 게시글 내 첨부파일 */

    @ToString.Exclude
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replies;

    private String postTitle; /* 게시글 제목 */

    @NotNull
    private String postContent; /* 게시글 내용 */

    @ColumnDefault("'Y'")
    private String postShowYn; /* 게시글 노출여부 */

    @NotNull
    private String postWriter; /* 게시글 작성자 */

    @ColumnDefault("0")
    private int postViewCount; /* 게시글 조회수 */

    @CreationTimestamp
    private Timestamp postCreateDate; /* 게시글 생성일 */

    @UpdateTimestamp
    private Timestamp postUpdateDate; /* 게시글 수정일 */

    @Builder(toBuilder = true)
    public Post(Long postId, Board board, List<Attach> attaches, List<Reply> replies, String postTitle, String postContent, String postShowYn, String postWriter, int postViewCount, Timestamp postCreateDate, Timestamp postUpdateDate) {
        this.postId = postId;
        this.board = board;
        this.attaches = attaches;
        this.replies = replies;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postShowYn = postShowYn;
        this.postWriter = postWriter;
        this.postViewCount = postViewCount;
        this.postCreateDate = postCreateDate;
        this.postUpdateDate = postUpdateDate;
    }

    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setBoard(post.getBoard());
        postDto.setAttaches(post.getAttaches());
        postDto.setReplies(post.getReplies());
        postDto.setPostTitle(post.getPostTitle());
        postDto.setPostContent(post.getPostContent());
        postDto.setPostShowYn(post.getPostShowYn());
        postDto.setPostWriter(post.getPostWriter());
        postDto.setPostViewCount(post.getPostViewCount());
        postDto.setPostCreateDate(post.getPostCreateDate());
        postDto.setPostUpdateDate(post.getPostUpdateDate());
        return postDto;
    }
}
