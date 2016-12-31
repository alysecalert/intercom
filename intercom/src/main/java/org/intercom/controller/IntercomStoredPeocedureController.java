package org.intercom.controller;

import org.codehaus.jettison.json.JSONException;
import org.intercom.services.StoredProcedureServices;
import org.intercom.utils.IntercomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntercomStoredPeocedureController {

	@RequestMapping(value = "getMasterDelta", method = RequestMethod.GET)
	public @ResponseBody String getTest() throws JSONException
	{
		IntercomUtils.printInfo("getMasterDelta API Called");
		return StoredProcedureServices.getInstances().getMasterDelta();
	}
}
