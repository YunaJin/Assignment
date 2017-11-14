import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> triangel(int rows) {
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            System.out.println(row);}
        return null;

    }
    public static void main(String args[]){
        PascalTriangle pascalTriangle=new PascalTriangle();
        pascalTriangle.triangel(5);
    }
}