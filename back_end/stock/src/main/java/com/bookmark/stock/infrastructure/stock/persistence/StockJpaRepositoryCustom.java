package com.bookmark.stock.infrastructure.stock.persistence;

import com.bookmark.stock.domain.stock.StockDomainDto;
import com.bookmark.stock.domain.stock.entity.StockEntity;

import java.util.Optional;

public interface StockJpaRepositoryCustom {

    Optional<StockEntity> findStock(StockDomainDto.StockSearchDto searchDto);
}
