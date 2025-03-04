package org.Bilsoft.service;

public interface IDatabaseScriptGeneratorService {
    String generateCreateDatabaseScript(String databaseName, String sqlScript);
}
