package com.srikanthdev.bootprocedure.rest.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srikanthdev.bootprocedure.model.Order;
import com.srikanthdev.bootprocedure.service.BootProcedureService;

@RestController
@RequestMapping("/oracleprocedure")
public class BootProcedureController {

	@Autowired
	BootProcedureService procedureService;

	@RequestMapping(value = "/findorders")
	public List<Order> findOrders(@RequestParam String requestId) {
		return procedureService.findOrders(requestId);
	}

}
