package com.bookmark.stock.domain.bookmark.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BookMarkAndStock")
public class BmWStockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long stockId;
    private Long bookMarkId;
}
