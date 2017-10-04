import java.io.*;
import java.util.*;

public class Main {
    final static String FILE_READ = "D:\\JavaProject\\Java_courses\\LinkedList\\FILE_READ.txt";
    final static String FILE_WRITE = "D:\\JavaProject\\Java_courses\\LinkedList\\FILE_WRITE.txt";

    public static void main(String[] args) {
        System.out.println("Select sort 1,2,3(is select 3 ,select word to sort) :");
        try{
            List<String> arrayList = returnLine(FILE_READ);
            Scanner in = new Scanner(System.in);
            int select = in.nextInt();
            switch (select) {
                case 1:
                    Comparator<String> compByString = (a, b) -> a.compareTo(b);
                    writeFile(FILE_WRITE, arrayList, compByString);
                    return;
                case 2:
                    Comparator<String> compByLength = (a, b) -> a.length() - b.length();
                    writeFile(FILE_WRITE, arrayList, compByLength);
                    return;
                case 3:
                    int i =in.nextInt();
                    if (in.next() != null) {
                        throw new InputMismatchException();
                    }
                    Comparator<String> compByWord = new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            String a = o1.split(" ")[i];
                            String b = o2.split(" ")[i];
                            return a.compareTo(b);
                        }
                    };
                    writeFile(FILE_WRITE, arrayList, compByWord);
                    break;
                default:
                    throw new IllegalArgumentException("IllegalArgument" );
            }
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException : " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException : " + e.getMessage());
        }  catch (IOException e) {
            System.err.println("IOException : " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException : " + e.getMessage());
        }
    }

    public static List<String> returnLine(String file) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                 list.add(line);
            }
        }
        return list;
    }

    public static void writeFile(String file, List<String> list, Comparator<String> comparator ) throws IOException {
        SortedMap<String,Integer> map =
                new TreeMap<String,Integer>(comparator);
        for (String temp : list) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        try (FileWriter fw =new FileWriter(file))
        {
            for(Map.Entry<String,Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                while (0 < count) {
                    fw.write(entry.getKey()+" "+entry.getValue()+"\n");
                    count--;
                }
            }
        }
    }

    public Map<Object, Object> returnMapByUniqueValues(Map<Object,Object> map)  {
        Map<Object,Object> result = new HashMap<>();
        for (Map.Entry<Object,Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (!result.containsValue(value)){
                result.put(entry.getKey(),value);
            }
        }
        return result;
    }
}
