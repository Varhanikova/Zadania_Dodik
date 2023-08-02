import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class kalkulacka {
   private ArrayList<String> keywords = new ArrayList<>();
   private ArrayList<Integer> nums =  new ArrayList<Integer>();
    public kalkulacka(String name) throws FileNotFoundException {
        Scanner sc = loadFile(name);
        int result= getLast(sc);
        result = calculate(result);
        System.out.println("result: "  + result);

    }
    private Scanner loadFile(String name) throws FileNotFoundException {
        File file = new File(
                name);
        return new Scanner(file);
    }
    public int getLast(Scanner sc){
        String keyword ="";  int num;
        while (!keyword.equals("apply")){
            keyword = sc.next();
            num = sc.nextInt();
            keywords.add(keyword);
            nums.add(num);
        }
        return nums.get(nums.size()-1);
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

