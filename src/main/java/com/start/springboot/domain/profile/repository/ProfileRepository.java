package com.start.springboot.domain.profile.repository;

import com.start.springboot.domain.profile.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long>, ProfileRepositoryCustom {
}
