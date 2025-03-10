package org.Bilsoft.service.Impl.transformer.converter;

import org.Bilsoft.service.ISQLTransformationService;

public class DefaultNullTransformationServiceImpl implements ISQLTransformationService {
    @Override
    public String transformCreateTableQuery(String mysqlQuery) {
        return mysqlQuery
                .replaceAll("(?i)\\sdefault\\snull", "");

    }
}
