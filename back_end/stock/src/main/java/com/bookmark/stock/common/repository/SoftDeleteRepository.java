package com.bookmark.stock.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteRepository<T,ID> extends JpaRepository<T,ID> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.delete = true")
    List<T> findDelete();

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 AND e.delete = false")
    Optional<T> findActiveById(ID id);
}
