import { useState } from "react"
import { IoSettingsSharp, IoAddSharp } from 'react-icons/io5';
import './Favorite.css'


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



function Favorite() {
    // useState
    const [selectedInfo, setSelectedInfo] = useState({
        id: '',
        name: ''
    })


    // click event
    const favoriteItemClickHandler = (selectedData) => {
        setSelectedInfo({
            id: '',
            name: selectedData.title
        })
    }

    const testList = [<StockListItem ticker="NVDA" currentPrice="100.00" percent="+10.02" />]

    return (
        <div className="favorite-container">
            <div className="sidebar">
                <div className="sidebar-header">
                    <div className="name">Stock Bookmark</div>
                    <div className="tool-set">
                        <button>
                            <IoAddSharp />
                        </button>
                        <button className="icon-btn">
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
                    <button>
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
        </div>
    )
}



export default Favorite