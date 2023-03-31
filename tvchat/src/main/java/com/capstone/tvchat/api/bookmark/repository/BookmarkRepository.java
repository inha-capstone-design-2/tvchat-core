package com.capstone.tvchat.api.bookmark.repository;

import com.capstone.tvchat.api.bookmark.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
