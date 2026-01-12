package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.entity.StockEntity;

import java.util.List;

public interface StockExternalApiClient {
    List<StockEntity> findSymbol(String symbol);
}
