import java.io.BufferedReader;
import java.io.IOException;

public interface WordCounter {
    public void startCounting() throws IOException;

    void init(FileWriter fileWriter, BufferedReader bufferedReader, ThreadControl threadControl);

    void setThreadControl(ThreadControl threadControl);
}
