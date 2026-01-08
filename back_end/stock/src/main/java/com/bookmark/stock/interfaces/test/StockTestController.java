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

    @GetMapping("/insert/data/{ticker}")
    public String saveStock(@PathVariable String ticker){
        stockService.findByTickerStock(new StockDomainDto.StockSearchDto(
                null,ticker,null,null
        ));
        return "OK";
    }
}
