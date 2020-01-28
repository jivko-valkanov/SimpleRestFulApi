package org.zhivko.todoListApi.services;

import java.util.List;

public interface BaseService<T> {

    public List<T> findAll();

    public T findById(long id);

    public void deleteById(long id);

}
