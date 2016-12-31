# boot-oracle-procedure

This Project demonstrates invoking oracle stored procedure from Java, which returns multiple rows of table/list of records. It shows how the results can be mapped to a Java Object.

## DB Procedure/Package/Types Creation Scripts

- Object Type Creation
```
CREATE OR REPLACE TYPE APPS.order_info_obj AS OBJECT
   (
      ORDER_ID            NUMBER,
      PRODUCT_NAME        VARCHAR2(200 BYTE),
      CUSTOMER_NAME       VARCHAR2(200 BYTE),
      CUSTOMER_ADDRESS    VARCHAR2(200 BYTE),
      CUSTOMER_ID         NUMBER
    }
/

```

- order_info_tab Type Creation
```
CREATE OR REPLACE TYPE APPS.order_info_tab AS TABLE OF order_info_obj
/
```
- Package/Procedure Creation
```
create or replace PACKAGE BODY  ORDERS_PKG
IS

PROCEDURE get_orders_info (p_id    IN     NUMBER,
                            x_order_data      OUT order_info_tab,
                            x_status         OUT VARCHAR2)
   IS
      l_order_data   order_info_tab;
   BEGIN
      SELECT xxvm_kp_meta_obj (ORDER_ID,
                               PRODUCT_NAME,
                               CUSTOMER_NAME,
                               CUSTOMER_ADDRESS,
                               CUSTOMER_ID
                               )
        BULK COLLECT INTO l_order_data
        FROM APPS.ORDERS
       WHERE customer_id = p_id AND PROCESSING_STATUS = 'IN_PROCESS';

      IF (l_order_data.COUNT > 0)
      THEN
         x_order_data := l_order_data;
         x_status := 'Success';
      ELSE
         x_status := 'No Records for Batch :' || p_id;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         x_status := 'Error Occured :' || SQLERRM;
   END;
END ORDERS_PKG;
```

## Update following properties in application.properties file
```
oracle.username=APPS
oracle.password=<PASSWORD>
oracle.url=jdbc:oracle:thin:@<servername>:1521:<SID/SERVICENAME>
```

## Rest Endpoint
```
http://localhost:8080/oracleprocedure/findorders?requestId=103
```
