package com.jr.starbux.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.jr.starbux.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, I> extends JpaRepository<E, I> {

    // (E) = Entity
    // (I) = ID

    @Query("select e from #{#entityName} e where e.active = true ")
    List<E> findAllActived();

    @Query("select e from #{#entityName} e where e.active = true and id = ?1 ")
    E findByIdActived(I id);

}
