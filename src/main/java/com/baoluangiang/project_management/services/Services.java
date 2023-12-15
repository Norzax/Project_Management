package com.baoluangiang.project_management.services;

import java.util.List;

public interface Services<T> {
    List<T> getAll();
    T getById(long id);
    String create(T data);
    String update(T data);
    String delete(long id);
}
