package com.bookmark.stock.domain.stock;

import com.bookmark.stock.domain.stock.dto.StockApiDto;

public interface StockApiClient {
    StockApiDto.PinnhubSymbolResponse findSymbol(String symbol);
}
