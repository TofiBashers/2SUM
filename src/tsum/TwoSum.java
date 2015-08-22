
package tsum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;

/**
 *
 * @author TofixXx
 */

/** Solving 2SUM problem by method of two pointers.
 Input information reads from file, result writes to file.*/
public class TwoSum {

    /** Searches two elements of array, that arr[i] + arr[j] = 0.
     * @param arr array
     * @return If elements are exists, returns aray of it's sorted indexes.
     * Otherwise, returns arry with -1 value
     */
    static int[] findTwoElementsWithZeroSum(int arr[]) {
        int[] sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        Arrays.sort(sortedArr);
        int resValue = Integer.MAX_VALUE;
        int i = 0, j = arr.length - 1;
        while (i < j && sortedArr[i] <= 0 && sortedArr[j] >= 0) {
            int sum = sortedArr[i] + sortedArr[j];
            if (sum > 0) {
                j--;
            } else if (sum < 0) {
                i++;
            } else {
                resValue = sortedArr[j];
                if (Math.abs(sortedArr[i + 1]) < sortedArr[j - 1]) {
                    j--;
                } else {
                    i++;
                }
            }
        }
        return resValue != Integer.MAX_VALUE ? 
                getIndexesByValue(arr, resValue) : new int[]{-1};
    }

    /** Returns two indexes (for positive and negative values of number)
    */
    static int[] getIndexesByValue(int arr[], int num) {
        int[] resInds = new int[]{-1, -1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num && resInds[0] == -1) {
                    resInds[0] = i+1;
            }
            else if (arr[i] == -num && resInds[1] == -1) {
                    resInds[1] = i+1;
            }
        }
        
        Arrays.sort(resInds);
        return resInds;
    }

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new FileReader("rosalind_2sum.txt"));
                FileWriter writer = new FileWriter(new File("output.txt"))) {
            String inp[] = reader.readLine().split(" ");
            int k = Integer.parseInt(inp[0]);
            int n = Integer.parseInt(inp[1]);
            while (reader.ready()) {
                int arr[] = new int[n];
                String str[] = reader.readLine().split(" ");
                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(str[i]);
                }
                int res[] = findTwoElementsWithZeroSum(arr);
                for(int i=0; i < res.length; i++){
                    writer.write(Integer.toString(res[i]));
                    if(i == res.length-1){
                       writer.write(System.getProperty("line.separator"));
                    }
                    else{
                       writer.write(" ");
                    }
                    writer.flush(); 
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
