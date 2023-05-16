package com.capstone.tvchat.api.bookmark.repository;

import com.capstone.tvchat.api.bookmark.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByCreatedBy(Long createdBy);
}
