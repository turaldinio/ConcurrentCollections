package DrawAndLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    private static LogWriter logWriter;
    private File file = new File("logFile.txt");
    private FileWriter writer;

    private LogWriter() {
        if (file.length() != 0) {
            file.delete();
            file = new File("logFile.txt");
        }
        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void writeResult(String mapType, String operationType, int size, int time) {
        try {
            writer.write(String.format("%s %s %d элементов: %d мс\n", mapType, operationType, size, time));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка записи данных в файл");
        }
    }

    public static LogWriter getInstance() {
        if (logWriter == null) {
            logWriter = new LogWriter();
        }
        return logWriter;
    }
}
