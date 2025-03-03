package org.Bilsoft.service.Impl.reader;

import org.Bilsoft.service.IMySQLReaderService;
import org.Bilsoft.service.ISQLStatementSplitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * MySQL döküm dosyasını okuyan ve SQL ifadelerini ayrıştıran sınıf.
 */
public class MySQLReaderServiceImpl implements IMySQLReaderService {

    private static final Logger logger = LoggerFactory.getLogger(MySQLReaderServiceImpl.class);

    @Override
    public String readDumpFile(String filePath) throws IOException, IllegalArgumentException {
        if (filePath == null || filePath.isEmpty()) {
            logger.error("Dosya yolu boş veya null olamaz.");
            throw new IllegalArgumentException("Dosya yolu boş veya null olamaz.");
        }

        String readedSqlScript = "";

        try {
            readedSqlScript = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
            //readedSqlScript = Files.readString(Paths.get(filePath));

            if (readedSqlScript == null || readedSqlScript.isEmpty()) {
                logger.warn("Döküm dosyası boş.");
            }

        } catch (IOException e) {
            logger.error("Döküm dosyası okunurken hata oluştu: {}", e.getMessage(), e); // Hata loglama
            throw e; // Hatayı yeniden fırlat
        }

        return readedSqlScript; // Metodun sonunda döndür
    }

}
