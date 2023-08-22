package com.start.springboot.domain.attach.service;

import com.start.springboot.domain.attach.dto.AttachDto;
import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.attach.repository.AttachRepository;
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
}
