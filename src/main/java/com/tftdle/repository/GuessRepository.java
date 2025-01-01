package com.tftdle.repository;

import com.tftdle.model.GuessModel;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuessRepository extends JpaRepository<GuessModel, Long> {
    @QueryHints(value = {
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    @Query("SELECT g FROM GuessModel g WHERE g.sessionId = :sessionId ORDER BY g.createdAt DESC")
    List<GuessModel> findGuessesBySessionId(@Param("sessionId") String sessionId);
}
