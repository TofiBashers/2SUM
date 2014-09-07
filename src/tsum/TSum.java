/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TSum {

    /**
     * @param args the command line arguments
     */
    static int[] TSum(int arr[], int N, int p, int q)
    {
        int res[] =new int[2];
        int MaxElem=0;
        for(int i = 0; i < N; i++)
        {
            if(Math.abs(arr[i]) > MaxElem)
            {
                MaxElem = Math.abs(arr[i]);
            }
        }
        int arrInv[] = new int[MaxElem+1];
        int arrNegInv[] = new int[MaxElem+1];
        for(int i = 0; i < MaxElem+1; i++)
        {
            arrInv[i] = -1;
            arrNegInv[i] = 1;
        }
        for (int i = 0; i < N; i++) {
            int ap = arrNegInv[Math.abs(arr[i])];
            int aq = arrInv[Math.abs(arr[i])];
            if (arr[i] > 0) {
                if (ap != 1) {
                    res[1] = ap + 1;
                    res[0] = i + 1;
                    return res;
                } else {
                    arrInv[arr[i]] = i;
                }
            } 
            else if(arr[i] == 0)
            {
                 if (aq != -1) {
                    res[1] = i + 1;
                    res[0] = aq + 1;
                    return res;
                } else {
                    arrInv[0] = i;
                }
            }
            else {
                if (aq != -1) {
                    res[1] = i + 1;
                    res[0] = aq + 1;
                    return res;
                } else {
                    arrNegInv[-arr[i]] = i;
                }
            }
        }
        res[0] = -1;
        return res;
    }
    
   static int BinarySearch(int lo, int hi, int N, int arr[]) {
        if (lo > hi) {
            return -1;
        }
        int S = lo + (hi - lo) / 2;
        if(arr[S] < N)
        {
            return BinarySearch(S+1, hi, N, arr);
        }
        else if(arr[S] > N)
        {
            return BinarySearch(lo, S-1, N, arr);
        }
        else
        {
            return S;
        }
    }
    
    static int[] TSumMod(int arr[], int N)
    {
        int SortedArr[] = arr.clone();
        Arrays.sort(SortedArr);
        int res[] = new int[2];
        int preRes = Integer.MAX_VALUE;
        res[0] = -1;
        int i = 0, j = N-1;
        while(i < j && SortedArr[i]<=0 && SortedArr[j]>=0)
        {
            int sum = SortedArr[i] + SortedArr[j];
            if(sum > 0)
            {
                j--;
            }
            else if(sum < 0)
            {
                i++;
            }
            else
            {
                preRes = SortedArr[j];
                if(Math.abs(SortedArr[i + 1]) < SortedArr[j - 1])
                {
                    j--;
                }
                else
                {
                    i++;
                }
            }
            //System.out.println(SortedArr[i] + " " + SortedArr[j]);
        }
        //System.out.println();
        return SearchInNotSorted(arr, N, preRes, (N-1)/2);
    }
    
    
    static int[] SearchInNotSorted(int arr[], int N, int num, int mid)
    {
        int a = -1, b = -1;
        int res[] = new int[2];
        res[0] = -1;
        for(int i = 0; i < N; i++)
        {
            if(arr[i] == num)
            {
                a = i;
                break;
            }
        }
        if(a == -1)
        {
            return res;
        }
        for(int i = 0; i < N ; i++)
        {
            if(arr[i] == -arr[a])
            {
                 b = i;
                break;
            }
        }
        if (a > b) {
            res[1] = a + 1;
            res[0] = b + 1;
        } else {
            res[0] = a + 1;
            res[1] = b + 1;
        }
        return res;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader("rosalind_2sum.txt"));
        FileWriter writer = new FileWriter(new File("output.txt"));
        String inp[] = reader.readLine().split(" ");
        int k = Integer.parseInt(inp[0]);
        int n = Integer.parseInt(inp[1]);
        while(reader.ready())
        {
            int arr[] = new int[n];
            String str[] = reader.readLine().split(" ");
            for(int i = 0; i < n; i++)
            {
                arr[i] = Integer.parseInt(str[i]);
            }
            int res[] = TSumMod(arr, n);
            if(res[0] == -1)
            {
                writer.write(Integer.toString(res[0]));
                writer.flush();
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
            else
            {
                writer.write(Integer.toString(res[0])  + " " + Integer.toString(res[1]));
                writer.flush();
                writer.write(System.getProperty("line.separator"));
                writer.flush();
            }
        }
        writer.close();
    }
}
