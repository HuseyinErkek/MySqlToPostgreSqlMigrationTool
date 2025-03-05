package org.Bilsoft.service.Impl.transformer;

import org.Bilsoft.service.ISQLTransformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * MySQL SQL ifadelerini PostgreSQL SQL ifadelerine dönüştüren sınıf.
 */
public class SQLTransformationServiceImpl  {
    private static final Logger log = LoggerFactory.getLogger(SQLTransformationServiceImpl.class);

    public String transform(List<ISQLTransformationService> transformations, String mysqlQuery) {
        if (mysqlQuery == null || mysqlQuery.isEmpty()) {
            log.warn("Dönüştürülecek SQL sorgusu boş veya null.");
            return mysqlQuery; // Boş veya null ise, aynı şekilde döndür
        }

        String postgresqlQuery = mysqlQuery;
        try {
            for (ISQLTransformationService transformation : transformations) {
                postgresqlQuery = transformation.transformCreateTableQuery(postgresqlQuery);
                log.debug("SQL dönüştürme adımı: {}", transformation.getClass().getSimpleName()); // Hangi dönüşümün uygulandığını logla
            }
            log.trace("MySQL sorgusu dönüştürüldü: {}", mysqlQuery); // Orijinal sorguyu logla (trace seviyesinde)
            log.trace("PostgreSQL sorgusu: {}", postgresqlQuery); // Dönüştürülmüş sorguyu logla (trace seviyesinde)
            return postgresqlQuery;
        } catch (Exception e) { // Daha genel bir exception yakala
            log.error("SQL dönüştürme sırasında hata oluştu: {}", e.getMessage(), e);
            throw e; // Hatayı yeniden fırlat
        }
    }

    /*İhtiyaç duyulursa başka dönüşüm metotları da buraya eklenebilir.
    // Örneğin, toplu dönüştürme işlemi için bir metot:
    public List<String> transformMultipleMySQLToPostgreSQL(List<String> mysqlQueries) {
        return mysqlQueries.stream()
                .map(this::transform) // transform metodunu kullan
                .toList(); // Java 16+ kullanıyorsanız toList(), daha eski sürümlerde collect(Collectors.toList())
    }*/

}