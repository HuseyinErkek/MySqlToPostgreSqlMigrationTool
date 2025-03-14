package org.Bilsoft.service.Impl.transformer.converter;

import org.Bilsoft.service.ISQLTransformationService;

/**
 * MySQL tamsayı tiplerini PostgreSQL'e uygun tiplere dönüştürür.
 */
public class IntTypesTransformationServiceImpl implements ISQLTransformationService {
private final SQLTypeConverterImpl typeConverter;

    public IntTypesTransformationServiceImpl(SQLTypeConverterImpl typeConverter) {
        this.typeConverter = typeConverter;
    }

    @Override
    public String transformCreateTableQuery(String mysqlQuery) {
        return typeConverter.convertType(mysqlQuery, "(?i)\\b(TINYINT|MEDIUMINT)(\\(\\d+\\))?\\b",
                "smallint","smallint");
    }

}
