import java.util.HashMap;

public class FileWriterImpl implements FileWriter {

    HashMap<String, Integer> output;

    public FileWriterImpl(HashMap<String, Integer> output) {
        this.output = output;
    }

    @Override
    public void addWord(String word) {
        synchronized (output) {
            if (!output.containsKey(word)) {
                output.put(word, 1);
            } else {
                output.put(word, output.get(word) + 1);
            }
            synchronized (System.out) {
                System.out.println(output.entrySet());
            }
        }
    }

    public HashMap<String, Integer> getOutput() {
        return output;
    }

    public void setOutput(HashMap<String, Integer> output) {
        this.output = output;
    }
}
