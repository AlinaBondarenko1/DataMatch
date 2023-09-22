import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> referenceList = readFile("D:\\javaProjects\\DataMatch\\Date\\inno.txt");
        List<String> listToCompare = readFile("D:\\javaProjects\\DataMatch\\Date\\base.txt");

        if (referenceList == null || listToCompare == null) {
            System.out.println("Ошибка при чтении файлов.");
            return;
        }

        int referenceSize = referenceList.size();
        int listToCompareSize = listToCompare.size();

        Set<String> referenceSet = new HashSet<>(referenceList);

        System.out.println("Количество элементов из конструктора: " + referenceSize);
        System.out.println("Количество элементов из отчета: " + listToCompareSize);

        System.out.println("Совпадающие элементы:");
        int commonCount = 0;
        for (int i = 0; i < listToCompareSize; i++) {
            String element = listToCompare.get(i);
            if (referenceSet.contains(element)) {
                commonCount++;
                System.out.println(i + 1 + ". " + element + " = " + element);
            }
        }

        System.out.println("Количество совпадающих элементов: " + commonCount);
        System.out.println("Элемент есть в конструкторе , но нет в отчете:");
        int missingCount = 0;
        for (int i = 0; i < referenceSize; i++) {
            String element = referenceList.get(i);
            if (!listToCompare.contains(element)) {
                missingCount++;
                System.out.println(element);
            }
        }
        System.out.println("Количество элементов, отсутствующих в отчете: " + missingCount);

        System.out.println("Элементы, отсутствующие в отчете из конструктора:");
        int unmatchedCount = 0;
        for (int i = 0; i < listToCompareSize; i++) {
            String element = listToCompare.get(i);
            if (!referenceSet.contains(element)) {
                unmatchedCount++;
                System.out.println(i + 1 + ". " + element);
            }
        }
        System.out.println("Количество элементов, отсутствующих в отчете из конструктора: " + unmatchedCount);
    }


    private static List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}