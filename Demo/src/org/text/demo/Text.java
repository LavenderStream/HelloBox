package org.text.demo;

import java.util.ArrayList;
import java.util.List;

public class Text
{
	public static void main(String[] args) {
		Text t = new Text();
	
		int[][] numoo = {
				{0,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,0,1,1},
				{1,1,1,1,1,1,0,1,1,1},
				{1,1,1,1,1,1,1,1,0,1},
				{1,1,1,0,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,0},
				{1,1,1,1,1,1,1,0,1,0},
				{1,1,1,1,1,1,1,1,1,0},
				{1,0,1,1,1,1,1,1,1,0},
				{1,1,1,1,1,1,1,1,1,0}
				};
		// 当改变可移动图案的形状的时候，一定要改变遍历matrix的限制条件
		int[][] num = {
				{1,1}};
		// 第一个参数是0~(10-x), 第二个三数是上下的偏移
		
		for (int i = 0; i < 10  ; i++) // 上下的偏移 row
		{
			for (int j = 0; j < 10 - 1; j++)  // 左右移动的偏移 line
			{
				//可移动图形的轮训 
				List<String> list = t.adjustMatrix(j, i, num, 10);
				
				for (String string : list)
				{
					System.out.println(string.toString());
				}
				System.out.println("mergeMatrix");
				
				// 两个矩阵合并后的效果
				List<String> list2 = t.mergeMatrix(numoo, list);
			/*	for (String string : list2)
				{
					System.out.println(string.toString());
				}*/
				// 地板的原始矩阵
				List<String> list0 = t.matrix2SingleDimensional(numoo);
			/*	System.out.println(t.haveOneInMatrix(list0));
				System.out.println(t.haveOneInMatrix(list));
				System.out.println(t.haveOneInMatrix(list2));*/
				
				if (t.haveOneInMatrix(list0) + t.haveOneInMatrix(list)
						==  t.haveOneInMatrix(list2))
				{
					for (String string : list)
					{
						System.out.println(string.toString());
					}
					System.out.println("\n");
				}
			}
		}
    }
	
	
	/**@author Tiny
	 * 两个字符串对位或运算
	 * 将两个长度相等并且string的值不是1就是0这样的字符串做|操作
	 * @param str1, str2 进行的两个字符串
	 * return 进行或操作之后的一个新的String*/ 
	private String faceStringOROperation(String str1, String str2)
	{
		String result = "";
		for (int i = 0; i < str1.length(); i++)
		{
			result = result + (
					Integer.parseInt(str1.substring(i, i + 1))
					| Integer.parseInt(str2.substring(i, i + 1)));
		}
		
		return result;
	}
	/**@author Tiny
	 * 数组向下调整行
	 * 即将这个list前面空出一整行0，使list下移，越界的整行元素去掉
	 * @param originalList：将要向下移动的目标list（这个list已经是经过规格化之后的
	 * 10x10的一个list）
	 * @param offsetDown：向下的偏移量，可以理解成为行前面的空行*/ 
	private List<String> structuredList(List<String> originalList, int offsetDown)
	{
		List<String> result = originalList;
		
		for (int i = 0; i < offsetDown; i++)
		{
			result.add(i, "0000000000");
		}
		for (int i = result.size(); i > 10; i--)
		{
			result.remove(i - 1);
		}
		
		return result;
	}
	
	/**@author Tiny
	 * 二维数组转一维字符串
	 * 将一个二维的int型数组，转化为arrayList，list中的每一个都是一个string
	 * @param int[][]
	 * @return List
	 */ 
	public List<String> matrix2SingleDimensional(int[][] matrix)
	{
		java.util.List<String> list = new ArrayList<String>();
		//System.out.println(matrix.length);
		//System.out.println(matrix[0].length);
		String temp = "";
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[0].length; j++)
			{
				temp =  temp + matrix[i][j];
			}
			list.add(i, temp);
			temp = "";
		}
		
		for (int i = list.size(); i < 10; i++)
		{
			list.add(i, "0");
		}
		return list;
	}
	
	
	/**@author Tiny
	 * 规整化字符串, 即将每组数的数字向右移动offset个单位
	 * @param structureLength：这里认为是10， string向左右移动，其余的空位补0，最后
	 * 字符串的的长度一定是10
	 * @return 一个长度为10的字符串，若转化错误，为x*/ 
	private String structuredString(int structureLength, String originalString,
			int offset)
	{
		String result = "x";
		if (offset <= structureLength - originalString.length())
		{
			String input = originalString;

			StringBuilder zero = new StringBuilder();

			for (int i = 0; i < structureLength; i++)
			{
				zero.append("0");
			}

			String middleResult = input
					+ zero.substring(offset, zero.length() - input.length());
			result = zero.substring(0, structureLength - middleResult.length())
					+ middleResult;
		}
		return result;
	}
	/**@author Tiny*/
	public List<String> adjustMatrix(int lineGap, int rowGap, int[][] matrix, int num)
	{
		// 现将代表每个图形的数组转成list形式
		List<String> list = matrix2SingleDimensional(matrix);
		
		for (int i = 0; i < list.size(); i++)
		{
			// 将每一行String都转成10个字符，在加好偏移量
			list.set(i, structuredString(num, list.get(i), lineGap));
		}
		
		list = structuredList(list, rowGap);
		return list;
	}
	/**@author Tiny
	 * 查寻二维数组里面有几个1*/
	public int haveOneInMatrix(List<String> list)
	{
		int count = 0;
		for (int i = 0; i < list.size(); i++)
		{
			String testChar = list.get(i);
			// 记录某个字符出现的个数，在这里是'a'
			int c = 0;
			// 将字符串变为字符数组
			char[] ch = testChar.toCharArray();
			// 循环判断是否有字符'a'
			for (char a : ch)
			{
				switch (a)
				{
				// 如果有计数器加1
				case '1':
					count++;
					break;
				}
			}

			count = count + c;

		}
		return count;
	}
	
	/**@author Tiny
	 * 安或的关系合并两个数组*/
	public List<String> mergeMatrix(int[][] matrix, List<String> list)
	{
		List<String> result = new ArrayList<String>();
		List<String> l = matrix2SingleDimensional(matrix);
		
		for (int i = 0; i < matrix.length; i++)
		{
			result.add(i, faceStringOROperation(l.get(i), list.get(i))
					);
		}
		return result;
	}

}
