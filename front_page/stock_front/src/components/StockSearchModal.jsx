import { useEffect, useRef, useState } from "react"
import './StockSearchModal.css'
import { IoSearchSharp } from 'react-icons/io5';
import { stockApi } from "../api/stockApi";

export default function StockSearchModal({ isOpen, onClose, selectStockHandler = () => { } }) {
    const [position, setPosition] = useState({ x: 0, y: 0 })
    const [isDragging, setIsDragging] = useState(false)
    const [dragStart, setDragStart] = useState({ x: 0, y: 0 })
    const [searchQuery, setSearchQuery] = useState('')
    const [isValidInput, setIsValidInput] = useState(true)
    const [searchResults, setSearchResults] = useState([])
    const [apiCacheResults, setApiCacheResults] = useState([])
    const [isSearching, setIsSearching] = useState(false)
    const [serverSearchCompleted, setServerSearchCompleted] = useState(false)
    const modalRef = useRef(null)
    const searchTimerRef = useRef(null)
    const lastSearchQueryRef = useRef('')

    const handleMouseDown = (e) => {
        if (e.target.closest('.modal-header')) {
            setIsDragging(true)
            setDragStart({
                x: e.clientX - position.x,
                y: e.clientY - position.y
            })
        }
    }

    const handleMouseMove = (e) => {
        if (isDragging) {
            setPosition({
                x: e.clientX - dragStart.x,
                y: e.clientY - dragStart.y
            })
        }
    }

    const handleOverlayClick = (e) => {
        if (e.target.classList.contains('modal-overlay')) {
            onClose()
        }
    }

    const handleMouseUp = () => {
        setIsDragging(false)
    }

    const handleStockSelect = (stock) => {
        selectStockHandler(stock)
        onClose()
    }

    const searchFromServer = async (ticker) => {
        try {
            const response = await stockApi.getStockByTicker(ticker)
            console.log('response : ', response)

            // 서버에서 결과를 찾았으면 그것만 표시, 못 찾았으면 기존 캐시 결과 유지
            if (response.data && response.data.length > 0) {
                setSearchResults(response.data)
            }
            // 서버에서도 못 찾았으면 캐시의 부분 일치 결과 유지 (searchResults 변경 안 함)

            setServerSearchCompleted(true)
        } catch (error) {
            console.log('Server search error:', error)
            // 에러 발생 시에도 캐시 결과 유지
            setServerSearchCompleted(true)
        } finally {
            setIsSearching(false)
        }
    }

    const tickerSearch = (query) => {
        setSearchQuery(query)
        setServerSearchCompleted(false)

        // 기존 타이머 클리어
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

        const isValid = /^[a-zA-Z0-9]+$/.test(query);
        setIsValidInput(isValid);

        if (!isValid) {
            setSearchResults([])
            setIsSearching(false)
            lastSearchQueryRef.current = ''
            return
        }

        // 1. 캐시에서 부분 일치 검색 (즉시 표시)
        const filteredResults = apiCacheResults.filter(stock =>
            stock.ticker?.toLowerCase().includes(query.toLowerCase()) ||
            stock.stockName?.toLowerCase().includes(query.toLowerCase())
        );

        setSearchResults(filteredResults)

        // 2. 캐시에서 정확히 일치하는 ticker가 있는지 확인
        const exactMatch = apiCacheResults.find(stock =>
            stock.ticker?.toLowerCase() === query.toLowerCase()
        );

        // 정확한 일치가 있으면 서버 검색 안 함
        if (exactMatch) {
            setIsSearching(false)
            lastSearchQueryRef.current = query
            return
        }

        // 3. 검색어 변동이 없으면 서버 검색 (debounce)
        searchTimerRef.current = setTimeout(() => {
            // 타이머 실행 시점에 검색어가 변경되지 않았는지 확인
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
        initUseState()

        if (isOpen && modalRef.current) {
            const modalRect = modalRef.current.getBoundingClientRect()
            setPosition({
                x: (window.innerWidth - modalRect.width) / 2,
                y: (window.innerHeight - modalRect.height) / 2 - 150
            })
        }

        return () => {
            if (searchTimerRef.current) {
                clearTimeout(searchTimerRef.current)
            }
        }
    }, [isOpen])

    useEffect(() => {
        if (isDragging) {
            document.addEventListener('mousemove', handleMouseMove)
            document.addEventListener('mouseup', handleMouseUp)
            return () => {
                document.removeEventListener('mousemove', handleMouseMove)
                document.removeEventListener('mouseup', handleMouseUp)
            }
        }
    }, [isDragging, dragStart, position])

    useEffect(() => {
        const fetchStockInfos = async () => {
            try {
                const tickerDataFromServer = await stockApi.getStockList();
                setApiCacheResults(tickerDataFromServer.data)
            }
            catch (error) {
                console.log('error : ', error)
            }
        }
        if (isOpen) {
            fetchStockInfos();
        }
    }, [isOpen]);

    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={handleOverlayClick}>
            <div
                ref={modalRef}
                className="modal-content"
                style={{
                    position: 'fixed',
                    left: `${position.x}px`,
                    top: `${position.y}px`,
                    cursor: isDragging ? 'grabbing' : 'default'
                }}
                onMouseDown={handleMouseDown}
            >
                <div className="modal-header" style={{ cursor: 'grab' }}>
                    <h2>Add Stock</h2>
                    <button onClick={onClose}>×</button>
                </div>
                <div className="modal-body">
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
                                            onClick={() => handleStockSelect(stock)}
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
                </div>
            </div>
        </div>
    )
}