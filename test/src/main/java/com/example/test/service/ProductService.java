package com.example.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dao.ProductDAO;
import com.example.test.dto.ProductDTO;



@Service
public class ProductService implements ProductServiceInt{
	 @Autowired
	 ProductDAO  board;
	 
	 public  List<ProductDTO>  getSelectMember() {
	  return board.selectMember();	  
	 }

	public void insertMember(ProductDTO vo) {
	 board.InsertMember(vo);	 
	}

}
