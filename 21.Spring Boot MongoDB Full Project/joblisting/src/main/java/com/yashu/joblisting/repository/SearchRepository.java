package com.yashu.joblisting.repository;

import com.yashu.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
