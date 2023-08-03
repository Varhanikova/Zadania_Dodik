import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\src\\pom.txt");
         kal.doCalculationFromFile();
    }
}