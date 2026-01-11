package com.bookmark.stock.interfaces.test;

import com.bookmark.stock.domain.stock.StockService;
import com.bookmark.stock.domain.stock.dto.StockDomainDto;
import com.bookmark.stock.infrastructure.stock.external.FinnhubClient;
import com.bookmark.stock.domain.stock.dto.StockApiDto;
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
    private final FinnhubClient finnhubClient;
    private final StockService stockService;

    @GetMapping("/finnhub/{ticker}")
    public StockApiDto.PinnhubSymbolResponse testPolygonApi(
            @PathVariable String ticker
    ){
        return finnhubClient.getSearchBySymbol(ticker);
    }

    @GetMapping("/search/data/{ticker}")
    public List<StockDomainDto.StockSearchDto> saveStock(@PathVariable String ticker){
        log.info("ticker : {}",ticker);
        return stockService.findStockByTicker(new StockDomainDto.StockSearchDto(
                null,ticker.toUpperCase(),null,null
        ));
    }
}
