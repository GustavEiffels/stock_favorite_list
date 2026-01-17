package com.bookmark.stock.infrastructure.bookmark;

import com.bookmark.stock.domain.bookmark.entity.BmWStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BmWStockJpaRepository extends JpaRepository<BmWStockEntity,Long>, BmWStockJpaRepositoryCustom {
}
