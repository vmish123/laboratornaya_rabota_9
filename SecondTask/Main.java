package SecondTask;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) throws IOException {

        String name = "Victor";
        String surname = "Mishura";

        // Определение корневой директории
        Path basePath = Path.of("").toAbsolutePath();

        // a. Создайте директорию <surname>
        System.out.println("a. Creating directory: " + basePath.resolve(surname));
        Files.createDirectory(basePath.resolve(surname));

        // b. Внутри директории <surname> создайте файл с названием <name>
        System.out.println("b. Creating file: " + basePath.resolve(surname).resolve(name));
        Path FileName = basePath.resolve(surname).resolve(name);
        Files.createFile(FileName);

        // c. Внутри вашей директории <surname> создайте вложенные директории dir1/ dir2/ dir3 и скопируйте туда ваш файл <name>
        System.out.println("c. Creating directories: ");

        // Создание директорий
        System.out.println(basePath.resolve(surname).resolve("dir1"));
        Files.createDirectory(basePath.resolve(surname).resolve("dir1"));
        System.out.println(basePath.resolve(surname).resolve("dir2"));
        Files.createDirectory(basePath.resolve(surname).resolve("dir2"));
        System.out.println(basePath.resolve(surname).resolve("dir3"));
        Files.createDirectory(basePath.resolve(surname).resolve("dir3"));

        System.out.println("Copying file into created directories:");

        // Копирование файла
        Files.copy(FileName, basePath.resolve(surname).resolve("dir1").resolve(FileName.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println(FileName.getFileName() + " copied to dir1");
        Files.copy(FileName, basePath.resolve(surname).resolve("dir2").resolve(FileName.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println(FileName.getFileName() + " copied to dir2");
        Files.copy(FileName, basePath.resolve(surname).resolve("dir3").resolve(FileName.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println(FileName.getFileName() + " copied to dir3");

        // d. Внутри директории dir1 создайте файл file1
        System.out.println("d. Creating file: " + basePath.resolve(surname).resolve("dir1").resolve("file1"));
        Files.createFile(basePath.resolve(surname).resolve("dir1").resolve("file1"));

        // e. Внутри директории dir2 создайте файл file2
        System.out.println("e. Creating file: " + basePath.resolve(surname).resolve("dir2").resolve("file2"));
        Files.createFile(basePath.resolve(surname).resolve("dir2").resolve("file2"));

        // f. Выполните рекурсивный обход вашей директории <surname> и выведите названия всех файлов и директорий, которые там есть.
        // При выводе перед именем файла поставьте пометку «F», перед именем директории – «D».
        System.out.println("f. Recursive directory traversal:");
        Files.walk(basePath.resolve(surname)).forEach(path -> {
            String prefix = Files.isDirectory(path) ? "D" : "F";
            Path relative = basePath.relativize(path);
            System.out.println(" " + prefix + ": " + relative);
        });

        // g. Удалите директорию dir1 со всем ее содержимым
        System.out.println("g. Deleting directory dir1:");
        Files.walkFileTree(basePath.resolve(surname).resolve("dir1"), new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrib) throws IOException {
                // Рекурсивное удаление файлов в директории
                System.out.println("Deleting file: " + file);
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                // Удаление директории
                System.out.println("Deleting directory: " + dir);
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("Directory deletion completed");
        System.out.println("Contents of the directory Mishura");
        // Проверка изменённого содержимого директории
        Files.walk(basePath.resolve(surname)).forEach(path -> {
            String prefix = Files.isDirectory(path) ? "D" : "F";
            Path relative = basePath.relativize(path);
            System.out.println(" " + prefix + ": " + relative);
        });
    }
}
