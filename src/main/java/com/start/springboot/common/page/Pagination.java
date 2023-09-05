package com.start.springboot.common.page;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Pagination<T> {
    private Page<T> result;

    private Pageable prevPage;
    private Pageable nextPage;

    private int currentPageNumber;
    private int totalPageNumber;

    private Pageable currentPage;

    @ToString.Exclude
    private List<Pageable> pageList;

    public Pagination(Page<T> result) {
        this.result = result;
        this.currentPage = result.getPageable();
        this.currentPageNumber = currentPage.getPageNumber() + 1;
        this.totalPageNumber = result.getTotalPages();
        this.pageList = new ArrayList<>();
        calculatePages();
    }

    private void calculatePages() {
        int tempEndNumber = (int) (Math.ceil(this.currentPageNumber / 10.0) * 10);
        int startNumber = tempEndNumber - 9;

        Pageable startPage = this.currentPage;

        for (int i = startNumber; i < currentPageNumber; i++) {
            startPage = startPage.previousOrFirst();
        }

        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        if (this.totalPageNumber < tempEndNumber) {
            tempEndNumber = this.totalPageNumber;
            this.nextPage = null;
        }

        for (int i = startNumber; i <= tempEndNumber; i++) {
            pageList.add(startPage);
            startPage = startPage.next();
        }

        this.nextPage = startPage.getPageNumber() + 1 < totalPageNumber ? startPage : null;
    }
}
