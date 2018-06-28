package knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class R	//Reader class
{
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) 
    {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException 
    {
        while ( ! tokenizer.hasMoreTokens() ) 
        {
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException 
    {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException 
    {
        return Double.parseDouble( next() );
    }

}


public class knapsack01 
{
	
	public int knapsack(int C, int N, int[] wt, int[] pr, int[] items)
	{
		int[][] sack=new int[N+1][C+1];
		
		for(int i=0; i<=N; i++)
		{
			for(int j=0; j<=C; j++)
			{
				if(i==0 || j==0)
					sack[i][j]=0;
				
				else if (wt[i-1]<=j)
				{
					sack[i][j]= Math.max(pr[i-1] + sack[i-1][j-wt[i-1]] , sack[i-1][j]);
					
				}
					
				
				else
					sack[i][j]=sack[i-1][j];
			}
		}
		
		
//		for(int a=0; a<=N; a++)
//		{
//			for(int b=0; b<=C; b++)
//			{
//				System.out.print(sack[a][b]+" ");
//			}
//			System.out.println();
//		}
		
		
		int x=N;
		int y=C;
		int p=0;
		
		while(sack[x][y]!=0)
		{
			if(sack[x][y]!=sack[x-1][y])
			{
				items[p]=x;
				p++;
				x=x-1;
				y=y-wt[x];
			}
				
			else
			{
				x=x-1;
			}
		}
		
		
		return sack[N][C];
		
		
	}

	public static void main(String[] args) throws IOException
	{
		R.init(System.in);
		
		int N,C;
		N=R.nextInt();
		C=R.nextInt();
		
		
		int[] weight=new int[N];
		int[] profit=new int[N];
		
		for(int i=0; i<N; i++)
		{
			weight[i]=R.nextInt();
			profit[i]=R.nextInt();
		}
		
		knapsack01 obj=new knapsack01();
		
		int[] items=new int[N];
		int tot_profit=obj.knapsack(C, N, weight, profit, items);
		System.out.println("The total profit is: "+tot_profit);
		System.out.print("The items in sack are: ");
		
		int l=0;
		while(items[l]!=0)
		{
			System.out.print("Item"+items[l]+" ");
			l++;
		}

	}

}
