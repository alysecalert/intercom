package org.intercom.daofactory;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.ConsistencyLevel;
public class DocumentDbDaoFactory {

    private static final String HOST = "https://alyseccom.documents.azure.com:443/";
    private static final String MASTER_KEY = "wdyYRq4iepFR0WtpN6TFVwviPNclOkpfKzSjSK79O7gwnuuLJfEXBy71JVTPhIemSzfXifyiCQKFF7xbSMfmGA==";

    private static DocumentClient documentClient;

    public static DocumentClient getDocumentClient() {
        if (documentClient == null) {
            documentClient = new DocumentClient(HOST, MASTER_KEY,
                    ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
        }

        return documentClient;
    }



}
