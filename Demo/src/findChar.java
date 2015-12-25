public class findChar
{
	public static void main(String[] arg)
	{
		// 你要测试的字符串
		String testChar = "010001111111";
		// 记录某个字符出现的个数，在这里是'a'
		int count = 0;
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
		
		System.out.println(count);
	}
}
