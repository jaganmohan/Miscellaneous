package miscellaneous;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class NirvanaBalancedString {

	public static void main(String []ar) throws Exception{
		
		Scanner sin = new Scanner(System.in);
		
		int N = sin.nextInt(),index, count, balIndices[], balIndex;
		HashMap<Character, Integer> M1 = new HashMap<Character, Integer>();
		HashMap<Character, Integer> M2 = new HashMap<Character, Integer>();
		String line; char curr; boolean flag;
		
		for(int i = 0; i<N; i++){
			
			line = sin.next(); M1.clear(); M2.clear();
			balIndices = new int[line.length()/2];
			Arrays.fill(balIndices, -1);
			index = count = balIndex = 0;
			balIndices[balIndex] = 0;
			flag = true;
			
			while(index < line.length()){
				curr = line.charAt(index++);
				if(M1.containsKey(curr)){
					if(M2.containsKey(curr)){
						if(M1.get(curr) == M2.get(curr))
							M1.put(curr, M1.get(curr)+1);
						else
							M2.put(curr, M2.get(curr)+1);
					}else{
						M2.put(curr, 1);
					}
				}else{
					M1.put(curr, 1);
				}
			
				if((flag || index == line.length()) && balancedString(M1, M2)){
					count++; 
					if(index < line.length())
						balIndices[++balIndex] = index;
					M1.clear(); M2.clear();
				}else if(balIndex > 0 && index == line.length()){
					count--; flag = false;
					index = balIndices[--balIndex];
					balIndices[balIndex] = -1;
					M1.clear(); M2.clear();
				}
			}
			System.out.println(count);
		}
		
	}
        
	public static boolean balancedString(HashMap<Character,Integer> M1, HashMap<Character,Integer> M2){
		
		Set<Character> alpha = M1.keySet();
		for(char temp: alpha){
			if(M1.get(temp) != M2.get(temp))
				return false;
		}
		return true;
		
	}
	
}
