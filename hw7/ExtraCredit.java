import java.util.Arrays;

public class ExtraCredit {
    public boolean partition(int[] nums) {
        int sum = 0;
        for (int number : nums) {
            sum += number;
        }
            if ((sum & 1) == 1) {
                return false;
            }
            sum /= 2;
            int n = nums.length;
            boolean[][] bePartition = new boolean[n + 1][sum + 1];
            for (int i = 0; i < bePartition.length; i++) {
                Arrays.fill(bePartition[i], false);
            }
            bePartition[0][0] = true;
            for (int j = 1; j < sum + 1; j++) {
                bePartition[0][j] = false;
            }

            for (int i = 1; i < n + 1; i++) {
                bePartition[i][0] = true;
            }
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < sum + 1; j++) {
                    bePartition[i][j] = bePartition[i - 1][j];
                    if (j >= nums[i - 1]) {
                        bePartition[i][j] = (bePartition[i][j] || bePartition[i - 1][j - nums[i - 1]]);
                    }
                }
            }
            System.out.println(bePartition[n][sum]);
            return bePartition[n][sum];
        }
    public static void main(String args[]){
        ExtraCredit extraCredit=new ExtraCredit();
        extraCredit.partition(new int[]{1,5,11,5});
    }
} 
