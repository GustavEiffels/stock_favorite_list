package com.bookmark.stock.infrastructure.stock.persistence;


import com.bookmark.stock.common.repository.SoftDeleteRepository;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockJpaJpaRepository extends JpaRepository<StockEntity,Long>, StockJpaRepositoryCustom {
}
