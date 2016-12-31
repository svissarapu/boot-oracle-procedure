package com.srikanthdev.bootprocedure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srikanthdev.bootprocedure.dao.BootProcedureDao;
import com.srikanthdev.bootprocedure.model.Order;

@Service
public class BootProcedureService {

	@Autowired
	BootProcedureDao procedureDao;

	public List<Order> findOrders(String requestId) {
		return procedureDao.readObjectFromProcedure(requestId);
	}

}
