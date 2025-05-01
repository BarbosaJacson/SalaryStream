import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //C:\\Users\\Jackson\\Desktop\\exerciciosJava\\Stream.csv
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Enter full file path: ");
        String path = scanner.nextLine();
        List<Employee> list = new ArrayList<>();
        try (FileReader fr = new FileReader(path, StandardCharsets.UTF_8);

            BufferedReader br = new BufferedReader(fr)) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                System.out.println(line);
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            scanner.close();
            return;
        }
        System.out.print("Email of people whose salary is more than: ");
        double AverageSalary = scanner.nextDouble();

        List<String> email = list.stream()
                .filter(t -> t.getSalary() > AverageSalary)
                .map(t -> t.getEmail())
                .sorted()
                .collect(Collectors.toList());
       System.out.println("People whose salary is above: " + email);
       System.out.print("Sum of salary of people whose name starts with 'M': " );
        double sumM = list.stream()
                .filter(x -> x.getName().toUpperCase().startsWith("M"))
                .map(x -> x.getSalary())
                .reduce(0.0, Double::sum);

        System.out.println(sumM);







        scanner.close();
    }
}
