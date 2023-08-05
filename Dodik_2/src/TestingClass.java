import org.junit.After;
import org.junit.Assert;
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
        kal.calculateFromFile();
        Assert.assertEquals(("Príklad 1, má výsledok: 5\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testStringSpravne(){
        Kalkulacka kal = new Kalkulacka("add 1\r\nminus 3\r\napply 6");
        kal.doCalculationFromString();
        Assert.assertEquals(("Príklad 1, má výsledok: 4\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testKalkulackaZlyPrikaz() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n addd 2 \n apply 5");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.calculateFromFile();

        Assert.assertEquals(("Nesprávny príkaz!!\r\nPríklad 1, má výsledok: "+ Integer.MIN_VALUE+"\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testStringZlyPrikaz() {
        Kalkulacka kal = new Kalkulacka("addd 1\r\nminus 3\r\napply 6");
        kal.doCalculationFromString();
        Assert.assertEquals(("Nesprávny príkaz!!\r\nPríklad 1, má výsledok: "+ Integer.MIN_VALUE+"\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testKalkulackaChybajuceCislo() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n add \n apply 5");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.calculateFromFile();

        Assert.assertEquals(("Nesprávne číslo!!\r\nPríklad 1, má výsledok: "+Integer.MIN_VALUE+"\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testStringChybajuceCislo() {
        Kalkulacka kal = new Kalkulacka("add \r\nminus 3\r\napply 6");
        kal.doCalculationFromString();
        Assert.assertEquals(("Nesprávne číslo!!\r\nPríklad 1, má výsledok: "+Integer.MIN_VALUE+"\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testKalkulackaChybajuciPrikaz() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n 3 \n apply 5");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.calculateFromFile();
        Assert.assertEquals(("Nesprávne číslo!!\r\nPríklad 1, má výsledok: "+Integer.MIN_VALUE+"\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testStringChybajuciPrikaz() {
        Kalkulacka kal = new Kalkulacka("2 \r\nminus 3\r\napply 6");
        kal.doCalculationFromString();
        Assert.assertEquals(("Nesprávne číslo!!\r\nPríklad 1, má výsledok: "+Integer.MIN_VALUE+"\r\n"),(outputStreamCaptor.toString()));
    }
    @Test
    public void testKalkulackaChybajuciApply() throws IOException {

        FileWriter myWriter = new FileWriter("filename.txt");
        myWriter.write("minus 2 \n add 2");
        myWriter.close();

        Kalkulacka kal = new Kalkulacka("C:\\Users\\Nadis\\IdeaProjects\\Dodik_2\\filename.txt");
        kal.calculateFromFile();
        Assert.assertEquals((""),(outputStreamCaptor.toString()));
    }
    @Test
    public void testStringChybajuciApply() {
        Kalkulacka kal = new Kalkulacka("add 2 \r\nminus 3");
        kal.doCalculationFromString();
        Assert.assertEquals((""),(outputStreamCaptor.toString()));
    }
}
