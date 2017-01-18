package org.intercom.controller;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.intercom.constants.IntercomConstants;
import org.intercom.services.StoredProcedureServices;
import org.intercom.utils.IntercomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntercomStoredProcedureController implements IntercomConstants{

	@RequestMapping(value = {"/",LOGIN_API}, method = RequestMethod.GET)
	private String getLoginPage()
	{
		IntercomUtils.printInfo("getting login page");
		return "login";
	}
	
	@RequestMapping(value = GET_MASTER_DELTA_API, method = RequestMethod.POST)
	private @ResponseBody String getMasterDelta(@RequestBody String input)
	{
		IntercomUtils.printInfo(GET_MASTER_DELTA_API+" API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getMasterDelta(user,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	
	@RequestMapping(value = {"getUpdatePage"}, method = RequestMethod.GET)
	private String getUpdatePage()
	{
		IntercomUtils.printInfo("getting login page");
		return "updatePage";
	}
	@RequestMapping(value = GET_APT_API, method = RequestMethod.POST)
	private @ResponseBody String getApt(@RequestBody String input)
	{
		IntercomUtils.printInfo(GET_APT_API+" API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getApt(user,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	@RequestMapping(value = GET_APT_PLAN_API, method = RequestMethod.POST)
	private @ResponseBody String getAptPlan(@RequestBody String input)
	{
		IntercomUtils.printInfo(GET_APT_PLAN_API+" API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getAptPlan(user,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	@RequestMapping(value = GET_SYNC_FREQ_API, method = RequestMethod.POST)
	private @ResponseBody String getSyncFreq(@RequestBody String input)
	{
		IntercomUtils.printInfo(GET_SYNC_FREQ_API+" API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getSyncFreq(user,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	@RequestMapping(value = GET_FIRST_SYNC_OF_DAY_API, method = RequestMethod.POST)
	private @ResponseBody String getFirstSyncOfDay(@RequestBody String input)
	{
		IntercomUtils.printInfo(GET_FIRST_SYNC_OF_DAY_API+" API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getFirstSyncOfDay(user,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	@RequestMapping(value = GET_OTP_DELTA_API, method = RequestMethod.POST)
	private @ResponseBody String getOtpDelta(@RequestBody String input)
	{
		IntercomUtils.printInfo(GET_OTP_DELTA_API+" API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String password = inputJson.getString("password");
			return StoredProcedureServices.getInstances().getOtpDelta(user,password);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	@RequestMapping(value = POST_TRANS_LOG_API, method = RequestMethod.POST)
	private @ResponseBody String postTransLog(@RequestBody String input)
	{
		IntercomUtils.printInfo(POST_TRANS_LOG_API+" API Called");
		try {
			JSONObject inputJson = new JSONObject(input);
			String user= inputJson.getString("userId");
			String password = inputJson.getString("password");
			JSONArray docs = inputJson.getJSONArray("docs");
			return StoredProcedureServices.getInstances().postTransLog(user,password,docs);
		} catch (JSONException e) {
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
}
