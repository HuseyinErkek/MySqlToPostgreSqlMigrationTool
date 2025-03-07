package org.Bilsoft.service.Impl.transformer.converter;

import org.Bilsoft.service.ISQLTransformationService;

public class AutoIncrementTransformationServiceImpl implements ISQLTransformationService {

    @Override
    public String transformCreateTableQuery(String mysqlQuery) {
        //.replaceAll("\\b(INT|INTEGER)\\s+AUTO_INCREMENT\\b", "SERIAL")

        return mysqlQuery
                .replaceAll("(?i)\\b(INT|INTEGER)\\s+NOT\\s+NULL\\s+AUTO_INCREMENT\\b", "SERIAL")
                .replaceAll("(?i)\\b(BIGINT)\\s+NOT\\s+NULL\\s+AUTO_INCREMENT\\b", "BIGSERIAL");
    }
}
