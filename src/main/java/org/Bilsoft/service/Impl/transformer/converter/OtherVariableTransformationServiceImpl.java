package org.Bilsoft.service.Impl.transformer.converter;

import org.Bilsoft.service.ISQLTransformationService;

public class OtherVariableTransformationServiceImpl implements ISQLTransformationService {
    @Override
    public String transformCreateTableQuery(String mysqlQuery) {
        return mysqlQuery
                .replaceAll("(?i)FLOAT", "real") // FLOAT'ı REAL'e çevir (büyük/küçük harf duyarsız)
                .replaceAll("(?i)DOUBLE", "double precision") // DOUBLE'ı DOUBLE PRECISION'a çevir
                .replaceAll("(?i)YEAR", "int") // YEAR'ı INTEGER'a çevir
                .replaceAll("(?i)(TINY|MEDIUM|LONG)?TEXT", "text") // TEXT tiplerini çevir (TINYTEXT, MEDIUMTEXT, LONGTEXT -> TEXT)
                .replaceAll("(?i)(MEDIUM|LONG)?BLOB", "bytea"); // BLOB tiplerini çevir (MEDIUMBLOB, LONGBLOB -> BYTEA)
    }
}
