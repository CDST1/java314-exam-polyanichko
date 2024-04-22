package Staff;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StaffList {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static List<Employee> employees = new ArrayList<>();
    private static final String FILE_NAME = "Staff.txt";
    private static final Scanner SCANNER = new Scanner(System.in);


    //  1) Найм
    public static void hireNewEmployee() {
        System.out.println("Введите данные нового сотрудника: ");
        System.out.print("ФИО: ");
        String name = SCANNER.nextLine();
        System.out.print("Дата рождения (гггг-мм-дд): ");
        String birthDateStr = SCANNER.nextLine();
        LocalDate birthDate = LocalDate.parse(birthDateStr);
        System.out.print("Пол: ");
        String genderScanner = SCANNER.nextLine();
        Gender gender = Gender.valueOf(genderScanner);
        System.out.print("Номер телефона: ");
        String phoneNumber = SCANNER.nextLine();
        System.out.print("Должность: ");
        String position = SCANNER.nextLine();
        System.out.print("Отдел: ");
        String department = SCANNER.nextLine();
        System.out.print("ФИО начальника: ");
        String manager = SCANNER.nextLine();
        System.out.print("Дата приема на работу (гггг-мм-дд): ");
        String hireDateStr = SCANNER.nextLine();
        LocalDate hireDate = LocalDate.parse(hireDateStr);
        System.out.print("Зарплата: ");
        int salary = Integer.parseInt(SCANNER.nextLine());
        System.out.print("id: ");
        int id = Integer.parseInt(SCANNER.nextLine());


        addEmployee(new Employee(name, birthDate, gender, phoneNumber, position, department, manager, hireDate, salary, id));
        writeEmployeesToFile();
        System.out.println("Сотрудник добавлен. Можете его найти по id " + id);
    }

    public static void writeEmployeesToFile() {

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(FILE_NAME), StandardOpenOption.TRUNCATE_EXISTING)) {   //!!!!
            for (Employee employee : employees) {
                bufferedWriter.write(employee.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // запись в файл
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
        //добавление в файл
    }


    //  2)удаление/увольнение сотрудника
    public static void dismissMenu() {
        System.out.println("Как вы хотите выследить сотрудника:" +
                "\n1 - по ФИО" +
                "\n2 - по id");
        int action = Integer.parseInt(SCANNER.nextLine());
        switch (action) {
            case 1:
                System.out.print("Введите ФИО: ");
                String name = SCANNER.nextLine();
                employees = employees.stream().filter(employee ->
                        !employee.getName().equals(name)).collect(Collectors.toList());
                System.out.println("Сотрудник уволен. Вам с этим жить");
                break;
            case 2:
                System.out.println("Введите id: ");
                int id = Integer.parseInt(SCANNER.nextLine());
                employees = employees.stream().filter(employee ->
                        employee.getId() != id).collect(Collectors.toList());
                System.out.println("Сотрудник уволен. Вам с этим жить");
                break;
            default:
                System.out.println("Ошибка. Попробуйте ещё раз");
                dismissMenu();
        }

        writeEmployeesToFile();
    }

//  3) изменение информации

    public static void searchAndReplace() {

        System.out.println("по какому параметру вы хотите найти сотрудника?" +
                "\n1 - по ФИО" +
                "\n2 - по дате рождения" +
                "\n3 - по номеру телефона" +
                "\n4 - по дате приёма" +
                "\n5 - по размеру з/п" +
                "\n6 - по id" +
                "\n7 - назад в главное меню");
        int action = Integer.parseInt(SCANNER.nextLine());
        List<Employee> filteredEmployees;
        switch (action) {
            case 1:
                System.out.print("Введите ФИО: ");
                String name = SCANNER.nextLine();
                filteredEmployees = employees.stream().filter(employee ->
                                employee.getName().equalsIgnoreCase(name))
                        .collect(Collectors.toList());

                break;
            case 2:
                System.out.print("Введите дату рождения(yyyy-MM-dd): ");
                String birthConsole = SCANNER.nextLine();
                LocalDate birthDate = LocalDate.parse(birthConsole, formatter);
                filteredEmployees = employees.stream().filter(employee -> employee.getBirthDate().equals(birthDate))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.println("Введите номер телефона: ");
                String phoneNumber = SCANNER.nextLine();
                filteredEmployees = employees.stream().filter(employee ->
                                employee.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                        .collect(Collectors.toList());

                break;
            case 4:
                System.out.println("Введите дату приёма(yyyy-MM-dd): ");
                String newHireDate = SCANNER.nextLine();
                LocalDate hireDate = LocalDate.parse(newHireDate, formatter);
                filteredEmployees = employees.stream().filter(employee -> employee.getHireDate().equals(hireDate))
                        .collect(Collectors.toList());
                break;
            case 5:
                System.out.println("Введите з/п: ");
                int salary = Integer.parseInt(SCANNER.nextLine());
                filteredEmployees = employees.stream().filter(employee ->
                                employee.getSalary() == salary)
                        .collect(Collectors.toList());
                break;
            case 6:
                System.out.println("Введите id: ");
                int id = Integer.parseInt(SCANNER.nextLine());
                filteredEmployees = employees.stream().filter(employee ->
                                employee.getId() == id)
                        .collect(Collectors.toList());
                break;
            case 7:

            default:
                System.out.println("Ошибка. Недопустимый вариант");
                return;
        }
        if (filteredEmployees.isEmpty()) {
            System.out.println("Список пуст");
        }

        if (filteredEmployees.size() > 1) {
            System.out.println("Найдено несколько сотрудников. Выберите: ");
            for (int i = 0; i < filteredEmployees.size(); i++) {
                System.out.println((i + 1) + " - " + filteredEmployees.get(i));
            }
            try {
                int selectAction = Integer.parseInt(SCANNER.nextLine());
                if (selectAction > 0 && selectAction < filteredEmployees.size()) {
                    Employee employee = filteredEmployees.get(selectAction - 1);
                    replaceEmployee(employee);
                }
            } catch (NumberFormatException ex) {
                System.out.println("Ошибка");
            }
        } else {
            Employee employee = filteredEmployees.get(0);
            System.out.println("Сотрудник найден " + employee);
            replaceEmployee(employee);
        }
    }

    public static void replaceEmployee(Employee employee) {
        System.out.println("Что меняем: " +
                "\n1 - ФИО" +
                "\n2 - Дату рождения" +
                "\n3 - Гендер" +
                "\n4 - Номер телефона" +
                "\n5 - Должность" +
                "\n6 - Отдел" +
                "\n7 - Руководителя отдела" +
                "\n8 - Дату трудоустройства" +
                "\n9 - Ставку" +
                "\n10 - id");
        int actionReplacement = Integer.parseInt(SCANNER.nextLine());
        switch (actionReplacement) {
            case 1:
                System.out.print("Новое имя: ");
                String newName = SCANNER.nextLine();
                employee.setName(newName);
                System.out.println("Информация изменена");
                break;
            case 2:
                System.out.print("Новая дата рождения (yyyy-MM-dd): ");
                String newBirthDate = SCANNER.nextLine();
                LocalDate birthDate = LocalDate.parse(newBirthDate, formatter);
                employee.setBirthDate(birthDate);
                System.out.println("Информация изменена");

                break;
            case 3:
                System.out.print("Новый пол: ");
                String newGender = SCANNER.nextLine();
                Gender gender = Gender.valueOf(newGender);
                employee.setGender(gender);
                System.out.println("Информация изменена");

                break;
            case 4:
                System.out.print("Новый номер телефона: ");
                String newPhoneNumber = SCANNER.nextLine();
                employee.setPhoneNumber(newPhoneNumber);
                System.out.println("Информация изменена");

                break;
            case 5:
                System.out.print("Новая должность: ");
                String newPost = SCANNER.nextLine();
                employee.setPost(newPost);
                System.out.println("Информация изменена");

                break;
            case 6:
                System.out.print("Новый отдел: ");
                String newDepartment = SCANNER.nextLine();
                employee.setDepartment(newDepartment);
                System.out.println("Информация изменена");

                break;
            case 7:
                System.out.print("Новый руководитель: ");
                String newManager = SCANNER.nextLine();
                employee.setManager(newManager);
                System.out.println("Информация изменена");

                break;
            case 8:
                System.out.print("Новая дата трудоустройства (yyyy-MM-dd): ");
                String newHireDate = SCANNER.nextLine();
                LocalDate hireDate = LocalDate.parse(newHireDate, formatter);
                employee.setHireDate(hireDate);
                System.out.println("Информация изменена");

                break;
            case 9:
                System.out.print("Новая ставка: ");
                int newSalary = Integer.parseInt(SCANNER.nextLine());
                employee.setSalary(newSalary);
                System.out.println("Информация изменена");

                break;
            case 10:
                System.out.println("Новый id: ");
                int newId = Integer.parseInt(SCANNER.nextLine());
                employee.setId(newId);
                System.out.println("Информация изменена");

                break;
            default:
                System.out.println("Ошибка. Возврат");
                return;
        }

        writeEmployeesToFile();
        //вызвать созданный метод для перезаписи файла staff (+)
    }


//  4) Поиск сотрудника

    public static void searchEmployees() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("по какому параметру вы хотите найти сотрудника?" +
                "\n1 - по ФИО" +
                "\n2 - по дате рождения" +
                "\n3 - по номеру телефона" +
                "\n4 - по дате приёма" +
                "\n5 - по размеру з/п" +
                "\n6 - по id");
        int action = Integer.parseInt(SCANNER.nextLine());
        List<Employee> searchedEmployees;
        switch (action) {
            case 1:
                System.out.print("Введите ФИО: ");
                String name = SCANNER.nextLine();
                searchedEmployees = employees.stream().filter(employee ->
                                employee.getName().equalsIgnoreCase(name))
                        .collect(Collectors.toList());

                break;
            case 2:
                System.out.print("Введите дату рождения(yyyy-MM-dd): ");
                String birthConsole = SCANNER.nextLine();
                LocalDate birthDate = LocalDate.parse(birthConsole, formatter);
                searchedEmployees = employees.stream().filter(employee -> employee.getBirthDate().equals(birthDate))
                        .collect(Collectors.toList());
                break;
            case 3:
                System.out.println("Введите номер телефона: ");
                String phoneNumber = SCANNER.nextLine();
                searchedEmployees = employees.stream().filter(employee ->
                                employee.getPhoneNumber().equalsIgnoreCase(phoneNumber))
                        .collect(Collectors.toList());

                break;
            case 4:
                System.out.println("Введите дату приёма(yyyy-MM-dd): ");
                String newHireDate = SCANNER.nextLine();
                LocalDate hireDate = LocalDate.parse(newHireDate, formatter);
                searchedEmployees = employees.stream().filter(employee -> employee.getHireDate().equals(hireDate))
                        .collect(Collectors.toList());
                break;
            case 5:
                System.out.println("Введите з/п: ");
                int salary = Integer.parseInt(SCANNER.nextLine());
                searchedEmployees = employees.stream().filter(employee ->
                                employee.getSalary() == salary)
                        .collect(Collectors.toList());
                break;
            case 6:
                System.out.println("Введите id: ");
                int id = Integer.parseInt(SCANNER.nextLine());
                searchedEmployees = employees.stream().filter(employee ->
                                employee.getId() == id)
                        .collect(Collectors.toList());
                break;
            default:
                System.out.println("Ошибка. Недопустимый вариант");
                return;

        }
        if (searchedEmployees.isEmpty()) {
            System.out.println("Нет сотрудников с такими данными");
        } else {
            System.out.println("Вот сотрудники, соответствующие вашему запросу: ");
            searchedEmployees.forEach(System.out::println);
        }

    }


    //  5) реализация чтения и вывода
    public static void showEmployees() {

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static void readEmployeesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Employee employee = parseEmployee(line);
                employees.add(employee);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//  6)стурктура организации

    public static void organizationStructure() {

        Map<String, String> departmentAndManager = new HashMap<>();

        for (Employee employee : employees) {
            String department = employee.getDepartment();
            String manager = employee.getManager();

            if (departmentAndManager.containsKey(department)) {
                continue;
            }
            if (departmentAndManager.containsKey(manager)) {
                continue;
            }

            departmentAndManager.put(department, manager);
        }

        System.out.println("Информация об отделах и начальниках: ");
        for (Map.Entry<String, String> entry : departmentAndManager.entrySet()) {
            System.out.println("Отдел: " + entry.getKey() + ", Менеджер: " + entry.getValue());
        }

    }


    //  7)средняя зп
    public static void calcAverageSalary() {

        double averageSalary = employees.stream().mapToInt(Employee::getSalary)
                .average().orElse(0);
        System.out.println("Средняя з/п по предприятию: " + averageSalary);

    }

//  8)топ 10 самых дорогих

    public static void tenMostExpensive() {

        Map<Integer, Employee> topExpensive = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary)
                        .thenComparing(Employee::getId).reversed())
                .limit(10)
                .collect(Collectors.toMap(Employee::getSalary, Function.identity(), (existing, replacement) ->
                        Math.min(existing.getId(), replacement.getId()) == existing.getId() ? replacement : existing, LinkedHashMap::new));
        System.out.println("ТОП-10 самых дорогих сотрудников: ");
        topExpensive.values().forEach(System.out::println);

    }

    //  9)топ 10 самых преданных
    public static void tenMostDevoted() {
        List<Employee> topDevoted = employees.stream()
                .sorted(Comparator.comparing(Employee::getHireDate))
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("ТОП-10 самых преданных сотрудников: ");
        topDevoted.forEach(System.out::println);
    }


    //  *чтение из файла*
    private static Employee parseEmployee(String line) {

        String[] part = line.split(", ");

        String name = part[0].substring(part[0].indexOf("=") + 1);
        LocalDate birthDate = LocalDate.parse(part[1].substring(part[1].indexOf("=") + 1));
        Gender gender = Gender.valueOf(part[2].substring(part[2].indexOf("=") + 1));
        String phoneNumber = part[3].substring(part[3].indexOf("=") + 1);
        String position = part[4].substring(part[4].indexOf("=") + 1);
        String department = part[5].substring(part[5].indexOf("=") + 1);
        String manager = part[6].substring(part[6].indexOf("=") + 1);
        LocalDate hireDate = LocalDate.parse(part[7].substring(part[7].indexOf("=") + 1));
        int salary = Integer.parseInt(part[8].substring(part[8].indexOf("=") + 1));
        int id = Integer.parseInt(part[9].substring(part[9].indexOf("=") + 1, part[9].indexOf("}")));

        return new Employee(name, birthDate, gender, phoneNumber, position, department, manager, hireDate, salary, id);

    }

}