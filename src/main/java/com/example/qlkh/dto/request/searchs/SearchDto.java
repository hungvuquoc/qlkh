package com.example.qlkh.dto.request.searchs;

import com.example.qlkh.constant.DateConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto implements Serializable {
    private int pageIndex = 0;
    private int pageSize = 10;
    private String keyword;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDate startDate;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE)
    private LocalDate endDate;

    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime createDate;
    @DateTimeFormat(pattern = DateConstants.US_NORMAL_DATE_TIME)
    private LocalDateTime modifyDate;

    private Boolean deleted;

    public void setPageIndex(int pageIndex) {
        if (pageIndex > 1) {
            this.pageIndex = pageIndex - 1;
        }
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    @JsonIgnore
    public Pageable getPageable(){
        return PageRequest.of(pageIndex, pageSize);
    }

    @JsonIgnore
    public int getLimit() {
        return this.pageSize;
    }

    @JsonIgnore
    public int getOffset() {
        return this.pageIndex * this.pageSize;
    }
}
