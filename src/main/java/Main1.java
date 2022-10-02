import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class Main1 {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        List<String> listOfStrings = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader("/home/alexeyleping/IdeaProjects/Sinteka/src/main/resources/file.txt"));
        String line = bf.readLine();
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }
        bf.close();
        String[] array = listOfStrings.toArray(new String[0]);
        int i = Integer.parseInt(array[0]);
        int j = Integer.parseInt(array[i+1]);
        String[] array1 = new String[i];
        String[] array2 = new String[j];
        System.out.println(array1.length);
        System.out.println(array2.length);
        for(int x = 1; x <= i; x++){
            array1[x-1] = array[x];
        }
        for(int y = 0; y < j; y++){
            array2[y] = array[i+j+y-1];
        }
        for(int p = 0; p < 4; p++){
            System.out.println(array1[p]);
        }
        for(int p = 0; p < 2; p++){
            System.out.println(array2[p]);
        }
        for(int z = 0; z < i; z++){
            for(int a = 0; a < j; a++){
                System.out.println(array1[z] + " " + array2[a]);
                double similarity = findSimilarity(array1[z], array2[a]);
                System.out.println(similarity);
                if (similarity > 0.4){
                    hashMap.put(array1[z], array2[a]);
                }
            }
        }
        hashMap.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        final String outputFilePath = "/home/alexeyleping/IdeaProjects/Sinteka/src/main/resources/output.txt";
        File file2 = new File(outputFilePath);
        BufferedWriter bf2 = null;
        try {
            bf2 = new BufferedWriter(new FileWriter(file2));
            for (Map.Entry<String, String> entry :
                    hashMap.entrySet()) {
                bf2.write(entry.getKey() + ":"
                        + entry.getValue());
                bf2.newLine();
            }
            bf2.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bf.close();
            }
            catch (Exception e) {
            }
        }
    }
    public static double findSimilarity(String x, String y) {
        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            return (maxLength - StringUtils.getLevenshteinDistance(x, y)) / maxLength;
        }
        return 1.0;
    }
}
