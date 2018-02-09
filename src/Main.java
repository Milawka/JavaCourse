import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String path = "e1.txt";
        String path2 = "e2.txt";

        HashMap<String, Integer> hashMap = new HashMap<>();
        Stack<WordCounter> wordCounterStack = new Stack<>();
        ThreadControl threadControl = new ThreadControl(false);
        CurrentType currentType = new CurrentType();

        try {

            System.out.println("Choose mode.");
            System.out.println("Press 1 to count all words");
            System.out.println("Press 2 to count words starting with 'ч' ");
            System.out.println("To change mode just enter one of above number.");

            Scanner sc = new Scanner(System.in);
                String mode = sc.nextLine();

                if (mode.equals("1")){
                    currentType.setWithCh(false);
                }

                if (mode.equals("2")){
                    currentType.setWithCh(true);
                }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( new FileInputStream(path), Charset.forName("UTF-8")));
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader( new FileInputStream(path2), Charset.forName("UTF-8")));

            FileWriter fileWriter = new FileWriterImpl(hashMap);

            WordCounter wc1 = (WordCounter) Proxy.newProxyInstance(WordCounter.class.getClassLoader(), new Class[]{WordCounter.class}, new Manager(bufferedReader, fileWriter, threadControl, currentType));
            WordCounter wc2 = (WordCounter) Proxy.newProxyInstance(WordCounter.class.getClassLoader(), new Class[]{WordCounter.class}, new Manager(bufferedReader2, fileWriter, threadControl, currentType));


            wordCounterStack.push(wc1);
            wordCounterStack.push(wc2);

            ThreadLauncher threadLauncher = new ThreadLauncherImpl(wordCounterStack, threadControl);


            threadLauncher.startTread();

            while (!threadControl.flag) {
                mode = sc.nextLine();

                if (mode.equals("1")){
                    currentType.setWithCh(false);
                }

                if (mode.equals("2")){
                    currentType.setWithCh(true);
                }
            }


        }
        catch (FileNotFoundException e) {
            System.out.println("Sorry , file not found.");
            e.printStackTrace();
        }

    }
}
