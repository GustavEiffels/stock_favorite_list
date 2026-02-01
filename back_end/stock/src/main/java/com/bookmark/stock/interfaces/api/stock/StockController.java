package com.bookmark.stock.interfaces.api.stock;

import com.bookmark.stock.common.ApiResponse;
import com.bookmark.stock.domain.stock.StockDomainDto;
import com.bookmark.stock.domain.stock.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping
    public ApiResponse<List<StockDomainDto.StockSearchDto>> initStockList(){
        return ApiResponse.ok(stockService.findStockInCache());
    }

    @GetMapping("/search/{ticker}")
    public ApiResponse<List<StockDomainDto.StockSearchDto>> searchTicker(@PathVariable String ticker){
        return ApiResponse.ok(stockService.findStockByTicker(new StockApiDto.SearchRequest(ticker,null).toDomain()));
    }
}
