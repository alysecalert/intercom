package org.intercom.services;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.intercom.constants.IntercomConstants;
import org.intercom.dao.StoredProcedureDao;
import org.intercom.utils.IntercomUtils;

public class StoredProcedureServices implements IntercomConstants{

	private static StoredProcedureServices storedProcedureServices=null;
	
	private StoredProcedureServices() {
	}
	public static StoredProcedureServices getInstances()
	{
		if(storedProcedureServices == null)
		{
			storedProcedureServices = new StoredProcedureServices();
		}
		return storedProcedureServices;
	}
	
	public String getMasterDelta(String userId,String password)
	{
		try {
			IntercomUtils.printInfo("getMasterDelta");
			JSONObject aptResponseJson = new JSONObject(getApt(userId, password));
			JSONArray aptData = aptResponseJson.getJSONArray("data");
			String aptName = aptData.getString(0);
			IntercomUtils.printInfo("got aptName "+aptName);
			Object[] procedureParams = {aptName,userId}; 
			IntercomUtils.printInfo("Calling "+GET_LAST_SYNC_TIME+" StoredProcedure get master delta");
			String syncTime = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_LAST_SYNC_TIME, procedureParams);
			if(!syncTime.isEmpty())
			{
				try {
					syncTime = new JSONArray(syncTime).getString(0);
				} catch (JSONException e) {
					IntercomUtils.printError("Error parsing sync time array ", e);
				}
			}
			IntercomUtils.printInfo("syncTime "+syncTime);
			Object[] procedureParams2 = {aptName,syncTime};
			IntercomUtils.printInfo("Calling "+GET_MASTER_DELTA+" StoredProcedure");
			String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_MASTER_DELTA, procedureParams2);
			IntercomUtils.printInfo("Final response of getMaster delta: "+response);
			response = response.replace("\\\"", "\"");
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (JSONException e) {
			IntercomUtils.printError("Error creating JSON", e);
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	public String getApt(String userId,String password)
	{
    	Object[] procedureParams = {userId,password}; 
		IntercomUtils.printInfo("Calling "+storedProcedureServices+" StoredProcedure for get Apt");
		String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_APT, procedureParams);
		IntercomUtils.printInfo("Final response of getAPt : "+response);
		response = response.replace("\\\"", "\"");
		try {
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (JSONException e) {
			IntercomUtils.printError("Error creating JSON", e);
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	public String getAptPlan(String userId,String password)
	{
		try
		{
			JSONObject aptResponseJson = new JSONObject(getApt(userId, password));
			JSONArray aptData = aptResponseJson.getJSONArray("data");
			String aptName = aptData.getString(0);
			Object[] procedureParams2 = {aptName};
			String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_APT_PLAN, procedureParams2);
			response = response.replace("\\\"", "\"");
			IntercomUtils.printInfo("Final response of getAptPlan : "+response);
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (JSONException e) {
			IntercomUtils.printError("Error creating JSON", e);
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	public String getSyncFreq(String userId,String password)
	{
		try
		{
	    	Object[] procedureParams = {userId,password}; 
			IntercomUtils.printInfo("Calling "+GET_SYNC_FREQ+" StoredProcedure for Get apt plan");
			String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_SYNC_FREQ, procedureParams);
			response = response.replace("\\\"", "\"");
			IntercomUtils.printInfo("Final response of getSyncFreq : "+response);
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (Exception e) {
			IntercomUtils.printError("Error creating JSON", e);
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	public String getFirstSyncOfDay(String userId,String password)
	{
		try
		{
	    	Object[] procedureParams = {userId,password}; 
			IntercomUtils.printInfo("Calling "+GET_FIRST_SYNC_OF_DAY+" StoredProcedure for Get apt plan");
			String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_FIRST_SYNC_OF_DAY, procedureParams);
			response = response.replace("\\\"", "\"");
			IntercomUtils.printInfo("Final response of getFirstSyncOfDay : "+response);
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (Exception e) {
			IntercomUtils.printError("Error creating JSON", e);
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	public String getOtpDelta(String userId,String password)
	{
		try {
			IntercomUtils.printInfo("getOtpDelta");
			JSONObject aptResponseJson = new JSONObject(getApt(userId, password));
			JSONArray aptData = aptResponseJson.getJSONArray("data");
			String aptName = aptData.getString(0);
			IntercomUtils.printInfo("got aptName "+aptName);
			Object[] procedureParams = {aptName,userId}; 
			IntercomUtils.printInfo("Calling "+GET_LAST_SYNC_TIME+" StoredProcedure get master delta");
			String syncTime = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_LAST_SYNC_TIME, procedureParams);
			if(!syncTime.isEmpty())
			{
				try {
					syncTime = new JSONArray(syncTime).getString(0);
				} catch (JSONException e) {
					IntercomUtils.printError("Error parsing sync time array ", e);
				}
			}
			IntercomUtils.printInfo("syncTime "+syncTime);
			Object[] procedureParams2 = {aptName,syncTime};
			IntercomUtils.printInfo("Calling "+GET_OTP_DELTA+" StoredProcedure");
			String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_OTP_DELTA, procedureParams2);
			IntercomUtils.printInfo("Final response of getMaster delta: "+response);
			response = response.replace("\\\"", "\"");
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (JSONException e) {
			IntercomUtils.printError("Error creating JSON", e);
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
	public String postTransLog(String userId,String password,JSONObject docs)
	{
		try {
			IntercomUtils.printInfo("postTransLog");
			JSONObject aptResponseJson = new JSONObject(getApt(userId, password));
			JSONArray aptData = aptResponseJson.getJSONArray("data");
			String aptName = aptData.getString(0);
			IntercomUtils.printInfo("got aptName "+aptName);
			Object[] procedureParams2 = {docs};
			IntercomUtils.printInfo("Calling "+BULK_IMPORT+" StoredProcedure");
			String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(BULK_IMPORT, procedureParams2);
			IntercomUtils.printInfo("Final response of getMaster delta: "+response);
			response = response.replace("\\\"", "\"");
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (JSONException e) {
			IntercomUtils.printError("Error creating JSON", e);
			return IntercomUtils.getFailureResponse(FAILURE_MSG);
		}
	}
}
