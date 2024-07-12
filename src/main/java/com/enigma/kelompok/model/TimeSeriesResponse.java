package com.enigma.kelompok.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class TimeSeriesResponse {

    @JsonProperty("Meta Data")
    private MetaData metaData;

    @JsonProperty("Monthly Time Series")
    private Map<String, MonthlyPrice> monthlyTimeSeries;

    @Data
    public static class MetaData {
        @JsonProperty("2. Symbol")
        private String symbol;
    }

    @Data
    public static class MonthlyPrice {
        @JsonProperty("1. open")
        private String open;
    }

    public List<Stock> toStockList() {
        List<Stock> stocks = new ArrayList<>();
        String name = this.metaData.getSymbol();
        int id = 1;

        for (Map.Entry<String, MonthlyPrice> entry : this.monthlyTimeSeries.entrySet()) {
            String code = entry.getKey();
            Integer price = (int) Double.parseDouble(entry.getValue().getOpen());
            stocks.add(new Stock(id, name, code, price));
            id++;
        }

        return stocks;
    }
}
