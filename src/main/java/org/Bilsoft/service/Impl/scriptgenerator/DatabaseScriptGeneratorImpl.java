package org.Bilsoft.service.Impl.scriptgenerator;

import org.Bilsoft.service.IDatabaseScriptGeneratorService;

public class DatabaseScriptGeneratorImpl implements IDatabaseScriptGeneratorService {
    @Override
    public String generateCreateDatabaseScript(String databaseName, String sqlScript) {
        String createStatement = "CREATE DATABASE " + databaseName + ";\n";
        return createStatement.concat(sqlScript);
    }
}
