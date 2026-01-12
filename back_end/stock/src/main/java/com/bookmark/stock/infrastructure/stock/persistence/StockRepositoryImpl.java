package com.bookmark.stock.infrastructure.stock.persistence;

import com.bookmark.stock.domain.stock.StockDomainDto;
import com.bookmark.stock.domain.stock.StockRepository;
import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaJpaRepository jpaRepository;

    @Override
    public StockEntity save(StockEntity stockEntity) {
        return jpaRepository.save(stockEntity);
    }

    @Override
    public List<StockEntity> saveAll(List<StockEntity> stockEntityList) {
        return jpaRepository.saveAll(stockEntityList);
    }

    @Override
    public Optional<StockEntity> findStock(StockDomainDto.StockSearchDto stockSearchDto) {
        log.info("stockSearchDto : {}",stockSearchDto);
        return jpaRepository.findStock(stockSearchDto);
    }


}
