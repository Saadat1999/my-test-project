package streams;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static void main() {
        taskOne();
        taskThree();
        taskFive();
    }

    static void taskOne() {
        List<Integer> integerList = List.of(1,2,3,4,5);

        //1ci tapsiriq
        List<Integer> squares = integerList.stream().map(x -> x * x).toList();
        System.out.println(squares);

        //2ci tapsiriq
        boolean b = squares.stream().allMatch(x -> x % 2 == 0);
        if(b) {
            System.out.println("Listin butun elementleri cut ededlerdir.");
        }
    }

    static void taskThree() {
        List<User> users = Arrays.asList(
                new User("Seadet", 26),
                new User("Fidan", 25),
                new User("Fatima", 16),
                new User("Lala", 30),
                new User("Sevinc", 20)
        );

        //3cu tapsiriq
        List<String> nameList = users.stream()
                .filter(user -> user.getAge() > 20)
                .map(User::getName)
                .map(String::toUpperCase)
                .toList();
        System.out.println(nameList);

        //4cu tapsiriq
        List<User> ageList = users.stream().sorted(Comparator.comparing(User::getAge).reversed()).toList();
        System.out.println(ageList);
    }

    static void taskFive() {
        List<Employee> employeeList = List.of(
                new Employee(1, "Anar", "IT", 3200, 25,
                        List.of(new Task("deploy release v1.2"), new Task("code review"))),
                new Employee(2, "Leyla", "HR", 2900, 24, List.of(new Task("conduct interview"))),
                new Employee(3, "Rafael", "DND", 3500, 26,
                        List.of(new Task("Daily monitoring"))),
                new Employee(4, "Zaur", "IT", 2800, 29,
                        List.of(new Task("write unit tests"), new Task("code review"))),
                new Employee(5, "Gila", "HR", 3500, 25)
        );

        //5ci tapsiriq
        double avg = employeeList.stream()
                .filter(dep -> "IT".equals(dep.getDepartment()))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println(avg);

        double v = employeeList.stream()
                .filter(dep -> "IT".equals(dep.getDepartment()))
                .map(Employee::getSalary)
                .reduce(Double::sum)
                .orElse(0.0);   // OptionalDouble vs Optional<Double>
        System.out.println(v);

        //6ci tapsiriq
        employeeList.stream()
                .filter(employee -> employee.getSalary()>3000)
                .map(Employee::getDepartment)
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::println);

        //7ci tapsiriq
        String highestPaidEmployee = employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getName)
                .orElseGet(String::new);
        System.out.println(highestPaidEmployee);

        //8ci tapsiriq
        String joinNamesByComma = employeeList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println(joinNamesByComma);


        //9cu tapsiriq
        List<String> listOfAllTasks = employeeList.stream()
                .map(emp -> Optional.ofNullable(emp.getTasks()).orElse(List.of()))
                .flatMap(Collection::stream)
                .map(Task::getName)
                .distinct()
                .toList();
        System.out.println(listOfAllTasks);


        //10cu tapsiriq
        String department = employeeList.stream()
                .filter(emp -> emp.getSalary() > 2500)
                .map(Employee::getDepartment)
                .distinct()
                .sorted()
                .findFirst()
                .orElse("Hec bir ad tapilmadi");
        System.out.println(department);
    }
}

