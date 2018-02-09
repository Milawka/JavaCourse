import java.io.IOException;
import java.util.Stack;

public class ThreadLauncherImpl implements ThreadLauncher {

    final Stack<WordCounter> wordCounterStack;
    ThreadControl threadControl;


    public ThreadLauncherImpl(Stack<WordCounter> wordCounters, ThreadControl threadControl) {
        this.wordCounterStack = wordCounters;
        this.threadControl = threadControl;
    }

    @Override
    public void startTread() {


        while (!wordCounterStack.empty()) {

            WordCounter wordCounter = wordCounterStack.pop();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!threadControl.getFlag()){
                            wordCounter.startCounting();
                        }
                        System.exit(0);
                    } catch (IOException e) {
                        System.out.println("Sorry, file cannot be read");
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

}
