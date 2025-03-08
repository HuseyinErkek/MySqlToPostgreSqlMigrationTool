package org.Bilsoft.service.Impl.transformer.converter;

import org.Bilsoft.service.ISQLTransformationService;

public class BooleanDefaultTransformationServiceImpl implements ISQLTransformationService {
    @Override
    public String transformCreateTableQuery(String mysqlQuery) {
        return mysqlQuery.replaceAll("(?i)DEFAULT\\s*'0'", "DEFAULT FALSE")
                .replaceAll("(?i)DEFAULT\\s*'1'", "DEFAULT TRUE");
    }

}
