import { useEffect, useRef, useState } from "react"
import './StockSearchModal.css'
import { IoSearchSharp } from 'react-icons/io5';

export default function StockSearchModal({ isOpen, onClose, selectStockHandler = () => { } }) {
    const [position, setPosition] = useState({ x: 0, y: 0 })
    const [isDragging, setIsDragging] = useState(false)
    const [dragStart, setDragStart] = useState({ x: 0, y: 0 })
    const [searchQuery, setSearchQuery] = useState('')
    const [isValidInput, setIsValidInput] = useState(true)
    const [searchResults, setSearchResults] = useState([
        { ticker: 'SOXL', name: '속슬' }, { ticker: 'SOXS', name: '속스' }, { ticker: 'SOXX', name: '속슬X' }, { ticker: 'SOX1', name: '속스1' },
        { ticker: 'SOX2', name: '속슬' }, { ticker: 'SOX3', name: '속스' }, { ticker: 'SOX4', name: '속슬X' }, { ticker: 'SOX5', name: '속스1' }
    ])
    const modalRef = useRef(null)


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
        console.log('선택된 주식:', stock)
        selectStockHandler(stock)
        onClose()
    }

    const tickerSearch = (query) => {
        setSearchQuery(query)

        if (query.length === 0) {
            setIsValidInput(true)
            return
        }
        const isValid = /^[a-zA-Z0-9]+$/.test(query)
        setIsValidInput(isValid)
    }

    const initUseState = () => {
        setSearchQuery('')
        setIsValidInput(true)
    }

    useEffect(() => {
        if (isOpen && modalRef.current) {
            const modalRect = modalRef.current.getBoundingClientRect()
            setPosition({
                x: (window.innerWidth - modalRect.width) / 2,
                y: (window.innerHeight - modalRect.height) / 2 - 150
            })
        }
        initUseState()

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
                            {searchResults.length > 0 ? (
                                <ul className="result-list">
                                    {searchResults.slice(0, 5).map((stock) => (
                                        <li
                                            key={stock.ticker}
                                            className="result-item"
                                            onClick={() => handleStockSelect(stock)}
                                        >
                                            <div className="stock-info">
                                                <span className="ticker">{stock.ticker}</span>
                                                <span className="name">{stock.name}</span>
                                            </div>
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <div className="no-results">검색 결과가 없습니다.</div>
                            )}
                        </div>
                    )}
                </div>
            </div>
        </div>
    )
}