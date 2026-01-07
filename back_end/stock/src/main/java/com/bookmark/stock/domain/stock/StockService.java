package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository repository;

    public StockEntity createStock(StockDomainDto.StockCreateDto createDto){
        return repository.save(createDto.toDomain());
    }
}
