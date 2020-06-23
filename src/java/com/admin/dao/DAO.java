/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T>
 */
public interface DAO<T> {
	void create(T o);
	void update(T o);
	ArrayList<T> list();
	List<T> pagedList(int page,int lic);
	void delete(T o);
	T detail(Long id);
        int itemCount();
}
