package com.example.test.dto;


public class ProductDTO {
	int id;
	String productName;
	int amount;
	int price;
	String remark;
	int no;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "WorkDTO [id=" + id + ", productName=" + productName + ", amount=" + amount + ", price=" + price
				+ ", remark=" + remark + ", no=" + no + "]";
	}
	public ProductDTO() {
	}
}
