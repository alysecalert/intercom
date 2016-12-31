package org.intercom.test;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController2 {

	@RequestMapping(value = "getTest", method = RequestMethod.GET)
	public @ResponseBody String getTest() throws JSONException
	{
		return new JSONObject().put("msg", "Sucess").toString();
	}
	@RequestMapping(value = "getTest2", method = RequestMethod.GET)
	public @ResponseBody String getTest2() throws JSONException
	{
		TestDao testDao = new TestDao();
		return testDao.readTodoItems();
	}
	@RequestMapping(value = "getTest3", method = RequestMethod.GET)
	public @ResponseBody String getTest3() throws JSONException
	{
		TestDao testDao = new TestDao();
		return testDao.testStoredProcedure();
	}
}
