package com.baoluangiang.project_management.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Services<T> {
    List<T> getAll();
    T getById(long id);
    String create(T data);
    String update(T data);
    String delete(long id);
}
