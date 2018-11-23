package com.darthjaxx.sweater.repos;

import com.darthjaxx.sweater.domain.Message;
import com.darthjaxx.sweater.domain.User;
import com.darthjaxx.sweater.domain.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MessageRepo extends CrudRepository<Message, Long> {

    @Query("SELECT NEW com.darthjaxx.sweater.domain.dto.MessageDto(" +
            "m, " +
            "COUNT (ml), " +
            "SUM (CASE WHEN ml = :user THEN 1 ELSE 0 END ) > 0" +
            ") " +
            "FROM Message AS m LEFT JOIN m.likes AS ml " +
            "GROUP BY m " +
            "ORDER BY m.id DESC")
    Page<MessageDto> findAll(Pageable pageable, @Param("user") User author);

    @Query("SELECT NEW com.darthjaxx.sweater.domain.dto.MessageDto(" +
            "m, " +
            "COUNT (ml), " +
            "SUM (CASE WHEN ml = :user THEN 1 ELSE 0 END ) > 0" +
            ") " +
            "FROM Message AS m LEFT JOIN m.likes AS ml " +
            "WHERE m.tag = :tag " +
            "GROUP BY m ")
    Page<MessageDto> findByTag(Pageable pageable, @Param("tag") String tag, @Param("user") User author);

    @Query("SELECT NEW com.darthjaxx.sweater.domain.dto.MessageDto(" +
            "m, " +
            "COUNT (ml), " +
            "SUM (CASE WHEN ml = :user THEN 1 ELSE 0 END ) > 0" +
            ") " +
            "FROM Message AS m LEFT JOIN m.likes AS ml " +
            "WHERE m.author = :author " +
            "GROUP BY m ")
    Page<MessageDto> findByUser(Pageable pageable, @Param("user") User user, @Param("author") User author);

}
