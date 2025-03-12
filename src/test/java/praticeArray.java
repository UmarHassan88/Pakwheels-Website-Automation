import org.testng.annotations.Test;

import java.util.Arrays;

@Test
public class praticeArray {
    int[] x = {1,2,3,4,5};
    int[] copy = new int[x.length];

    public void Arr(){
    for(int i=0;i<x.length;i++){
        copy[i] = x[i];

    }
    System.out.println("Copied Array" + Arrays.toString(copy) );
    }
}
