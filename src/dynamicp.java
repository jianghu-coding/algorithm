/**
 * @author hejianglong
 * @date 2019/7/23
 */
public class dynamicp {

    public static void main(String[] args) {
        int[] weight = new int[]{2,2,4,6,3};

        int rs = knapsack2(weight, weight.length, 9);
        int rs2 = KnaspackPlus.knaspack(KnaspackPlus.weights, KnaspackPlus.prices, KnaspackPlus.n, KnaspackPlus.w);
        System.out.println(rs);
        System.out.println(rs2);
    }

    public static int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        states[0][0]=true;
        states[0][weight[0]]=true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j + weight[i] <= w; j++) {
                if (states[i-1][j]) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[n-1][i]) {
                return i;
            }
        }
        return 0;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1];
        states[0] = true;
        states[items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }
}
class KnaspackPlus {

    static int[] weights = {2,2,4,6,3};
    static int[] prices = {3,4,8,9,6};
    static int n = 5;
    static int w = 9;

    public static int knaspack(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].length; j++) {
                System.out.print(states[i][j]+ " ");
            }
            System.out.println();
        }
        int max = -1;
        for (int j = 0; j <= w; j++) {
            if (states[n-1][j] > max) {
                max = states[n-1][j];
            }
        }
        return max;
    }
}
class Tabao {

    /**
     *
     * @param items 商品的价格
     * @param n 商品个数
     * @param w 满减条件
     */
    public static void double11advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3*w+1]; // 超过3倍价格就没有撸羊毛的价值了
        states[0][0] = true;
        states[0][items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 3 * w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= 3 * w - items[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        int j;
        // 输出结果大于等于 W 的最小值
        for (j = w; j < 3 * w + 1; j++) {
            if (states[n - 1][j]) {
                break;
            }
        }
        // 无解
        if (j == -1) {
            return;
        }
        for (int i = n - 1; i >= 1; i--) {
            if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
                System.out.println(items[i] + " ");
                j = j - items[i];
            }
        }
        if (j != 0) {
            System.out.println(items[0]);
        }
    }
}