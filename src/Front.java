import Staff.StaffList;

import java.util.Scanner;

import static java.awt.SystemColor.menu;


public class Front {
    private static final String PAGE =
            "\n*****************************************************************" +
                    "\n*****************************************************************" +
                    "\n*****************************************************************\n";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main() {
        StaffList.readEmployeesFromFile();
        do {
            menu();
        } while (true);
    }

    //стримы в папке лямбда в файле стрим
    public static void menu() {
        System.out.println(PAGE);
        System.out.println(
                "Главная страница. Что вы хотите сделать?" +
                        "\n1)Принять нового сотрудника" + //add to file
                        "\n2)Уволить сотрудника" + //++
                        "\n3)Изменить информацию о сотруднике" + //стрим фильтр вытащить сотрудника и изменить проверить изменился ли
                        "\n4)Поиск сотрудника(ФИО, Должность, Отдел, ФИО начальника)" + //стрим
                        "\n5)Список всех сотрудников" +
                        "\n6)Структура организации(Инфо об отделах, ФИО начальников)" + //стримы всех сотрудников, вытаскиваете отдел с боссом в мап(отдел, босс) и печать мапа
                        "\n7)Средняя зп по предприятию" + //стрим всех сотрудников, вытаскиваете зп, суммируете зп и делите на количество всех сотрудников
                        "\n8)ТОП-10 самых дорогих сотрудников" + //сортировка поубыванию зп
                        "\n9)ТОП-10 самых преданных сотрудников" +
                        "\n0)Завершение работы программы"); //сортировка по дате приема на работу
        int action = SCANNER.nextInt();
        switch (action) {
            case 1:
                StaffList.hireNewEmployee();
                break;
            case 2:
                StaffList.dismissMenu();
                break;
            case 3:
                StaffList.searchAndReplace();
                break;
            case 4:
                StaffList.searchEmployees();
                break;
            case 5:
                StaffList.showEmployees();
                break;
            case 6:
                StaffList.organizationStructure();
                break;
            case 7:
                StaffList.calcAverageSalary();
                break;
            case 8:
                StaffList.tenMostExpensive();
                break;
            case 9:
                StaffList.tenMostDevoted();
                break;
            case 0:
                System.out.println("Завершение работы. Для возврата перезапустите программу");
                break;
            default:
                System.out.println("Ошибка, нет такого варианта. Попробуйте ещё раз");
                break;
        }
    }
}