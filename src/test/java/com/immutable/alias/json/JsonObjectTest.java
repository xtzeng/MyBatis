package com.immutable.alias.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class JsonObjectTest {

	@Test
	public void testCreateJSONObject() {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "huangwuyi");
        jsonObject.put("sex", "男");
        jsonObject.put("QQ", "413425430");
        jsonObject.put("Min.score", new Integer(99));
        jsonObject.put("nickname", "梦中心境");
        System.out.println("【jsonObject===】"+jsonObject);
        
        // 判读输出对象的类型
        boolean isArray = jsonObject.isArray();
        boolean isEmpty = jsonObject.isEmpty();
        boolean isNullObject = jsonObject.isNullObject();
        System.out.println("是否为数组:" + isArray + "， 是否为空:" + isEmpty
                + "， isNullObject:" + isNullObject);

        // 添加属性，在jsonObject后面追加元素。
        jsonObject.element("address", "福建省厦门市");
        System.out.println("添加属性后的对象：" + jsonObject);

        // 返回一个JSONArray对象
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "this is a jsonArray value");
        jsonArray.add(1, "another jsonArray value");
        jsonObject.element("jsonArray", jsonArray);
        //在jsonObject后面住家一个jsonArray
        JSONArray array = jsonObject.getJSONArray("jsonArray");
        System.out.println(jsonObject);
        
        
        System.out.println("【返回一个JSONArray对象：】" + array);
        // 添加JSONArray后的值
        // {"username":"huangwuyi","sex":"男","QQ":"413425430","Min.score":99,"nickname":"梦中心境","address":"福建省厦门市","jsonArray":["this is a jsonArray value","another jsonArray value"]}
        System.out.println("结果=" + jsonObject);

        // 根据key返回一个字符串
        String username = jsonObject.getString("username");
        System.out.println("【username==>】" + username);

        // 把字符转换为 JSONObject
        String temp = jsonObject.toString();
        JSONObject object = JSONObject.fromObject(temp);
        // 转换后根据Key返回值
        System.out.println("qq=" + object.get("QQ"));
	}
	
	
	@Test
	public void JSONTest(){
		JSONObject jsonObj0  = new JSONObject();
        JSONObject jsonObj  = new JSONObject();
        JSONObject jsonObj2  = new JSONObject();
        JSONObject jsonObj3  = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        
        //创建jsonObj0
        jsonObj0.put("name0", "zhangsan");
        jsonObj0.put("sex1", "female");
        System.out.println("jsonObj0:"+jsonObj0);
        
        //创建jsonObj1
        jsonObj.put("name", "xuwei");
        jsonObj.put("sex", "male");
        System.out.println("jsonObj:"+jsonObj);
    
        //创建jsonObj2，包含两个条目，条目内容分别为jsonObj0，jsonObj1
        jsonObj2.put("item0", jsonObj0);
        jsonObj2.put("item1", jsonObj);
        System.out.println("jsonObj2:"+jsonObj2);
        
        //创建jsonObj3，只有一个条目，内容为jsonObj2
        jsonObj3.element("j3", jsonObj2);
        System.out.println("jsonObj3:"+jsonObj3);
    
        //往JSONArray中添加JSONObject对象。发现JSONArray跟JSONObject的区别就是JSONArray比JSONObject多中括号[]
        jsonArray.add(jsonObj);
        System.out.println("jsonArray:"+jsonArray);
        
        JSONObject jsonObj4  = new JSONObject();
        jsonObj4.element("weather", jsonArray);
        System.out.println("jsonObj4:"+jsonObj4);
	}
}
