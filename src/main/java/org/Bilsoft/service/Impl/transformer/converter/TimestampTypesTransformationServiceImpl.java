package org.Bilsoft.service.Impl.transformer.converter;

import org.Bilsoft.service.ISQLTransformationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * MySQL TIMESTAMP tiplerini PostgreSQL tiplerine dönüştürür. CURRENT_TIMESTAMP ve ON UPDATE CURRENT_TIMESTAMP ifadelerini TIMESTAMPTZ'ye dönüştürür.
 */
public class TimestampTypesTransformationServiceImpl implements ISQLTransformationService {
    @Override
    public String transformCreateTableQuery(String mysqlQuery) {
        Pattern pattern = Pattern.compile("\\bTIMESTAMP\\b(?!\\s*WITH TIME ZONE)");
        Matcher matcher = pattern.matcher(mysqlQuery);
        StringBuffer convertedQuery = new StringBuffer();

        while (matcher.find()) {
            String replacement = (mysqlQuery.contains("CURRENT_TIMESTAMP") || mysqlQuery.contains("ON UPDATE")) ? "TIMESTAMPTZ" : "TIMESTAMP";
            matcher.appendReplacement(convertedQuery, replacement);
        }
        matcher.appendTail(convertedQuery);
        return convertedQuery.toString();
    }
}
