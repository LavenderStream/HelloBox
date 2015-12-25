package org.hellobox.utils;

/**
 * 图形的属性， 每个图形都需要遵守这两条属性
 */
public class ShapeAttribute
{
    // 图形的限制行数
	private int mLimiteLastLine;
    // 图形的限制列数
	private int mLimiteLastRow ;
    // 代表图形的二维数组
	private int[][] matrix;
	
	public int getmLimiteLastLine()
	{
		return mLimiteLastLine;
	}
	public void setmLimiteLastLine(int mLimiteLastLine)
	{
		this.mLimiteLastLine = mLimiteLastLine;
	}
	public int getmLimiteLastRow()
	{
		return mLimiteLastRow;
	}
	public void setmLimiteLastRow(int mLimiteLastRow)
	{
		this.mLimiteLastRow = mLimiteLastRow;
	}
	public int[][] getMatrix()
	{
		return matrix;
	}
	public void setMatrix(int[][] matrix)
	{
		this.matrix = matrix;
	}
}
