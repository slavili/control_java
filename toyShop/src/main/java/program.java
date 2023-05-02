import java.nio.file.Files;
import java.util.List;


public class program {
    public static void main(String[] args) throws Exception {

        CreateFile userDb =new CreateFile(".\\");

        System.out.printf(Integer.toString(userDb.getAutoIncrement()));
        System.out.println(" ");
        List<String> listToys = Files.readAllLines(userDb.getToys());

        for (String str: listToys) {
            System.out.println(str);
        }
    }
}
