package com.srikanthdev.bootprocedure.dao;

import java.util.List;

import com.srikanthdev.bootprocedure.model.Order;

public interface BootProcedureDao {
	List<Order> readObjectFromProcedure(String requestId);
}
