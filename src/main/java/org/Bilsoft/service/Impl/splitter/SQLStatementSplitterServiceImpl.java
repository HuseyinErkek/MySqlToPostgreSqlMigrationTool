package org.Bilsoft.service.Impl.splitter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import org.Bilsoft.service.ISQLStatementSplitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLStatementSplitterServiceImpl implements ISQLStatementSplitterService {
    private static final Logger logger = LoggerFactory.getLogger(SQLStatementSplitterServiceImpl.class);
    //private final  Pattern pattern = Pattern.compile("(?<=;)([^;]+;)|([^;]+;)"); // Regex deseni
    //private final  Pattern pattern = Pattern.compile("([^;]+;\n)");
    private final Pattern pattern = Pattern.compile("([^;]+;\\s*)");

    @Override
    public List<String> splitSqlStatements(String sqlScript) throws IllegalArgumentException {
        if (sqlScript == null || sqlScript.isEmpty()) {
            logger.error("SQL scripti boş veya null olamaz.");
            throw new IllegalArgumentException("SQL scripti boş veya null olamaz.");
        }

        List<String> spiletSqlStatements = new ArrayList<>();

        Matcher matcher = pattern.matcher(sqlScript);

        while (matcher.find()) {
            String statement = matcher.group().trim(); // Eşleşen tüm grubu al
            if (!statement.isEmpty()) {
                String newStatemet = statement.replaceAll("\\s+", " ");
                spiletSqlStatements.add(newStatemet);
                logger.debug("Ayrıştırılan SQL ifadesi: {}", newStatemet);
            }
        }

        logger.info("Toplam {} SQL ifadesi ayrıştırıldı.", spiletSqlStatements.size());

        return spiletSqlStatements;
    }
}