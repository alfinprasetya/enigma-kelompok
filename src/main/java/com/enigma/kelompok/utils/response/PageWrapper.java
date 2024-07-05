package com.enigma.kelompok.utils.response;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageWrapper<T> {
  private List<T> content;
  private Long totalElements;
  private Integer totalPages;
  private Integer page;
  private Integer size;

  public PageWrapper(Page<T> page) {
    this.content = page.getContent();
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.page = page.getNumber();
    this.size = page.getSize();
  }
}
