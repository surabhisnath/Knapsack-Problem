package knapsack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//TO FIND:
//1) MAX PROFIT
//2) ITEMS CHOSEN


class Reader			//Reader class
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


class info
{
	float data;
	int wt;
	int pr;
	int no;
	
	public info(float x, int a, int b, int c)
	{
		data=x;
		wt=a;
		pr=b;
		no=c;
	}
}	



public class fracks 
{

	
	public void mergesort(info[] arr, int low, int high)
	{
		
		if (low<high) 
		{
			int middle = (low + high) / 2;
			mergesort(arr, low, middle);
			mergesort(arr, middle + 1, high);

			merge(arr, low, middle, high);
		}
	}
	
	public void merge(info[] arr, int lef, int mid, int rig)
	{
		info[] aux=new info[rig+1];		

		for (int i = lef; i <= rig; i++) 
		{
			aux[i] = arr[i];
		}

		int i = lef;
		int j = mid + 1;
		int k = lef;

		while (i <= mid && j <= rig) 
		{
			if(aux[i].data>aux[j].data)
			{
				arr[k]=aux[i];
				i++;
			} 
			
			else
			{
				arr[k]=aux[j];
				j++;
			}
			
			k++;
		}
		
		while (i <= mid) 
		{
			arr[k] = aux[i];
			k++;
			i++;
		}
			
		while (j <= rig) 
		{
			arr[k] = aux[j];
			k++;
			j++;
		}
	
	}
	
	
	public static void main(String[] args) throws IOException 
	{
		Reader.init(System.in);
		
		int N;					//Number of items
		int C;					//Capacity of knapsack
		
		N=Reader.nextInt();
		C=Reader.nextInt();
		
		
		int W[]=new int[N];		//arrays storing weights and profit values for each item
		int P[]=new int[N];
		
		info PbyW[]= new info[N];	//array for storing profit/weight value for each item
		
		fracks object= new fracks();
		
		float tot_profit=0;
		
		
		for(int i=0; i<N; i++)
		{
			W[i]=Reader.nextInt();
			
			P[i]=Reader.nextInt();
			
			info obj= new info((float)P[i]/W[i], W[i], P[i], i+1);
			PbyW[i]=obj;
		}
		
		
		object.mergesort(PbyW, 0, N-1);			//sorts items in PbyW on the basis profit/weight ratio in descending order
		
		
		int item[]= new int[N];
		int count=0;
		int flag=-1;
		float frac=1;
		int j;
		
		for(int i=0; i<N; i++)
		{
			for(j=0; j<PbyW[i].wt; j++)
			{
				tot_profit+=PbyW[i].data;
				count++;
				
				if(count==C)
				{
					if(j!=PbyW[i].wt-1)
					{
						flag=j;
					}
					
					break;
				}
					
			}
			
			if(flag!=-1)
			{
				frac=((float)(j+1)/PbyW[i].wt);
				item[i]=PbyW[i].no;
				break;
			}
			
			item[i]=PbyW[i].no;
		}
		
		
		
		int size=0;
		System.out.println("Total profit is: "+Math.round(tot_profit));
		System.out.print("Items are: ");
		for(int k=0; k<N; k++)
		{
			if(item[k]==0)
				break;
			size++;
		}
		
		
		for(int u=0; u<size; u++)
		{
			if(u==size-1)
			{
				System.out.println((int)(frac*100)+"% of "+"Item"+item[u]);
			}
			
			else
				System.out.print("Item"+item[u]+", ");
		}

	}

}
