import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        int[] num = new int[size];

        for(int i = 0; i < size; i++){
            num[i] = s.nextInt();
        }
        s.close();

        SortNumber(num);
        for (int j : num) {
            System.out.println(j);
        }
    }

    public static void SortNumber(int[] num){
        for(int i = 0; i < num.length - 1; i++){
            for(int j = i+1; j < num.length; j++){
                if(num[i] > num[j]){
                    int temp;
                    temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }
    }
}
