import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class TestingClass {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @After
    public void destroy() {
        System.setOut(originalOut);
    }
    @Test
    public void testKalkulackaSpravne() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n add 2 \n apply 5");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.doCalculationFromFile();
        assert(("Príklad 1, má výsledok: 5\r\n").equals(outputStreamCaptor.toString()));
    }
    @Test
    public void testKalkulackaZlyPrikaz() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n addd 2 \n apply 5");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.doCalculationFromFile();
        assert(("Nesprávny príkaz!!\r\nPríklad 1, má výsledok: "+Integer.MIN_VALUE+"\r\n").equals(outputStreamCaptor.toString()));
    }
    @Test
    public void testKalkulackaChybajuceCislo() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n add \n apply 5");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.doCalculationFromFile();
        assert(("Nesprávne číslo!!\r\nNesprávny príkaz!!\r\nPríklad 1, má výsledok: "+Integer.MIN_VALUE+"\r\n").equals(outputStreamCaptor.toString()));
    }
    @Test
    public void testKalkulackaChybajuciPrikaz() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n add \n apply 5");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.doCalculationFromFile();
        assert(("Nesprávne číslo!!\r\nNesprávny príkaz!!\r\nPríklad 1, má výsledok: "+Integer.MIN_VALUE+"\r\n").equals(outputStreamCaptor.toString()));
    }
}