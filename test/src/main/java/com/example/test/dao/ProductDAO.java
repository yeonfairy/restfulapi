package com.example.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.example.test.dto.ProductDTO;

@Mapper
@Component
public interface ProductDAO {
	  public  List<ProductDTO>  selectMember() throws DataAccessException  ;
	  public void InsertMember(ProductDTO vo) throws DataAccessException;

}
