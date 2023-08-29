package com.start.springboot.common.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//@Getter
//@Setter
public class PageDto {
    private static final int DEFAULT_SIZE = 5;
    private static final int DEFAULT_MAX_SIZE = 10;

    private int page;
    private int size;

    public PageDto() {
        this.page = 1;
        this.size = DEFAULT_SIZE;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page < 0 ? 1 : page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public Pageable makePageable(Sort.Direction direction, String... props) {
        return PageRequest.of(this.page - 1, this.size, direction, props);
    }
}
