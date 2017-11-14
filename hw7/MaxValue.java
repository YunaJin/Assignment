
public class MaxValue extends Thread {
    private int lo, hi;
    private int[] randomNumbers;
    private int ans = 0;

    public MaxValue(int[] randomNumbers, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.randomNumbers = randomNumbers;
    }

    @Override
    public void run() {
        for (int i = lo; i < hi; i++) {
            if (randomNumbers != null && randomNumbers[i] > ans)
                ans = randomNumbers[i];
        }
    }
    public static int maxValues(int[] arr) throws InterruptedException {
        int len = arr.length;
        int ans = 0;
        MaxValue[] maxValue = new MaxValue[4];
        for (int i = 0; i < 4; i++) {
            maxValue[i] = new MaxValue(arr, (i * len) / 4, ((i + 1) * len / 4));
            maxValue[i].start();
        }
        for (int i = 0; i < 4; i++) {
            maxValue[i].join();
            ans += maxValue[i].ans;
        }
        return ans/2;

    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[4];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println("max " + maxValues(new int[]{1,5,11,5}));
    }
}


