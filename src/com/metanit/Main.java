package com.metanit;


/*
15. Предыдущая задача, только теперь надо найти все файлы, имя которых (включая расширение) задано в виде маски со звездочками и вопросиками (например "input??.*").
Примечание: Для проверки соответствия имени файла шаблону можно воспользоваться функциями из проекта к лекции о рекурсии.
 */


import java.io.File;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    private static void printDir(File dir, String startsMask) {
        // Получаем подфайлы и каталоги
        File[] files=dir.listFiles();
        /*
                 1. Циклическая печать
                         Определите, является ли файл input??.*, распечатайте
         */
        for (File file : files) {
            if(file.isFile()){
                // Если это файл, оцениваем имя файла и выводим абсолютный путь
                if(file.getName().startsWith(startsMask)){
                    System.out.println ("Путь " + file.getAbsoluteFile ());
                }
            }else{
                // Не файл, а каталог. Продолжайте обход и вызовите метод printDir для формирования рекурсии
                printDir(file, startsMask);
            }
        }
    }

    public static void main(String[] args) {
        // Создаем объект File
        File dir=new File("C:\\Users\\GoldStump\\Documents\\Test_Task_12");
        // Вызов пользовательского метода печати
        Scanner input = new Scanner(System.in);
        String nameInput = input.nextLine();
        String[] answer = StrPatternDemo.match("input01.txt", nameInput);
        printDir(dir,  String.join("",answer));
    }
}