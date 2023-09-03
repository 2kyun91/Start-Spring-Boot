package com.start.springboot.domain.attach.dto;

import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class AttachDto {
    private Long attachId;

    private String attachPath;

    private String attachOriginalName;

    private String attachPhysicalName;

    private String attachShowYn;

    private Post post;

    private Timestamp attachCreateDate;

    private Timestamp attachUpdateDate;

    public Attach toEntity() {
        return Attach.builder()
                .attachId(attachId)
                .attachPath(attachPath)
                .attachOriginalName(attachOriginalName)
                .attachPhysicalName(attachPhysicalName)
                .attachShowYn(attachShowYn)
                .post(post)
                .attachCreateDate(attachCreateDate)
                .attachUpdateDate(attachUpdateDate)
                .build();
    }
}
