import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class rwDb extends CreateFile{

    private List<String[]> bonusToyTemp;

    public rwDb(String pathToFile) throws Exception {
        this.bonusToyTemp = new ArrayList<>();
        this.toys = Path.of(pathToFile+"toy.csv");
        this.bonusToy = Path.of(pathToFile+"bonusToy.csv");
        this.autoIncrement = Path.of(pathToFile+"AI.txt");
        this.createDbFile(this.toys);
        this.createDbFile(this.bonusToy);
        this.createDbFile(this.autoIncrement);
        this.setAutoIncrement();

    }
    @Override
    protected void createDbFile(Path dbFile) throws Exception {
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
    @Override
    public Path getToysLink(){
        return this.toys;
    }
    @Override
    public Path getBonusToyLink(){
        return this.bonusToy;
    }

    @Override
    protected void setAutoIncrement(int valueAi) throws Exception {
        Files.writeString(this.autoIncrement, Integer.toString(valueAi+1));
    }
    @Override
    protected void setAutoIncrement() throws IOException {
        if(Files.size(this.autoIncrement) == 0){
            Files.writeString(this.autoIncrement, Integer.toString(1));
        }
    }
    @Override
    public int getAutoIncrement() throws Exception {
        return Integer.parseInt(Files.readString(this.autoIncrement));
    }

    public List<String[]> getAllProduct(Path pathToDb) throws IOException {
        List<String> listToys = Files.readAllLines(pathToDb);
        List<String[]> resultList = new ArrayList<>();

        for (String str: listToys) {
            resultList.add(str.split(";"));
        }

        return resultList;
    }
    @Override
    public void addProduct(Path pathToFile, String[] userData) throws IOException {
        String userString="";
        if(Files.size(pathToFile) == 0){
            userString = String.join(";",userData);
        }else
            userString = "\n"+String.join(";",userData);

        Files.writeString(pathToFile, userString, StandardOpenOption.APPEND);
    }

    @Override
    public void modificateProduct(Path pathToFile, List<String[]> tempArray) throws IOException {
        String userString = "";

        for (int i = 0, j = tempArray.size(); i < j; i++) {
            if (i == 0){
                userString+=String.join(";",tempArray.get(i));
            }else {
                userString+="\n"+String.join(";",tempArray.get(i));
            }
        }

        Files.writeString(pathToFile, userString, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * playBonusToy - розыгрышь игрушки,
     * берётся одна игрушка случайным образом выбранная из поступающего массива.
     * @param userToys
     */
    public void playBonusToy(List<String[]> userToys) throws IOException {
        Random tempR = new Random();
        Integer tempItem;

        for(int j = 0; userToys.size() > 0 && j < userToys.size();) {
            if (Integer.parseInt(userToys.get(j)[2]) < 1)
            {
                userToys.remove(j);
                j = 0;
            } else j++;
        }
        if (userToys.size() > 0){
            tempItem = tempR.nextInt(userToys.size());
            bonusToyTemp.add(userToys.get(tempItem));
            System.out.println("Разыгранные игрушки:");
            for (int i = 0, j = bonusToyTemp.size(); i < j; i++) {
                System.out.println(String.join(";",bonusToyTemp.get(i)));
            }
            this.getBonusToy(this.getAllProduct(this.toys));
        }
        else
            System.out.println("Отсуствуют игрушки для розыгрыша");
    }

    private void getBonusToy(List<String[]> allProduct) throws IOException {
        String[] userString = new String[2];

        for (int i = 0, j = bonusToyTemp.size(); i < j; i++) {

            userString[0] = bonusToyTemp.get(i)[0];
            userString[1] = bonusToyTemp.get(i)[1];

            addProduct(this.bonusToy, userString);
            bonusToyTemp.remove(i);

            for (int k = 0, z = allProduct.size(); k < z; k++) {
                if(allProduct.get(k)[0].equals(userString[0])){
                    String[] tempItem = allProduct.get(k);
                    tempItem[2] = Integer.toString(Integer.parseInt(tempItem[2]) - 1);
                    allProduct.set(k, tempItem);
                    break;
                }
            }

            modificateProduct(this.getToysLink(), allProduct);

        }

    }

}
