import { useEffect, useRef, useState } from "react"
import './DraggableModal.css'

export default function DraggableModal({
    isOpen,
    onClose,
    title = "Modal",
    children,
    initialOffsetY = -150
}) {
    const [position, setPosition] = useState({ x: 0, y: 0 })
    const [isDragging, setIsDragging] = useState(false)
    const [dragStart, setDragStart] = useState({ x: 0, y: 0 })
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

    const handleMouseUp = () => {
        setIsDragging(false)
    }

    const handleOverlayClick = (e) => {
        if (e.target.classList.contains('modal-overlay')) {
            onClose()
        }
    }

    useEffect(() => {
        if (isOpen && modalRef.current) {
            const modalRect = modalRef.current.getBoundingClientRect()
            setPosition({
                x: (window.innerWidth - modalRect.width) / 2,
                y: (window.innerHeight - modalRect.height) / 2 + initialOffsetY
            })
        }
    }, [isOpen, initialOffsetY])

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

    if (!isOpen) return null

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
                    <h2>{title}</h2>
                    <button onClick={onClose}>×</button>
                </div>
                <div className="modal-body">
                    {children}
                </div>
            </div>
        </div>
    )
}