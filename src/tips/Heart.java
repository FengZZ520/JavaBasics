package tips;

/**
 * @author 冯振卓
 * @ 2021/11/28 21:21
 */
public class Heart {
    public static void main(String argv[]){
        for(float y = (float) 1.5;y>-1.5;y -=0.1) {
            for(float x= (float) -1.5;x<1.5;x+= 0.05) {
                float a = x*x+y*y-1;
                if((a*a*a-x*x*y*y*y)<=0.0) {
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }
}
