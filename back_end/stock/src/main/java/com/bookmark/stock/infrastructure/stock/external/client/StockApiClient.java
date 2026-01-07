package com.bookmark.stock.infrastructure.stock.external.client;

import com.bookmark.stock.infrastructure.stock.external.dto.PolygonApiDto;

import java.util.List;

public interface StockApiClient {

    List<PolygonApiDto.ExternalStockDto> fetchTopStocksByMarketCap(int limit);
}
