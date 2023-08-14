package com.start.springboot.domain.post.repository;

import com.start.springboot.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface PostRepository extends CrudRepository<Post, Long>, PostRepositoryCustom {
//    public List<Post> findByPostTitleContaining(String postTitle);
//
//    public List<Post> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable);
//
//    public Page<Post> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable);
//
//    /* JPQL */
//    @Query("SELECT p FROM Post p WHERE p.postTitle like %:pTitle% AND p.postId < :pId ORDER BY p.postId DESC")
//    public Page<Post> findByPostTitleContainingAndPostIdLessThan(@Param("pTitle") String postTitle, @Param("pId") Long postIdLess, Pageable pageable);
//
//    /* JPQL */
//    @Query("SELECT p.postTitle, p.postWriter, p.postContent, p.postCreateDate FROM Post p WHERE p.postTitle = :pTitle AND p.postId > :pId")
//    public List<Object[]> findByPostTitleAndPostIdGreaterThan(@Param("pTitle") String postTitle, @Param("pId") Long postIdGreater);
}
