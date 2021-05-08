package com.github.fabriciolfj.domain.service;

import com.github.fabriciolfj.api.category.mapper.CategoryMapper;
import com.github.fabriciolfj.domain.entity.Category;
import com.github.fabriciolfj.domain.exceptions.CategoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper mapper;

    @Transactional(Transactional.TxType.REQUIRED)
    public void save(final Category category) {
        Category.persist(category);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Category findByDescription(final String description) {
        return Category.find("description", description)
                .firstResultOptional()
                .map(c -> {
                    log.info("Category find: {}", c.toString());
                    return (Category) c;
                })
                .orElseThrow(() -> new CategoryException("Category not found " + description));
    }

    @Transactional(Transactional.TxType.NEVER)
    public List<Category> findAll() {
        final List<Category> categories = Category.listAll();
        return categories;
    }
}
