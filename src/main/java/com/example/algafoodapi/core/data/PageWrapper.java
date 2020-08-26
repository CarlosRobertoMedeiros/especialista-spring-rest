package com.example.algafoodapi.core.data;
/*
 *  @criado em: 26/08/2020 - {17:04}
 *  @projeto  : algafood-api
 *  @autor    : roberto
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageWrapper<T> extends PageImpl<T> {

    private Pageable pageable;

    public PageWrapper(Page<T> page, Pageable pageable){
        super(page.getContent(),pageable,page.getTotalElements());
        this.pageable = pageable;
    }

    @Override
    public Pageable getPageable() {
        return this.pageable;
    }
}
