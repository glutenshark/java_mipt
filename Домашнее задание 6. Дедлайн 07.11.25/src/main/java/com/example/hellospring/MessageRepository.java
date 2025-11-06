package com.example.hellospring;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface MessageRepository extends JpaRepository<Message,Long>{
 @Query("SELECT m FROM Message m JOIN FETCH m.author ORDER BY m.createdAt DESC")
 List<Message> findLatestWithAuthors(Pageable pageable);
 List<Message> findTop30ByOrderByCreatedAtDesc();
 @EntityGraph(attributePaths="author")
 Page<Message> findByOrderByCreatedAtDesc(Pageable pageable);
}
