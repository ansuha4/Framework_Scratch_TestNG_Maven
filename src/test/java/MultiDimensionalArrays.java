public class MultiDimensionalArrays {
    public static void main(String[] args) {
        //2 9 5
        //4 5 6
        //1 8 3
        int a[][] = new int[2][3];
        int b[][] = {{2, 9, 5}, {4, 5, 6}, {21, 12, 3}};
        //  int num = 0;
        int min = b[0][0];
        int col = 0;
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
//                if(b[i][j]>=num){
//                    num=b[i][j];
                if (b[i][j] < min) {
                    min = b[i][j];
                    col = j;

                }
            }


        }

        //         System.out.println();

        //    System.out.println(num);
        System.out.println(min);
        int max = b[0][col];
        int k =0;
        while(k<3){
            if(b[k][col]>=max){
                max = b[k][col];
            }
            k++;
        }
System.out.println(max);
    }
}



