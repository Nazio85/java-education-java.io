import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.sql.Time;
import java.util.*;

public class Main {
    private static BufferedReader reader;

    public static void main(String[] args) {
        String path = "src/file/firstFile.txt";
        solutionOne(path);
        solutionTwo();
        solutionThree();


    }

    private static void solutionOne(String path) {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solutionTwo() {
        ArrayList<FileInputStream> list = new ArrayList<>();
        try {
            list.add(new FileInputStream("src/file/one.txt"));
            list.add(new FileInputStream("src/file/two.txt"));
            list.add(new FileInputStream("src/file/three.txt"));
            list.add(new FileInputStream("src/file/four.txt"));
            list.add(new FileInputStream("src/file/five.txt"));

            Enumeration<FileInputStream> enumeration = Collections.enumeration(list);
            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(list));

            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("src/file/six.txt", true));

            long time = System.currentTimeMillis();
            int tmp = -1;

            // Способ первый ()
            while (enumeration.hasMoreElements()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(enumeration.nextElement()));
                while ((tmp = reader.read()) != -1) {
                    writer.write(tmp);
                }
            }


            //Второй способ
            while  ( (tmp = in.read()) != -1){
                writer.write(tmp);
            }

            //первый способ быстрей
            System.out.println(System.currentTimeMillis() - time);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solutionThree() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/file/firstFile.txt"));
            Scanner scanner = new Scanner(System.in);

            int count = 0;
            int tmp = -1;
            StringBuilder stringBuilder = new StringBuilder();


            long time = System.currentTimeMillis();
            endProgram:
            while ((tmp = reader.read()) != -1) {

                count++;
                stringBuilder.append((char)tmp);

                if (count == 1799) {
                    count = 0;
                    String str = "";
                    System.out.println(stringBuilder);
                    stringBuilder.setLength(0);

                    do {
                        long timeEnd = System.currentTimeMillis()-time;

                        System.out.println(""); // Для читабельности аналог <br>
                        System.out.println(""); // Для читабельности аналог <br>
                        System.out.println("Обработка первой страницы заняла " + timeEnd);
                        // Выполняется за 4 милисекунды (book.txt - 23.3 mb)
                        // Надеюсь я правильно понял задание
                        System.out.println("-------------------------   Страница закончилась   -------------------------");
                        System.out.println("--------------   Чтобы продолжить нажмите Y, END для выхода   --------------");
                        str = scanner.next();
                        if (str.equals("end")) {
                            break endProgram;
                        }
                    } while (!str.equalsIgnoreCase("y"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
