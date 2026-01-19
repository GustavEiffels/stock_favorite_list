import { useEffect, useState, useRef } from "react"
import { IoSettingsSharp, IoAddSharp } from 'react-icons/io5';
import './Favorite.css'
import StockSearchModal from "../../components/StockSearchModal";


// Context Menu
function ContextMenu({ position, onClose, onEdit, onDelete, onCopyLink }) {
    const menuRef = useRef(null)

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (menuRef.current && !menuRef.current.contains(event.target)) {
                onClose()
            }
        };

        document.addEventListener('mousedown', handleClickOutside);
        return () => document.removeEventListener('mousedown', handleClickOutside);
    }, [onClose])

    return (
        <div
            ref={menuRef}
            className="context-menu"
            style={{
                top: position.y,
                left: position.x,
                position: 'absolute',
                zIndex: 1000
            }}
        >
            <div className="context-menu-item" onClick={onEdit}>
                <span>✏️ 이름 바꾸기</span>
                <span className="shortcut">⌘⇧R</span>
            </div>
            <div className="context-menu-item" onClick={onCopyLink}>
                <span>🔗 링크 복사</span>
            </div>
            <div className="context-menu-item" onClick={onDelete}>
                <span>🗑️ 삭제</span>
            </div>
        </div>
    );
}

// Context Menu
function FavoriteListItem({ title, quantity, createDate, stockList, onClick }) {
    return (
        <div className="favorite-list-item" onClick={() => onClick({ title, stockList })}>
            <div className="basic-info">
                <span className="title">{title}</span>
                <span className="quantity">{quantity}</span>
            </div>
            <div className="create-date">Create : {createDate}</div>
        </div>
    )
}

function StockListItem({ ticker, currentPrice, percent }) {
    const isSuccess = ticker != null && currentPrice != null && percent != null;

    return (
        isSuccess ?
            <div className="stock-list-item">
                <div className="title">
                    {ticker}
                </div>
                <div className="price-frame">
                    <div className="current">
                        {currentPrice}
                    </div>
                    <div className="compare-before">
                        ( {percent}% )
                    </div>
                </div>
            </div>
            :
            null
    )
}

function FavoriteContents({ title, stockList, modalHandler }) {
    return (
        <>
            <div className="header">
                <div className="search">
                    <h1>{title}</h1>
                    <input type="text" placeholder="검색..." />
                </div>
                <button onClick={() => modalHandler(true)}>
                    + ADD
                </button>
            </div>
            <div className="stock-list">
                {stockList.length > 0 ? (
                    stockList
                ) : (
                    <div className="empty-state">
                        <p>등록된 주식이 없습니다.</p>
                        <p className="sub-text">+ ADD 버튼을 눌러 주식을 추가해보세요</p>
                    </div>
                )}
            </div>
        </>
    )
}


export default function Favorite() {
    // useState
    const [selectedInfo, setSelectedInfo] = useState({
        id: '',
        name: ''
    })

    // Function Options 
    const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 })
    const [showMenu, setShowMenu] = useState(false)
    const [showStockModal, setShowStockModal] = useState(false)

    // BookMark List
    const [bookMarkList, setBookMarkList] = useState([])
    const [stockList, setStockList] = useState([])

    useEffect(() => {
        const tempBookMarkList = [
            {
                title: "미국 반도체 주식",
                createDate: "2025-11-25",
                quantity: 15,
                stockList: [
                    { ticker: "NVDA", name: "엔비디아", currentPrice: "145.20", percent: "+5.2" },
                    { ticker: "AMD", name: "AMD", currentPrice: "180.30", percent: "-2.1" }
                ]
            },
            {
                title: "금 관련",
                createDate: "2025-11-25",
                quantity: 10,
                stockList: [
                    { ticker: "GLD", name: "금 ETF", currentPrice: "195.50", percent: "+1.8" },
                    { ticker: "GOLD", name: "금광", currentPrice: "28.90", percent: "+3.5" }
                ]
            },
            {
                title: "반도체 관련",
                createDate: "2025-11-25",
                quantity: 5,
                stockList: [
                    { ticker: "TSM", name: "TSMC", currentPrice: "105.60", percent: "+4.2" },
                    { ticker: "INTC", name: "인텔", currentPrice: "48.70", percent: "-1.3" }
                ]
            }
        ]

        const items = tempBookMarkList.map((item, index) => {
            return <FavoriteListItem
                key={index}
                title={item.title}
                createDate={item.createDate}
                quantity={item.quantity}
                stockList={item.stockList}
                onClick={favoriteItemClickHandler}
            />
        });

        setBookMarkList(items);
    }, [])


    const handleMenuClick = (e) => {
        const rect = e.currentTarget.getBoundingClientRect()
        setMousePosition({
            x: rect.left,
            y: rect.bottom + 5
        })
        setShowMenu(true)
    }

    const favoriteItemClickHandler = (selectedData) => {
        setSelectedInfo({
            id: selectedData.index,
            name: selectedData.title
        })
        const stockComponents = selectedData.stockList.map((stock, index) => (
            <StockListItem
                key={index}
                ticker={stock.ticker}
                currentPrice={stock.currentPrice || "0.00"}
                percent={stock.percent || "0.00"}
            />
        ))
        setStockList(stockComponents)
    }

    const stockAddHandler = (stockData) => {
    }

    const testList = [<StockListItem key="1" ticker="NVDA" currentPrice="100.00" percent="+10.02" />]

    return (
        <div className="favorite-container">
            <div className="sidebar">
                <div className="sidebar-header">
                    <div className="name">Stock Bookmark</div>
                    <div className="tool-set">
                        <button>
                            <IoAddSharp />
                        </button>
                        <button className="icon-btn" onClick={handleMenuClick}>
                            <IoSettingsSharp />
                        </button>
                    </div>
                </div>
                <div className="favorite-list">
                    {bookMarkList}
                </div>
            </div>
            <div className="contents">
                {
                    selectedInfo.id == ''
                        ? (
                            <div className="empty-state-container">
                                <div className="empty-message">
                                    <h2>📋 북마크를 선택해주세요</h2>
                                    <p>왼쪽 목록에서 관심있는 북마크를 클릭하면</p>
                                    <p>등록된 주식 목록을 확인할 수 있습니다</p>
                                </div>
                            </div>
                        )
                        :
                        <FavoriteContents title={selectedInfo.name} stockList={stockList} modalHandler={setShowStockModal} />
                }

            </div>

            {showMenu && (
                <ContextMenu
                    position={mousePosition}
                    onClose={() => setShowMenu(false)}
                    onEdit={() => {
                        console.log('이름 바꾸기')
                        setShowMenu(false)
                    }}
                    onDelete={() => {
                        console.log('삭제')
                        setShowMenu(false)
                    }}
                    onCopyLink={() => {
                        console.log('링크 복사')
                        setShowMenu(false)
                    }}
                />
            )}

            <StockSearchModal
                isOpen={showStockModal}
                onClose={() => setShowStockModal(false)}
                selectStockHandler={stockAddHandler}
            />
        </div>
    )
}