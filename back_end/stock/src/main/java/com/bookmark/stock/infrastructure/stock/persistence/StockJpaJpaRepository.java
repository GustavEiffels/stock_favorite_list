package com.bookmark.stock.infrastructure.stock.persistence;


import com.bookmark.stock.common.repository.SoftDeleteRepository;
import com.bookmark.stock.domain.stock.entity.StockEntity;

public interface StockJpaJpaRepository extends SoftDeleteRepository<StockEntity,Long>, StockJpaRepositoryCustom {
}
