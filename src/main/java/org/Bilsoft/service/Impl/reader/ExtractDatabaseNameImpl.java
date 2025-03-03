package org.Bilsoft.service.Impl.reader;

import org.Bilsoft.service.IExtractDatabaseNameService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractDatabaseNameImpl implements IExtractDatabaseNameService {

    private String databaseName;

    @Override
    public String extractDatabaseName(String readedSql) {
        Pattern databasePattern = Pattern.compile("-- Host:.*?Database:\\s*([a-zA-Z0-9_]+)");
        Matcher matcher = databasePattern.matcher(readedSql);

        if (matcher.find()) {
            this.databaseName = matcher.group(1);
            return this.databaseName;
        } else {
            throw new IllegalArgumentException("Geçersiz CREATE DATABASE sorgusu: " + readedSql);
        }
    }

    @Override
    public String getDatabaseName() {
        return this.databaseName;
    }
}