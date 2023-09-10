package com.start.springboot.domain.attach.service;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.domain.attach.dto.AttachDto;
import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.attach.repository.AttachRepository;
import com.start.springboot.domain.post.dto.PostBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttachService {
    private final AttachRepository attachRepository;

    public AttachDto createAttach(AttachDto attachDto) {
        Attach attach = attachRepository.save(attachDto.toEntity());
        return attach.toDto(attach);
    }

    public void updateAttach(AttachDto attachDto) {
        attachRepository.updateAttach(attachDto.toEntity());
    }

    public void deleteAttach(Long attachId) {
        attachRepository.deleteById(attachId);
    }

    public void createPostIncludeDummyAttaches(List<Attach> attaches) {
        attachRepository.saveAll(attaches);
    }

    public AttachDto getAttach(Long attachId) {
        Attach attach = attachRepository.findById(attachId).orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return attach.toDto(attach);
    }

    public List<Attach> getAttachList(Long postId) {
        List<Attach> attachList = attachRepository.getAttachList(postId);
        return attachList;
    }

    public void deleteAttachList(PostBoardDto postBoardDto) {
        attachRepository.deleteAttachList(postBoardDto);
    }
}
