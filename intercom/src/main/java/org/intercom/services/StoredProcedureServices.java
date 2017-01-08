package org.intercom.services;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
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
	
	public String getMasterDelta()
	{
    	Object[] procedureParams = {"ccvoa-560064","9845678900"}; 
		IntercomUtils.printInfo("Calling "+storedProcedureServices+" StoredProcedure");
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
		Object[] procedureParams2 = {"ccvoa-560064",syncTime};
		IntercomUtils.printInfo("Calling "+storedProcedureServices+" StoredProcedure");
		String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_MASTER_DELTA, procedureParams2);
		IntercomUtils.printInfo("Final response of getMaster delta: "+response);
		try {
			response = response.replace("\\\"", "\"");
			return IntercomUtils.getSuccessResponse(new JSONArray(response));
		} catch (JSONException e) {
			IntercomUtils.printError("Error creating JSON", e);
			return "";
		}
	}
	public String getApt(String userId, String imei)
	{
    	Object[] procedureParams = {userId,imei}; 
		IntercomUtils.printInfo("Calling "+storedProcedureServices+" StoredProcedure");
		String response = StoredProcedureDao.getInstances().getResponseOfStoredProcedure(GET_APT, procedureParams);
		IntercomUtils.printInfo("Final response of getAPt : "+response);
		return IntercomUtils.getSuccessResponse(response);
	}
}
