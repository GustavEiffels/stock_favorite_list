package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;

import java.util.Optional;

public interface BmWStockJpaRepositoryCustom {


    Optional<BmWStockEntity> findByBMIdWStockId(Long bookMarkId, Long stockId);
}
