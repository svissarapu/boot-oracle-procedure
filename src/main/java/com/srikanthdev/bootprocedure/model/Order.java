package com.srikanthdev.bootprocedure.model;

public class Order {
	private String orderId;
	private String customerName;
	private String customerAddress;
	private String productName;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerName=" + customerName + ", customerAddress=" + customerAddress
				+ ", productName=" + productName + "]";
	}

}
