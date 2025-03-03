package org.Bilsoft.service;

import java.io.IOException;
import java.util.List;

public interface IMySQLReaderService {
    String readDumpFile(String filePath) throws IOException;
}
