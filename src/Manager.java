import java.io.BufferedReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Manager implements InvocationHandler {

    BufferedReader bufferedReader;
    FileWriter fileWriter;
    WordCounter wordCounter;
    ThreadControl threadControl;
    MyClassLoader myClassLoader;
    CurrentType currentType;
    Boolean lastType;

    public Manager(BufferedReader bufferedReader, FileWriter fileWriter, ThreadControl threadControl, CurrentType currentType) {
        this.bufferedReader = bufferedReader;
        this.fileWriter = fileWriter;
        this.threadControl = threadControl;
        myClassLoader = new MyClassLoader();
        this.currentType = currentType;
        lastType = currentType.getWithCh();


    }

    public void reloadClass() {
        Class<?> aClass;
        try {
            if (currentType.getWithCh()) {
                aClass = myClassLoader.loadClass("WordCounterImpl2");
            } else {
                aClass = myClassLoader.loadClass("WordCounterImpl1");
            }
            wordCounter = (WordCounter) aClass.newInstance();
            wordCounter.init(fileWriter, bufferedReader, threadControl);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        if (wordCounter == null) {
            reloadClass();
        }
        if (lastType != currentType.getWithCh()) {
            reloadClass();
        }

        lastType = currentType.getWithCh();

        method.invoke(wordCounter, objects);


        return null;
    }
}
