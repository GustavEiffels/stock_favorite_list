package com.bookmark.stock.infrastructure.stock.persistence;

import com.bookmark.stock.domain.stock.StockDomainDto;
import com.bookmark.stock.domain.stock.StockRepository;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaJpaRepository jpaRepository;

    @Override
    public StockEntity save(StockEntity stockEntity) {
        return jpaRepository.save(stockEntity);
    }

    @Override
    public Optional<StockEntity> findStock(StockDomainDto.StockSearchDto stockSearchDto) {
        return Optional.empty();
    }


}
