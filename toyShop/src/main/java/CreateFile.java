import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс для создания файлов базы данных и работы с данными
 */
abstract class CreateFile {
    protected  Path toys;
    protected Path autoIncrement;
    protected Path bonusToy;
    public CreateFile(){
    }

    /**
     * getToysLink - получение ссылки на файл toy.csv - склад игрушек
     * @return
     */
    abstract Path getToysLink();

    /**
     * getBonusToyLink - получение ссылки на файл bonusToy.csv - выигранные игрушки
     * @return
     */
    abstract Path getBonusToyLink();

    /**
     * setAutoIncrement - с передаваемы параметром. Увеличивает значение в файле AI.txt на единицу.
     * @param valueAi
     * @throws Exception
     */
    abstract void setAutoIncrement(int valueAi) throws Exception;

    /**
     * setAutoIncrement - без параметра, используется в конструкторе при создании файла AI.txt
     * и устанавливает значение равным единице.
     * @throws IOException
     */
    abstract void setAutoIncrement() throws IOException;

    /**
     *getAutoIncrement - получение значения для ID из файла AI.txt
     * @return
     * @throws Exception
     */
    abstract int getAutoIncrement() throws Exception;

    /**
     * Метод для создания файлов базы данных по указанному пути
     * @param dbFile
     * @throws Exception
     */
    abstract void createDbFile(Path dbFile) throws Exception;

    /**
     * addProduct - добавляет игрушку в файл csv в формате: id;name;count;percent
     * @param pathToFile
     * @param userData
     * @throws IOException
     */
    abstract void addProduct(Path pathToFile, String[] userData) throws IOException;

    /**
     * modificateProduct - используется для обновления игрушки(кроме поля id) и удаления.
     * @param pathToFile
     * @param tempArray
     * @throws IOException
     */
    abstract void modificateProduct(Path pathToFile, List<String[]> tempArray) throws IOException;

}
