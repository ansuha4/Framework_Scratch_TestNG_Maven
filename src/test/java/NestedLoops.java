public class NestedLoops {
    public static void main(String[] args) {
        //most of the interview questions come from nested loop itself
        //1 2 3 4
        //5 6 7
        //8 9
        //10
        int k = 1;
        for(int i=0;i<4;i++){
            for(int j=1;j<=4-i;j++){
                System.out.print(k+" ");
                k++;
            }

            System.out.println();

        }


    //1
    //2 3
    //4 5 6
    //7 8 9 10
    int p = 1;
    for(int i =0;i<4;i++){
      for (int j=1;j<=i+1;j++){
          System.out.print(p+" ");
          p++;
      }
      System.out.println();
     }
        //1
        //1 2
        //1 2 3
        //1 2 3 4
        for(int i=0;i<4;i++){
            for(int j=1;j<=1+i;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
            //1 2 3 4
            //1 2 3
            //1 2
            //1
        for(int i=0;i<4;i++){
            for(int j=1;j<=4-i;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        for(int i=0;i<4;i++){
            for(int j=1;j<=1+i;j++){
                System.out.print(3*j+" ");
            }
            System.out.println();
        }
    }
}

