package org.intercom.utils;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.intercom.constants.IntercomConstants;

public class IntercomUtils implements IntercomConstants{

	public static void printInfo(String msg)
	{
		System.out.println("msg: "+msg);
	}
	public static void printError(String msg, Throwable e)
	{
		System.err.println("ERROR ----------- ");
		System.err.println("Custom msg: "+msg+" Exception msg: "+e.getMessage());
		System.err.println("ERROR ----------- ");
	}
	public static String getSuccessResponse(Object dataMsg)
	{
		JSONObject responseJson = new JSONObject();
		try {
			responseJson.put("statusCode", SUCCESS_CODE);
			responseJson.put("status",SUCCESS);
			responseJson.put("data", dataMsg);
		} catch (JSONException e) {
			printError("Error creating common success response", e);
		}
		return responseJson.toString();
	}
	public static String getFailureResponse(Object dataMsg)
	{
		JSONObject responseJson = new JSONObject();
		try {
			responseJson.put("statusCode", FAILURE_CODE);
			responseJson.put("status",FAILURE);
			responseJson.put("data", dataMsg);
		} catch (JSONException e) {
			printError("Error creating common failure response", e);
		}
		return responseJson.toString();
	}
}
