package org.Bilsoft.service.Impl.cleaner;

import org.Bilsoft.service.ISQLStatementsCleanerService;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SQLStatementsCleanerImpl implements ISQLStatementsCleanerService {
    @Override
    public String cleanSQLStatements(String mysqlScript) {
        return Arrays.stream(mysqlScript.split("\n")) // Satırları böl
                .map(line -> line.replaceAll("(?m)^--.*$", "")) // Tek satır yorumları kaldır
                .map(line -> line.replaceAll("/\\*.*?\\*/;", "")) // Çok satırlı yorumları kaldır
                .map(line -> line.replaceAll("ENGINE=.*?\\s*;\\s*", ";")) // ENGINE ifadelerini kaldır
                //.map(line -> line.replaceAll("'", "")) // Tek tırnakları kaldır
                .map(line -> line.replaceAll("`", "")) // Ters tırnakları kaldır
                .map(line -> line.replaceAll("unsigned", "")) //unsigned kelimesini siler.
                .map(String::trim) // Satır başı ve sonundaki boşlukları temizle
                .filter(line -> !line.isEmpty()) // Boş satırları filtrele
                .collect(Collectors.joining("\n")); // Sonucu tekrar birleştir
    }
}
