package com.start.springboot.domain.attach.service;

import com.start.springboot.domain.attach.repository.AttachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttachService {
    private final AttachRepository attachRepository;
}
