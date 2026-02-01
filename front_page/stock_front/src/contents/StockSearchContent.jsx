import { useEffect, useRef, useState } from "react"
import './StockSearchContent.css'
import { IoSearchSharp } from 'react-icons/io5'
import { stockApi } from "../api/stockApi"

export default function StockSearchContent({ onStockSelect }) {
    const [searchQuery, setSearchQuery] = useState('')
    const [isValidInput, setIsValidInput] = useState(true)
    const [searchResults, setSearchResults] = useState([])
    const [apiCacheResults, setApiCacheResults] = useState([])
    const [isSearching, setIsSearching] = useState(false)
    const [serverSearchCompleted, setServerSearchCompleted] = useState(false)
    const searchTimerRef = useRef(null)
    const lastSearchQueryRef = useRef('')
    const isMountedRef = useRef(false)

    const searchFromServer = async (ticker) => {
        try {
            const response = await stockApi.getStockByTicker(ticker)
            console.log('response : ', response)

            if (response.data && response.data.length > 0) {
                setSearchResults(response.data)
            }

            setServerSearchCompleted(true)
        } catch (error) {
            console.log('Server search error:', error)
            setServerSearchCompleted(true)
        } finally {
            setIsSearching(false)
        }
    }

    const tickerSearch = (query) => {
        setSearchQuery(query)
        setServerSearchCompleted(false)

        if (searchTimerRef.current) {
            clearTimeout(searchTimerRef.current)
        }

        if (query.length === 0) {
            setIsValidInput(true)
            setSearchResults([])
            setIsSearching(false)
            lastSearchQueryRef.current = ''
            return
        }

        const isValid = /^[a-zA-Z0-9]+$/.test(query)
        setIsValidInput(isValid)

        if (!isValid) {
            setSearchResults([])
            setIsSearching(false)
            lastSearchQueryRef.current = ''
            return
        }

        const filteredResults = apiCacheResults.filter(stock =>
            stock.ticker?.toLowerCase().includes(query.toLowerCase())
            // ||stock.stockName?.toLowerCase().includes(query.toLowerCase())
        )

        setSearchResults(filteredResults)

        const exactMatch = apiCacheResults.find(stock =>
            stock.ticker?.toLowerCase() === query.toLowerCase()
        )

        if (exactMatch) {
            setIsSearching(false)
            lastSearchQueryRef.current = query
            return
        }

        searchTimerRef.current = setTimeout(() => {
            if (query === searchQuery) {
                lastSearchQueryRef.current = query
                setIsSearching(true)
                searchFromServer(query)
            }
        }, 500)
    }

    const initUseState = () => {
        setSearchQuery('')
        setIsValidInput(true)
        setSearchResults([])
        setIsSearching(false)
        setServerSearchCompleted(false)
        lastSearchQueryRef.current = ''
        if (searchTimerRef.current) {
            clearTimeout(searchTimerRef.current)
        }
    }

    useEffect(() => {
        const fetchStockInfos = async () => {
            try {
                const tickerDataFromServer = await stockApi.getStockList()
                setApiCacheResults(tickerDataFromServer.data)
            } catch (error) {
                console.log('error : ', error)
            }
        }

        if (!isMountedRef.current) {
            isMountedRef.current = true
            fetchStockInfos()
        }

        return () => {
            if (searchTimerRef.current) {
                clearTimeout(searchTimerRef.current)
            }
        }
    }, [])

    // Reset search when component unmounts
    useEffect(() => {
        return () => {
            initUseState()
        }
    }, [])

    return (
        <>
            <div className="search-layer">
                <IoSearchSharp className="search-icon" />
                <input
                    type="text"
                    placeholder="티커 또는 회사명 검색..."
                    value={searchQuery}
                    onChange={(e) => tickerSearch(e.target.value)}
                />
            </div>
            {!isValidInput && (
                <div className="error-message">영어 또는 숫자만 입력 가능합니다.</div>
            )}
            {searchQuery.length > 0 && isValidInput && (
                <div className="search-results">
                    {searchResults.length > 0 && (
                        <ul className="result-list">
                            {searchResults.slice(0, 5).map((stock) => (
                                <li
                                    key={stock.ticker}
                                    className="result-item"
                                    onClick={() => onStockSelect(stock)}
                                >
                                    <div className="stock-info">
                                        <span className="ticker">{stock.ticker}</span>
                                        <span className="name">{stock.stockName}</span>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    )}
                    {isSearching && (
                        <div className="loading-container">
                            <div className="spinner"></div>
                            <p>서버에서 검색 중...</p>
                        </div>
                    )}
                    {!isSearching && searchResults.length === 0 && (
                        <div className="no-results">검색 결과가 없습니다.</div>
                    )}
                </div>
            )}
        </>
    )
}