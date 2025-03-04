package org.Bilsoft.service;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.statement.Statement;

import java.util.List;

public interface ISQLStatementSplitterService  {
    public List<String> splitSqlStatements(String sqlScript) throws IllegalArgumentException;
}
