package com.srikanthdev.bootprocedure.dao;

import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.srikanthdev.bootprocedure.model.Order;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.Datum;
import oracle.sql.STRUCT;

@Repository
public class BootProcedureDaoImpl implements BootProcedureDao {

	private static final Log LOG = LogFactory.getLog(BootProcedureDaoImpl.class);
	@Autowired
	DataSource dataSource;

	@Value("${oracle.package}")
	private String ebsPackage;

	@Value("${oracle.procedure}")
	private String ebsProcedure;

	@Value("${oracle.username}")
	private String username;

	private static final String EBS_QUERY_OUT_OBJECT = "APPS.ORDER_TAB";

	@Override
	public List<Order> readObjectFromProcedure(String requestId) {
		OracleCallableStatement cs = null;
		Connection connection = null;
		List<Order> orderList = null;
		String ebsQuery = "{call " + username + "." + ebsPackage + "." + ebsProcedure + " (?,?,?) }";
		try {
			connection = dataSource.getConnection();
			cs = (OracleCallableStatement) connection.prepareCall(ebsQuery);
			cs.setInt(1, Integer.valueOf(requestId));
			cs.registerOutParameter(2, OracleTypes.ARRAY, EBS_QUERY_OUT_OBJECT);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.execute();

			String status = cs.getString(3);
			LOG.info("EBS Package Response status: " + status);
			orderList = new ArrayList<Order>();
			ARRAY outArray = cs.getARRAY(2);
			Order order = null;
			if (outArray != null) {
				Datum[] rows = outArray.getOracleArray();
				if (rows != null) {
					LOG.info("Number of EBS records selected : " + rows.length);
					for (int i = 0; i < rows.length; i++) {
						order = new Order();
						Object[] row = ((STRUCT) rows[i]).getAttributes();
						order.setOrderId(row[0].toString());
						order.setProductName(row[1].toString());
						order.setCustomerName(row[2].toString());
						order.setCustomerAddress(row[3].toString());
						orderList.add(order);
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Error", e);
		}
		return orderList;
	}

}