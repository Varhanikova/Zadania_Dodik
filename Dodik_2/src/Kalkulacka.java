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
    public void loadFile() throws FileNotFoundException {
        File file = new File(
                name);
        Scanner sc = new Scanner(file);
        String keyword ="";  int num; int i=1;int res;
        while(sc.hasNextLine()){
            while (!keyword.equals("apply")){
                keyword = sc.next();
                num = sc.nextInt();
                keywords.add(keyword);
                nums.add(num);
            }
            res = calculate(nums.get(nums.size()-1));
            System.out.println("Priklad " + i + ", má výsledok: " + res);
            keyword="";
            keywords.clear();
            nums.clear();
        }

        sc.close();
      // return calculate(nums.get(nums.size()-1));
    }

    public int calculate(int result){
        String keyword;  int num;
        for(int i =0;i<nums.size()-1;i++){
            keyword = keywords.get(i);
            num = nums.get(i);
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
            }
        }
        return result;
    }

}

