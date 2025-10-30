package com.aslan.homework5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByAuthorName(String name, Pageable pageable);
}
