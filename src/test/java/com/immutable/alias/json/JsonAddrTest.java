package com.immutable.alias.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonAddrTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void testToJavaObj() {
		Address address = new Address();
	    address.setNo("104");
	    address.setProvience("陕西");
	    address.setRoad("高新路");
	    address.setStreate("");
	    JSONArray json = JSONArray.fromObject(address);
	    logger.info(json.toString());
	}
	
	
	@Test
	public void testListToJson() {
		 Address address = new Address();
		  address.setNo("104");
		  address.setProvience("陕西");
		  address.setRoad("高新路");
		  address.setStreate("");
		  Address address2 = new Address();
		  address2.setNo("105");
		  address2.setProvience("陕西");
		  address2.setRoad("未央路");
		  address2.setStreate("张办");
		  List list = new ArrayList();
		  list.add(address);
		  list.add(address2);
		  JSONArray json = JSONArray.fromObject(list);
		  logger.debug("[hello]"+json.toString());
	}
	
	@Test
	public void testJsonArrayToList() {
		JSONObject jsonObject = JSONObject.fromObject("{\"no\":\"104\",\"provience\":\"陕西\",\"road\":\"高新路\",\"streate\":\"\"}");
		  JSONArray jsonArray = new JSONArray();
		  jsonArray.add("{\"no\":\"104\",\"provience\":\"陕西\",\"road\":\"高新路\",\"streate\":\"\"}");
		  jsonArray.add("{\"no\":\"104\",\"provience\":\"陕西\",\"road\":\"高新路\",\"streate\":\"123\"}");
		  Object object = JSONArray.toList(jsonArray,Address.class);
	}
}
