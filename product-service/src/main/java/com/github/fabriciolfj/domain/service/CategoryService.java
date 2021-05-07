package com.github.fabriciolfj.domain.service;

import com.github.fabriciolfj.api.category.mapper.CategoryMapper;
import com.github.fabriciolfj.domain.entity.Category;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper mapper;

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(final Category category) {
        Category.persist(category);
    }

    @Transactional(Transactional.TxType.NEVER)
    public Category findByDescription(final String description) {
        return Optional.of(Category.find("description", description))
                .map(c -> (Category) c)
                .orElseThrow(() -> new RuntimeException("Category not found " + description));
    }

    @Transactional(Transactional.TxType.NEVER)
    public List<Category> findAll() {
        final List<Category> categories = Category.listAll();
        return categories;
    }
}
