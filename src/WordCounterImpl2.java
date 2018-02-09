import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WordCounterImpl2 implements WordCounter{

        FileWriter fileWriter;
        BufferedReader bufferedReader;
        ArrayList<Character> legal = new ArrayList<>();
        ArrayList<Character> letter = new ArrayList<>();
        StringBuilder wordBuffer = new StringBuilder();
        ThreadControl threadControl;

        @Override
        public void init(FileWriter fileWriter, BufferedReader bufferedReader, ThreadControl threadControl) {
            this.fileWriter = fileWriter;
            this.bufferedReader = bufferedReader;
            this.threadControl = threadControl;

            for (char i = 'А'; i <= 'я'; i++) {
                letter.add(i);
            }
            letter.add('ё');

            legal.addAll(letter);
            legal.add('1');
            legal.add('2');
            legal.add('3');
            legal.add('4');
            legal.add('5');
            legal.add('6');
            legal.add('7');
            legal.add('8');
            legal.add('9');
            legal.add('0');
            legal.add('.');
            legal.add(',');
            legal.add('\n');
            legal.add(' ');
            legal.add('-');
            legal.add('!');
            legal.add('?');
            legal.add('"');
            legal.add(';');
            legal.add(':');
            legal.add('(');
            legal.add(')');
            legal.add('–');
            legal.add('«');
            legal.add('»');
        }

        @Override
        public void startCounting() throws IOException {
            int temp;
            char symbol;

            temp = bufferedReader.read();


            symbol = (char) temp;

            if (symbol == '\uFFFF'){
                threadControl.setFlag(true);
                return;
            }
            if (legal.contains(symbol)) {

                if (letter.contains(symbol) && (symbol == 'ч' || symbol == 'Ч')) {
                    wordBuffer = new StringBuilder();
                    wordBuffer.append(symbol);

                    while ((temp = bufferedReader.read()) != -1) {
                        symbol = (char) temp;
                        if (symbol == '\uFFFF'){
                            threadControl.setFlag(true);
                            break;
                        }
                        if (legal.contains(symbol)) {

                            if (letter.contains(symbol)) {
                                wordBuffer.append(symbol);
                            } else {
                                break;
                            }
                        } else {

                            System.out.println("Illegal char found: " + (char)temp);
                            threadControl.setFlag(true);
                            return;
                        }
                    }
                    fileWriter.addWord(wordBuffer.toString());
                    try {
                        TimeUnit.MILLISECONDS.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            } else {
                System.out.println("Illegal char found: " + (char)temp);
                threadControl.setFlag(true);
                return;
            }


        }

        @Override
        public void setThreadControl(ThreadControl threadControl) {
            this.threadControl = threadControl;
        }
    }


