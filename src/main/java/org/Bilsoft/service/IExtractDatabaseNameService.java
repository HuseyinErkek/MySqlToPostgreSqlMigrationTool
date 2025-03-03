package org.Bilsoft.service;

public interface IExtractDatabaseNameService {
    public String extractDatabaseName(String sqlQuery) throws  IllegalArgumentException;
    public  String getDatabaseName();

}
