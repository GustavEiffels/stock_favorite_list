import DraggableModal from '../components/DraggableModal'
import StockSearchContent from '../contents/StockSearchContent'

export default function StockSearchModal({ isOpen, onClose, selectStockHandler = () => { } }) {
    const handleStockSelect = (stock) => {
        selectStockHandler(stock)
        onClose()
    }

    return (
        <DraggableModal
            isOpen={isOpen}
            onClose={onClose}
            title="Add Stock"
            initialOffsetY={-150}
        >
            <StockSearchContent onStockSelect={handleStockSelect} />
        </DraggableModal>
    )
}