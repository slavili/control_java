import java.util.List;
import java.util.Scanner;

public class program {
    public static void main(String[] args) throws Exception {

        rwDb toyDataBase = new rwDb("D:\\toys\\");
        System.out.println("help - показать команды управления");
        System.out.println("insert - добавить новую игрушку");
        System.out.println("delete - удалить игрушку");
        System.out.println("update - обновление игрушки");
        System.out.println("select - показать список всех игрушек");
        System.out.println("play - розыгрыш игрушки с одновременной выдачей (в файл)");
        System.out.println("q - выход");

        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Выберите действие: ");
            String cmd = in.nextLine();
            if (cmd.equals("select")) {
                List<String[]> tempArray = toyDataBase.getAllProduct(toyDataBase.getToysLink());
                System.out.println("Кол-во разновидностей игрушек на складе: " + tempArray.size());
                for (String[] userArr: tempArray) {
                    System.out.println("id: "+userArr[0]+". название: "+userArr[1]+". кол-во на складе: "+userArr[2]+". процент выигрышности: "+userArr[3] + "%");
                }
            } else if (cmd.equals("insert")){

                String[] arrayForAdd = new String[4];
                int newId = toyDataBase.getAutoIncrement();
                arrayForAdd[0] = Integer.toString(newId);
                System.out.print("Наберите имя игрушки: ");
                String name = in.nextLine();
                arrayForAdd[1] = name;
                System.out.print("Кол-во штук на складе: ");
                String number = in.nextLine();
                arrayForAdd[2] = number;
                System.out.print("Процент выигрошности: ");
                String procent = in.nextLine();
                arrayForAdd[3] = procent;

                toyDataBase.addProduct(toyDataBase.getToysLink(), arrayForAdd);
                toyDataBase.setAutoIncrement(newId);
                System.out.println("Новая игрушка добавлена");

            } else if (cmd.equals("delete")) {
                Boolean isFlag = true;
                List<String[]> tempArray = toyDataBase.getAllProduct(toyDataBase.getToysLink());
                System.out.print("Введите id игрушки для удаления: ");
                String del = in.nextLine();

                for(int i = 0, j = tempArray.size(); i < j; i++){
                    if (tempArray.get(i)[0].equals(del)){
                        //System.out.println(tempArray.size());
                        tempArray.remove(i);
                        toyDataBase.modificateProduct(toyDataBase.getToysLink(), tempArray);
                        System.out.println("Игрушка удалена!");
                        isFlag = false;
                        break;
                    }
                }
                if (isFlag) {
                    System.out.println("Игрушки с id = " + del + " не существует.");
                }
            } else if (cmd.equals("update")) {
                Boolean isFlag = true;
                List<String[]> tempArray = toyDataBase.getAllProduct(toyDataBase.getToysLink());
                System.out.print("Введите id игрушки для модификации: ");
                String upd = in.nextLine();

                for(int i = 0, j = tempArray.size(); i < j; i++){

                    if (tempArray.get(i)[0].equals(upd)){
                        System.out.println("id: "+tempArray.get(i)[0]+". название: "+tempArray.get(i)[1]+". кол-во на складе: "+tempArray.get(i)[2]+". процент выигрышности: "+tempArray.get(i)[3] + "%");
                        System.out.println("В следующих пункта, если не писать новое имя и т.д., а просто нажать Enter, то имя не изменится");
                        System.out.print("Наберите новое имя игрушки: ");
                        String name = in.nextLine();
                        if(!name.equals(""))
                            tempArray.get(i)[1] = name;
                        System.out.print("Кол-во на складе: ");
                        String cnt = in.nextLine();
                        if(!cnt.equals(""))
                            tempArray.get(i)[2] = cnt;
                        System.out.print("Процент выигрышности: ");
                        String procent = in.nextLine();
                        if(!procent.equals(""))
                            tempArray.get(i)[3] = procent;
                        //System.out.println("id: "+tempArray.get(i)[0]+". название: "+tempArray.get(i)[1]+". кол-во на складе: "+tempArray.get(i)[2]+". процент выигрышности: "+tempArray.get(i)[3] + "%");
                        toyDataBase.modificateProduct(toyDataBase.getToysLink(), tempArray);
                        System.out.println("Игрушка обновлена");
                        isFlag = false;
                        break;
                    }
                }
                if (isFlag){
                    System.out.println("Игрушки с id = "+upd+" не существует.");
                }

            } else if (cmd.equals("play")) {
                toyDataBase.playBonusToy(toyDataBase.getAllProduct(toyDataBase.getToysLink()));

            } else if (cmd.equals("help")) {
                System.out.println("help - показать команды управления");
                System.out.println("insert - добавить новую игрушку");
                System.out.println("delete - удалить игрушку");
                System.out.println("update - обновление игрушки");
                System.out.println("select - показать список всех игрушек");
                System.out.println("play - розыгрыш игрушки с одновременной выдачей (в файл)");
                System.out.println("q - выход");
            }

            else if (cmd.equals("q")) {
                System.out.println("Работа с программой завершена!");
                break;
            } else {
                System.out.println("Неизвестная комманда!");
            }
        }
    }
}
