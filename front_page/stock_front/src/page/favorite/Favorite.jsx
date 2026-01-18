import { useEffect, useState, useRef } from "react"
import { IoSettingsSharp, IoAddSharp } from 'react-icons/io5';
import './Favorite.css'
import StockSearchModal from "../../components/StockSearchModal";


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

function FavoriteListItem({ title, quantity, createDate, onClick }) {
    return (
        <div className="favorite-list-item" onClick={() => onClick({ title })}>
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

export default function Favorite() {
    // useState
    const [selectedInfo, setSelectedInfo] = useState({
        id: '',
        name: ''
    })

    const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 })
    const [showMenu, setShowMenu] = useState(false)
    const [showStockModal, setShowStockModal] = useState(false)

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
            id: '',
            name: selectedData.title
        })
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
                    <FavoriteListItem title="미국 반도체 주식들 1" createDate="2025-11-25" quantity={15} onClick={favoriteItemClickHandler} />
                    <FavoriteListItem title="미국 반도체 주식들 2" createDate="2025-11-25" quantity={15} onClick={favoriteItemClickHandler} />
                    <FavoriteListItem title="미국 반도체 주식들 3" createDate="2025-11-25" quantity={15} onClick={favoriteItemClickHandler} />
                </div>
            </div>
            <div className="contents">
                <div className="header">
                    <div className="search">
                        <h1>{selectedInfo.name}</h1>
                        <input type="text" placeholder="검색..." />
                    </div>
                    <button onClick={() => setShowStockModal(true)}>
                        + ADD
                    </button>
                </div>
                <div className="stock-list">
                    {testList.length > 0 ? (
                        testList
                    ) : (
                        <div className="empty-state">
                            <p>등록된 주식이 없습니다.</p>
                            <p className="sub-text">+ ADD 버튼을 눌러 주식을 추가해보세요</p>
                        </div>
                    )}
                </div>
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

            <StockSearchModal isOpen={showStockModal} onClose={() => setShowStockModal(false)} />
        </div>
    )
}