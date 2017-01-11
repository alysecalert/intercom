package org.intercom.constants;

public interface IntercomConstants {

	//API's
	public static final String LOGIN_API = "login";
	public static final String GET_MASTER_DELTA_API= "getMasterDelta";
	public static final String GET_APT_API = "getApt";
	public static final String GET_APT_PLAN_API = "getAptPlan";
	public static final String GET_SYNC_FREQ_API ="getSyncFreq";
	public static final String GET_FIRST_SYNC_OF_DAY_API ="getFirstSyncOfDay";
	public static final String GET_OTP_DELTA_API ="getOtpDelta";
	public static final String POST_TRANS_LOG_API ="postTransLog";
	
	//Stored procedures
	public static final String GET_LAST_SYNC_TIME = "Get_last_sync_time";
	public static final String GET_MASTER_DELTA =  "Get_master_delta";
	public static final String GET_APT = "Get_apt";
	public static final String GET_APT_PLAN = "Get_apt_plan";
	public static final String GET_SYNC_FREQ = "Get_sync_freq";
	public static final String GET_FIRST_SYNC_OF_DAY ="Get_first_sync_of_day";
	public static final String GET_OTP_DELTA =  "Get_otp_delta";
	public static final String BULK_IMPORT =  "bulkImport";
	
	//Response
	public static final String SUCCESS =  "Success";
	public static final int SUCCESS_CODE =  200;
	
	public static final String FAILURE =  "Failure";
	public static final String FAILURE_MSG =  "Invalid userId/password";
	public static final int FAILURE_CODE =  500;
	
}
