package org.Bilsoft.starter;

import org.Bilsoft.service.*;
import org.Bilsoft.service.Impl.cleaner.SQLStatementsCleanerImpl;
import org.Bilsoft.service.Impl.dbmanager.DatabaseOperationsServiceImpl;
import org.Bilsoft.service.Impl.dbmanager.PostgreSQLConnectionServiceImpl;
import org.Bilsoft.service.Impl.executor.PostgreSQLExecutorServiceImpl;
import org.Bilsoft.service.Impl.reader.DatabaseTableOperationsServiceImpl;
import org.Bilsoft.service.Impl.filter.SQLFilterServiceImpl;
import org.Bilsoft.service.Impl.reader.ExtractDatabaseNameImpl;
import org.Bilsoft.service.Impl.scriptgenerator.DatabaseScriptGeneratorImpl;
import org.Bilsoft.service.Impl.splitter.SQLStatementSplitterServiceImpl;
import org.Bilsoft.service.Impl.reader.MySQLReaderServiceImpl;
import org.Bilsoft.service.Impl.transformer.SQLInsertTransformationServiceImpl;
import org.Bilsoft.service.Impl.transformer.converter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // 1. SQLTypeConverter ve Dönüşümler
        SQLTypeConverterImpl sqlTypeConverter = new SQLTypeConverterImpl();
        List<ISQLTransformationService> transformations = new ArrayList<>();
        transformations.add(new AutoIncrementTransformationServiceImpl());
        transformations.add(new TinyIntToBooleanTransformationServiceImpl());
        transformations.add(new DefaultNullTransformationServiceImpl());
        transformations.add(new IntTypesTransformationServiceImpl(sqlTypeConverter));
        transformations.add(new BooleanDefaultTransformationServiceImpl());
        transformations.add(new OtherVariableTransformationServiceImpl());
        transformations.add(new ConvertBooleanValuesServiceImpl());
        //transformations.add(new UnsignedTypesTransformationServiceImpl(sqlTypeConverter));
        //transformations.add(new TimestampTypesTransformationServiceImpl());

        // 2. Diğer Servisler
        IMySQLReaderService mySQLReaderService = new MySQLReaderServiceImpl();
        IExtractDatabaseNameService extractDatabaseNameService = new ExtractDatabaseNameImpl();
        IDatabaseScriptGeneratorService databaseScriptGeneratorService = new DatabaseScriptGeneratorImpl();
        ISQLStatementsCleanerService sqlStatementsCleanerService = new SQLStatementsCleanerImpl();
        ISQLStatementSplitterService sqlStatementSplitterService = new SQLStatementSplitterServiceImpl();
        ISQLFilterService sqlFilterService = new SQLFilterServiceImpl();
        //IDatabaseTableOperationsService databaseTableOperationsService = new DatabaseTableOperationsServiceImpl();
        //ISQLInsertTransformationService sqlInsertTransformationService = new SQLInsertTransformationServiceImpl(databaseTableOperationsService);
        IDatabaseConnectionService databaseConnectionService = new PostgreSQLConnectionServiceImpl("jdbc:postgresql://localhost:5432/","postgres","1234"); // Veritabanı bilgilerinizi buraya girin!
        IDatabaseOperationsService databaseOperationsService = new DatabaseOperationsServiceImpl(databaseConnectionService);
        IPostgreSQLExecutorService postgreSQLExecutorService = new PostgreSQLExecutorServiceImpl(databaseConnectionService,databaseOperationsService,extractDatabaseNameService);




        // 3. MigrationService
        MigrationService migrationService = new MigrationService(
                mySQLReaderService,extractDatabaseNameService,
                databaseScriptGeneratorService,sqlStatementsCleanerService,
                sqlStatementSplitterService,transformations,sqlFilterService,
                databaseConnectionService,postgreSQLExecutorService
        );

        // 4. Migrasyon
        String dumpFilePath = "/Users/huseyinerkek/Desktop/development/Projects/MySqlToPostgreSqlMigrationTool/src/main/resources/test_db_backup.sql";

        try {
            migrationService.migrate(dumpFilePath);
            logger.info("Migrasyon başarıyla tamamlandı!");
        } catch (IOException e) {
            logger.error("Dump dosyası okuma hatası: {}", e.getMessage(), e);
        } catch (SQLException e) {
            logger.error("SQL Dönüştürme veya Filtreleme sırasında hata oluştu: {}", e.getMessage(), e);
        }
    }
}