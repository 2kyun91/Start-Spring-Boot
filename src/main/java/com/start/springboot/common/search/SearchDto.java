package com.start.springboot.common.search;

import com.querydsl.core.BooleanBuilder;
import com.start.springboot.domain.post.entity.QPost;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@ToString
public class SearchDto {
    private String searchType;

    private String searchWord;

    public BooleanBuilder makeBooleanBuilder(QPost post) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (StringUtils.isEmpty(searchType) || StringUtils.isEmpty(searchWord)) {
            return booleanBuilder;
        }

        switch (searchType) {
            case "all" :
                booleanBuilder.and(post.postTitle.contains(searchWord))
                        .or(post.postContent.contains(searchWord));
                break;
            case "title" :
                booleanBuilder.and(post.postTitle.contains(searchWord));
                break;
            case "content" :
                booleanBuilder.and(post.postContent.contains(searchWord));
                break;
            default:
                break;
        }
        return  booleanBuilder;
    }
}
