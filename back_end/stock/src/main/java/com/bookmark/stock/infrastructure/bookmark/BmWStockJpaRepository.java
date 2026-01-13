package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.common.repository.SoftDeleteRepository;
import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;

public interface BmWStockJpaRepository extends SoftDeleteRepository<BmWStockEntity,Long>, BmWStockJpaRepositoryCustom {
}
