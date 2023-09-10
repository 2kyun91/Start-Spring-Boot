package com.start.springboot.domain.attach.repository;

import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.post.dto.PostBoardDto;

import java.util.List;

public interface AttachRepositoryCustom {
    long updateAttach(Attach attach);

    List<Attach> getAttachList(Long postId);

    void deleteAttachList(PostBoardDto postBoardDto);
}
