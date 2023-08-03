import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kalkulacka {
   private ArrayList<String> keywords = new ArrayList<>();
   private ArrayList<Integer> nums =  new ArrayList<>();
   private String name;
   public Kalkulacka(String p_name)  {
        name = p_name;
    }
    public void doCalculationFromFile() throws FileNotFoundException {
        File file = new File(
                name);
        Scanner sc = new Scanner(file);
        String keyword ="";  int num; int i=1;int res;
        while(sc.hasNextLine()){
            while (!keyword.equals("apply") && sc.hasNextLine()){
                keyword = sc.hasNext() ?  sc.next() : "";
                num = sc.hasNextInt() ? sc.nextInt() : Integer.MIN_VALUE;
                keywords.add(keyword);
                nums.add(num);

            }
            if(nums.size()>0 && keyword.equals("apply")) {
                res = calculate(nums.get(nums.size() - 1));
                System.out.println("Príklad " + i + ", má výsledok: " + res);
            }
            keyword="";i++;
            keywords.clear();
            nums.clear();
        }
        sc.close();
    }

    public int calculate(int result){
        String keyword;  int num;
        for(int i =0;i<nums.size()-1;i++){
            keyword = keywords.get(i);
            num = nums.get(i);
            if(num == Integer.MIN_VALUE)  {
                System.out.println("Nesprávne číslo!!");
                keyword="";
            }
            switch (keyword){
                case "add":
                    result +=num;
                    break;
                case "multiply":
                    result*=num;
                    break;
                case "divide":
                    result/=num;
                    break;
                case "minus":
                    result-=num;
                    break;
                default:
                    System.out.println("Nesprávny príkaz!!");
                    result=Integer.MIN_VALUE;
            }
        }
        return result;
    }

}

