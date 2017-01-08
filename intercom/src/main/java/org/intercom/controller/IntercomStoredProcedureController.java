package org.intercom.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.intercom.services.StoredProcedureServices;
import org.intercom.utils.IntercomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntercomStoredProcedureController {

	@RequestMapping(value = {"/","login"}, method = RequestMethod.GET)
	public String getLoginPage()
	{
		IntercomUtils.printInfo("getting login page");
		return "login";
	}
	
	@RequestMapping(value = "getMasterDelta", method = RequestMethod.GET)
	private @ResponseBody String getTest() throws JSONException
	{
		IntercomUtils.printInfo("getMasterDelta API Called");
		return StoredProcedureServices.getInstances().getMasterDelta();
	}
	
	@RequestMapping(value = {"getUpdatePage"}, method = RequestMethod.GET)
	public String getUpdatePage()
	{
		IntercomUtils.printInfo("getting login page");
		return "updatePage";
	}
	@RequestMapping(value = "getApt", method = RequestMethod.POST)
	private @ResponseBody String getApt(@RequestBody String input)
	{
		IntercomUtils.printInfo("getApt API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String imei = inputJson.getString("imei");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getApt(user,imei,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse("No proper input!");
		}
	}
	@RequestMapping(value = "getAptPlan", method = RequestMethod.POST)
	private @ResponseBody String getAptPlan(@RequestBody String input)
	{
		IntercomUtils.printInfo("getApt API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String imei = inputJson.getString("imei");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getAptPlan(user,imei,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse("No proper input!");
		}
	}
}
