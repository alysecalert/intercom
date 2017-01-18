package org.intercom.dao;

import java.util.List;

import org.intercom.daofactory.DocumentDbDaoFactory;
import org.intercom.utils.IntercomUtils;

import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.SqlQuerySpec;
import com.microsoft.azure.documentdb.StoredProcedure;
import com.microsoft.azure.documentdb.StoredProcedureResponse;


public class StoredProcedureDao {

	private static StoredProcedureDao storedProcedureDao=null;
	
	private StoredProcedureDao() {
	}
	public static StoredProcedureDao getInstances()
	{
		if(storedProcedureDao == null)
		{
			storedProcedureDao = new StoredProcedureDao();
			setCollectionCache();
		}
		return storedProcedureDao;
	}
	
    private static final String DATABASE_ID = "alycom";

    // The DocumentDB Client
    private static DocumentClient documentClient = DocumentDbDaoFactory
            .getDocumentClient();

    // Cache for the database object, so we don't have to query for it to
    // retrieve self links.
    private static Database databaseCache;

    // Cache for the collection object, so we don't have to query for it to
    // retrieve self links.
    private static DocumentCollection collectionCache;
    
    private static Database getTodoDatabase() {
        if (databaseCache == null) {
            // Get the database if it exists
            List<Database> databaseList = documentClient
                    .queryDatabases(
                            "SELECT * FROM root r WHERE r.id='" + DATABASE_ID
                                    + "'", null).getQueryIterable().toList();

            if (databaseList.size() > 0) {
                // Cache the database object so we won't have to query for it
                // later to retrieve the selfLink.
                databaseCache = databaseList.get(0);
            } else {
                // Create the database if it doesn't exist.
                try {
                    Database databaseDefinition = new Database();
                    databaseDefinition.setId(DATABASE_ID);

                    databaseCache = documentClient.createDatabase(
                            databaseDefinition, null).getResource();
                } catch (DocumentClientException e) {
                	IntercomUtils.printError("Error setting documentClient", e);
                }
            }
        }

        return databaseCache;
    }
    
    private static void setCollectionCache()
    {
    	List<DocumentCollection> collectionList = documentClient
                .queryCollections(
                        getTodoDatabase().getSelfLink(),
                        "SELECT * FROM root", null).getQueryIterable().toList();
    	collectionCache = collectionList.get(0);
    }
    
	public String getResponseOfStoredProcedure(String stroredProcedureName,Object[] procedureParams)
	{
    	String response = "";
    	try {
    		
    		IntercomUtils.printInfo("Calling Storeprocedure: "+stroredProcedureName+" with params: ");
    		for(Object param: procedureParams)
    		{
    			IntercomUtils.printInfo(param.toString());
    		}
    		StoredProcedure sProc = documentClient.queryStoredProcedures(collectionCache.getSelfLink(),
    				new SqlQuerySpec("SELECT * FROM root r where r.id='" + stroredProcedureName + "'"), null).getQueryIterable().toList().get(0);
			StoredProcedureResponse storedProcedureResponse = documentClient.executeStoredProcedure(sProc.getSelfLink(), procedureParams);
			response = storedProcedureResponse.getResponseAsString();
			IntercomUtils.printInfo("Raw response of Storeprocedure: "+stroredProcedureName+" "+response);
			if(response.length()>1)
			{
				response = response.substring(1, response.length()-1);
			}
		} catch (DocumentClientException e) {
			IntercomUtils.printError("ERROR while fetching stored procedures", e);
			response = e.getMessage();
		}
    	return response;
	}
}
