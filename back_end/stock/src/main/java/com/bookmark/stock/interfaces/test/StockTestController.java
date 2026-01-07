package com.bookmark.stock.interfaces.test;

import com.bookmark.stock.infrastructure.stock.external.client.StockApiClient;
import com.bookmark.stock.infrastructure.stock.external.dto.PolygonApiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class StockTestController {
    private final StockApiClient stockApiClient;

    @GetMapping("/polygon/{limit}")
    public List<PolygonApiDto.ExternalStockDto> testPolygonApi(
            @PathVariable int limit
    ){
        return stockApiClient.fetchTopStocksByMarketCap(limit);
    }
}
