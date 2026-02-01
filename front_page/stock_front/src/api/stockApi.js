// src/api/stockApi.js
import apiClient from './apiClient';

export const stockApi = {
    getStockList: async () => {
        return await apiClient.get('/api/v1/stocks');
    },

    getStockDetail: async (stockId) => {
        return await apiClient.get(`/api/stocks/${stockId}`);
    },

    getStockByTicker: async (ticker) => {
        return await apiClient.get(`/api/v1/stocks/search/${ticker}`);
    }


};