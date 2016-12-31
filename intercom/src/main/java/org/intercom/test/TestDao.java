package org.intercom.test;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.intercom.daofactory.DocumentDbDaoFactory;

import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.SqlQuerySpec;
import com.microsoft.azure.documentdb.StoredProcedure;
import com.microsoft.azure.documentdb.StoredProcedureResponse;

public class TestDao {


    // The name of our database.
    private static final String DATABASE_ID = "alycom";

    // The name of our collection.
    private static final String COLLECTION_ID = "intercom";

    // We'll use Gson for POJO <=> JSON serialization for this example.
    //private static Gson gson = new Gson();

    // The DocumentDB Client
    private static DocumentClient documentClient = DocumentDbDaoFactory
            .getDocumentClient();

    // Cache for the database object, so we don't have to query for it to
    // retrieve self links.
    private static Database databaseCache;

    // Cache for the collection object, so we don't have to query for it to
    // retrieve self links.
    private static DocumentCollection collectionCache;
    
    private Database getTodoDatabase() {
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
                    // TODO: Something has gone terribly wrong - the app wasn't
                    // able to query or create the collection.
                    // Verify your connection, endpoint, and key.
                    e.printStackTrace();
                }
            }
        }

        return databaseCache;
    }
    public String readTodoItems() {
        // Get the collection if it exists.
        List<DocumentCollection> collectionList = documentClient
                .queryCollections(
                        getTodoDatabase().getSelfLink(),
                        "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID
                                + "'", null).getQueryIterable().toList();
        collectionCache = collectionList.get(0);
    	List<Document> documents = documentClient
                .queryDocuments(
                		collectionCache.getSelfLink(),
                        "SELECT * FROM root r", null).getQueryIterable().toList();
    	JSONArray arr = new JSONArray();
    	for(Document d : documents)
    	{
    		try {
				arr.put(new JSONObject(d.toString()));
			} catch (JSONException e) {
				System.err.println("\n\n ERROR "+e.getMessage());
			}
    	}
         try {
			return new JSONObject().put("data", arr).toString();
		} catch (JSONException e) {
			return "";
		}
    }
    public String testStoredProcedure()
    {
    	String response = "";
    	//Get_flat_no
    	String spName = "Get_last_sync_time";
    	Object[] procedureParams = {"ccvoa-560064","9845678900"}; 
    	try {
    		StoredProcedure sProc = documentClient.queryStoredProcedures(collectionCache.getSelfLink(),
    				new SqlQuerySpec("SELECT * FROM root r where r.id='" + spName + "'"), null).getQueryIterable().toList().get(0);
			StoredProcedureResponse storedProcedureResponse = documentClient.executeStoredProcedure(sProc.getSelfLink(), procedureParams);
			System.out.println("--------------------------");
			System.out.println("storedProcedureResponse "+storedProcedureResponse.getResponseAsString());
			response = storedProcedureResponse.getResponseAsString();
		} catch (DocumentClientException e) {
			System.err.println("\n\n ERROR "+e.getMessage());
		}

    	try {
			return new JSONObject().put("data", response).toString();
		} catch (JSONException e) {
			return "";
		}    }
}
