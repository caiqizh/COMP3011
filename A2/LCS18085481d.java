import java.text.DecimalFormat;
import java.lang.Math;

public class LCS {

    private static String Find_LCS(String X, String Y, String Z) {
        int l = X.length(), m=Y.length(), n=Z.length();
        int[][][] a = new int[l + 1][m + 1][n + 1];
        int[][][] b = new int[l + 1][m + 1][n + 1];

        /*
         * 0: go one step in x - axis, y - axis, and z - axis
         * 1: go one step in z - axis
         * 2: go one step in y - axis
         * 3: go one step in x - axis
         */

        for (int i=0; i<=l; i++) {
            for (int j=0; j<=m; j++) {
                for (int k=0; k<=n; k++) {
                    if (i == 0 || j == 0||k == 0) {
                        a[i][j][k] = 0;
                    }
                    else if (X.charAt(i - 1) == Y.charAt(j - 1) && X.charAt(i - 1)==Z.charAt(k - 1)) {
                        a[i][j][k] = a[i - 1][j - 1][k - 1] + 1;
                        b[i][j][k] = 0;
                    }

                    else {
                        if (a[i - 1][j][k] == Math.max(Math.max(a[i - 1][j][k], a[i][j - 1][k]), a[i][j][k - 1])) {
                            a[i][j][k] = a[i - 1][j][k];
                            b[i][j][k] = 3;
                        } else if (a[i][j - 1][k] == Math.max(Math.max(a[i - 1][j][k], a[i][j - 1][k]), a[i][j][k - 1])) {
                            a[i][j][k] = a[i][j - 1][k];
                            b[i][j][k] = 2;
                        } else {
                            a[i][j][k] = a[i][j][k - 1];
                            b[i][j][k] = 1;
                        }
                    }
                }
            }
        }

        int x = l, y = m, z = n, length = a[l][m][n];

        char[] LCS = new char[a[l][m][n]];

        while(x != 0 && y != 0 && z != 0){
            switch(b[x][y][z]){
                case 0 :
                    x--;
                    y--;
                    z--;
                    LCS[length-1] = X.charAt(x);
                    length--;
                    break;
                case 1 :
                    z--;
                    break;
                case 2 :
                    y--;
                    break;
                case 3 :
                    x--;
                    break;
            }
        }

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < LCS.length; i++) {

            s.append(LCS[i]);
        }

        return s.toString();
    }

    public static String random_binary_sequence(int length){

        StringBuilder sb = new StringBuilder(1024);

        for (int i = 0; i < length; i++) {
            int rd = Math.random()>0.5?1:0;
            sb.append(rd);
        }
        String sequence = sb.toString();
        return sequence;
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        /*
        String X = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVYZABCDE";
        String Y = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVYZABCDE";
        String Z = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVYZABCDE";
        */
        /*
        String X = "11111111";
        String Y = "00100011";
        String Z = "11100000";

         */

        String X = "NINE";
        String Y = "SINGING";
        String Z = "NINJAS";
        System.out.println("The LCS is " + Find_LCS(X, Y, Z));

        long endTime = System.currentTimeMillis();

        System.out.println("Running time：" + (endTime - startTime) + "ms");

        /**
         *
         * The following is for question 4 and 5.
         *
         *
        int[] test_list = new int[]{80, 160, 240, 320, 400, 480, 560, 640, 720, 800};

        for (int i = 0; i < 10; i++) {
            double LCS_sum = 0;
            for (int j = 0; j < 10; j++) {

                String O = random_binary_sequence(test_list[i]);
                String P = random_binary_sequence(test_list[i]);
                String Q = random_binary_sequence(test_list[i]);

                //System.out.println(O + ", " +P + ", " +Q +  ", "+ Find_LCS(O,P,Q)+ ", "+ Find_LCS(O,P,Q).length());
                LCS_sum += Find_LCS(O,P,Q).length();
            }

            double LCS_rate = LCS_sum/10;

            DecimalFormat df=new DecimalFormat("0.0000");



            System.out.println(test_list[i] +" & "+ LCS_rate +" & "+ df.format((float)LCS_rate/test_list[i])+" //");



        }
         **/
    }
}