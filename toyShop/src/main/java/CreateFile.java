import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс для создания файлов базы данных
 */
public class CreateFile {
    private  Path toys;
    private Path autoIncrement;
    public CreateFile(String pathToFile) throws Exception {
        this.toys = Path.of(pathToFile+"toy.csv");
        this.autoIncrement = Path.of(pathToFile+"AI.txt");
        this.createDbFile(this.toys);
        this.createDbFile(this.autoIncrement);
        if(Files.size(this.autoIncrement) == 0){
            this.setAutoIncrement(0);
        }
    }

    public Path getToys(){
        return this.toys;
    }

    public void setAutoIncrement(int valueAi) throws Exception {
        Files.writeString(this.autoIncrement, Integer.toString(valueAi));
    }

    public int getAutoIncrement() throws Exception {
        return Integer.parseInt(Files.readString(this.autoIncrement));
    }

    /**
     * Метод для создания файлов базы данных по указанному пути
     * @param dbFile
     * @throws Exception
     */
    private void createDbFile(Path dbFile) throws Exception {
        if (Files.exists(dbFile) == false) {
            Files.createFile(dbFile);
            if (Files.isRegularFile(dbFile) == true) {
                System.out.println("Файл \"" + dbFile + "\" успешно создан!");
            }
            else{
                System.out.println("Файл \"" + dbFile + "\" НЕ создан!");
            }
        }
    }

}
