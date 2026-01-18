package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category){
        //category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    /**
     * @param categoryId
     * @return string message
     * It simply search the category in the database if not found then throw exception.
     */

    @Override
    public String deleteCategory(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        categoryRepository.delete(category);
        return "Category with categoryId : "+categoryId +" deleted successfully";
    }


    /**
     * @param category
     * @param categoryId
     * @return savedCategory
     * Very simple, we searched the category by categoryId from the database, if its not found then we just through
     * exception and appropriate Http message.
     */

    @Override
    public Category updateCategory(Category category, Long categoryId) {
         Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not Found"));

        category.setCategoryId(categoryId);
        savedCategory=categoryRepository.save(category);
        return savedCategory;
    }

}
