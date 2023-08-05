import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kalkulacka {
   private ArrayList<String> keywords = new ArrayList<>();
   private ArrayList<Integer> nums =  new ArrayList<>();
   private String name;
   private ArrayList<Integer> positionsOfApplies = new ArrayList<>();
   public Kalkulacka(String p_name)  {
        name = p_name;
        positionsOfApplies.add(0);
    }
    public void calculateFromFile() throws FileNotFoundException {
        File file = new File(
                name);
        Scanner sc = new Scanner(file);
        String keyword ="";  int num; int i=1;int j=0;
        while(sc.hasNextLine()){
            keyword = sc.hasNext() ?  sc.next() : "";
            keywords.add(keyword);
            if(keyword.equals("apply")){
                positionsOfApplies.add(j);
            }
            num = sc.hasNextInt() ? sc.nextInt() : Integer.MIN_VALUE;
            nums.add(num);
            j++;
        }
        sc.close();
        calculate();
    }
    public void doCalculationFromString() {
            String[] line = name.split(System.lineSeparator());
            String[] pom ;
            int num ;
            for(int i=0;i<line.length;i++) {
                pom = line[i].split("\\s+");
                    keywords.add(pom[0]);
                    if(pom.length>1){
                        num = Integer.parseInt(pom[1]);
                        nums.add(num);
                    } else{
                        num = Integer.MIN_VALUE;
                        nums.add(num);
                    }

                if(pom[0].equals("apply")){
                    positionsOfApplies.add(i);
                }

            }
        calculate();
    }
    public void calculate(){
        String keyword;  int num; int result=0;
        for(int i=1;i<positionsOfApplies.size();i++){
                result=nums.get(positionsOfApplies.get(i));
                for (int j = positionsOfApplies.get(i - 1); j < positionsOfApplies.get(i); j++) {
                    if (!keywords.get(j).equals("apply")) {
                        keyword = keywords.get(j);
                        num = nums.get(j);
                        if (num == Integer.MIN_VALUE ) {
                            System.out.println("Nesprávne číslo!!");
                            result = Integer.MIN_VALUE;
                            break;
                        }
                        if(!checkKeyword(keyword)){
                            System.out.println("Nesprávny príkaz!!");
                            result = Integer.MIN_VALUE;
                            break;
                        }
                        switch (keyword) {
                            case "add":
                                result += num;
                                break;
                            case "multiply":
                                result *= num;
                                break;
                            case "divide":
                                result /= num;
                                break;
                            case "minus":
                                result -= num;
                                break;
                            default:
                                System.out.println("Nesprávny príkaz!!");
                                result = Integer.MIN_VALUE;
                        }
                    }
                }
                System.out.println("Príklad " + i + ", má výsledok: " + result);
        }
    }
    public boolean checkKeyword(String word){
       return word.equals("add") || word.equals("divide") || word.equals("multiply") || word.equals("minus");
    }
}

