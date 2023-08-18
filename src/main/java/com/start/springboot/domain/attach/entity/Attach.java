package com.start.springboot.domain.attach.entity;

import com.start.springboot.domain.attach.dto.AttachDto;
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

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_attach")
@DynamicInsert
@SequenceGenerator(name = "ATTACH_SEQ_GENERATOR", sequenceName = "attach_seq", initialValue = 1, allocationSize = 1)
public class Attach {
    @Id
    @GeneratedValue(generator = "ATTACH_SEQ_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long attachId; /* 첨부파일 Id */

    @NotNull
    private String attachPath; /* 첨부파일 경로 */

    @ColumnDefault("'Y'")
    private String attachShowYn; /* 첨부파일 노출 여부 */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_post_id")
    private Post post;

    @CreationTimestamp
    private Timestamp attachCreateDate; /* 첨부파일 경로 */

    @UpdateTimestamp
    private Timestamp attachUpdateDate; /* 첨부파일 경로 */

    @Builder
    public Attach(Long attachId, String attachPath, String attachShowYn, Post post, Timestamp attachCreateDate, Timestamp attachUpdateDate) {
        this.attachId = attachId;
        this.attachPath = attachPath;
        this.attachShowYn = attachShowYn;
        this.post = post;
        this.attachCreateDate = attachCreateDate;
        this.attachUpdateDate = attachUpdateDate;
    }

    public AttachDto toDto(Attach attach) {
        AttachDto attachDto = new AttachDto();
        attachDto.setAttachId(attach.getAttachId());
        attachDto.setAttachPath(attach.getAttachPath());
        attachDto.setAttachShowYn(attach.getAttachShowYn());
        attachDto.setPost(attach.getPost());
        attachDto.setAttachCreateDate(attach.getAttachCreateDate());
        attachDto.setAttachUpdateDate(attach.getAttachUpdateDate());
        return attachDto;
    }
}
