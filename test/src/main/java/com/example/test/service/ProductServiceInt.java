package com.example.test.service;

import java.util.List;

import com.example.test.dto.ProductDTO;

import org.apache.ibatis.annotations.Mapper;


public interface ProductServiceInt {
	public  List<ProductDTO>  getSelectMember(); 
	public void insertMember(ProductDTO vo) ;
}
