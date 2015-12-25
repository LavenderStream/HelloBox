package org.hellobox.utils;


import android.util.SparseArray;

import java.util.List;

import org.hellobox.application.Utils;
import org.hellobox.view.BackGroundSquare;
import org.hellobox.view.BackGroundView;

/**
 * 游戏判断的工具类
 * checkWhole(int hashcode) :判断下一个图形时候可以放到现在的背景中去
 * checkLine()：检查一行或者一列，看看其是否放满，若放满就消除
 */
public class Tool
{
    /**
     * @param hashCode
     * @return boolean true：可以放置  false ： 不能放置
     * @deprecated 判断下一个图形时候可以放到现在的背景中去
     */
    public static boolean checkWhole(int hashCode)
    {
        boolean flag = false;
        ShapeInfo sp = new ShapeInfo();
        SparseArray<ShapeAttribute> ls = sp.ShapeInfoInit();

        Find findTool = new Find();
        // 这是地板图形的目前占用的情况
        BackGroundSquare[][] myTextView = BackGroundView.myTextView;
        int[][] backGroundMatrix = new int[10][10];

        for (int i = 0; i < myTextView.length; i++)
        {
            for (int j = 0; j < myTextView.length; j++)
            {
                backGroundMatrix[i][j] = myTextView[i][j].getIsEx();
            }
        }
        // 将此时hashCode所代表的图形矩阵转成int 二维数组
        int[][] num = sp.getList().get(hashCode).getMatrix();
        // 将上面得到的二维数组在背景的copy中遍历放置
        for (int i = 0; i < 10 - sp.getList().get(hashCode).getmLimiteLastRow(); i++)
        {
            for (int j = 0; j < 10 - sp.getList().get(hashCode).getmLimiteLastLine(); j++)
            {
                // 将待放进的二维数组转成list数组
                List<String> targetMatrixList = findTool.adjustMatrix(j, i, num, 10);
                // 将待选的数组个背景的数组相比较
                List<String> mergeMatrixzList = findTool.mergeMatrix(backGroundMatrix, targetMatrixList);
                //也将背景数组转化为list 这样方便比较
                List<String> backGroundMatrixList = findTool.matrix2SingleDimensional(backGroundMatrix);

                if (findTool.haveOneInMatrix(backGroundMatrixList) + findTool.haveOneInMatrix(targetMatrixList)
                        == findTool.haveOneInMatrix(mergeMatrixzList))
                {
                    flag = true;
                    /*for (String string : list)
                    {
						System.out.println(string.toString());
					}
					System.out.println("\n");*/
                }
            }
        }
        return flag;
    }

    /** 判断某一行或者某一列的是否填满，若填满则全部去掉
     * @return
     */
    public static int checkLine()
    {
        int count = 0;
        int countRow = 0;
        // 这里要变
        int[] line = new int[Utils.MATRIXNUM];
        int[] row = new int[Utils.MATRIXNUM];

        //初始化矩阵，仅仅为了区分行号的一个标记，
        for (int i = 0; i < Utils.MATRIXNUM; i++)
        {
            line[i] = -1;
            row[i] = -1;
        }

        // 首先按行查找
        for (int k = 0; k < Utils.MATRIXNUM; k++)
        {
            count = 0;
            countRow = 0;
            for (int k2 = 0; k2 < Utils.MATRIXNUM; k2++)
            {
                if (BackGroundView.myTextView[k][k2].getIsEx() == Utils.FILL)
                {
                    count++;
                    // 若这一行正好满足10个（总数），证明这个已经满了，这个k（行号）保存下来
                    if (count == Utils.MATRIXNUM)
                    {
                        line[k] = k;
                    }
                }


                if (BackGroundView.myTextView[k2][k].getIsEx() == Utils.FILL)
                {
                    countRow++;
                    if (countRow == Utils.MATRIXNUM)
                    {
                        row[k] = k;
                    }
                }
            }
        }

        int removwLineCount = 0;
        for (int i = 0; i < line.length; i++)
        {
            // 看看这个行有没有被标记
            if (line[i] != -1)
            {
                removwLineCount++;
                // 被标记之后将这个行或者是这个列改变颜色
                for (int j = 0; j < Utils.MATRIXNUM; j++)
                {
                    BackGroundView.myTextView[line[i]][j].addAnimationBack(j * 30);
                }
            }
        }

        for (int i = 0; i < row.length; i++)
        {
            if (row[i] != -1)
            {
                removwLineCount++;
                for (int j = 0; j < Utils.MATRIXNUM; j++)
                {
                    BackGroundView.myTextView[j][row[i]].addAnimationBack(j * 30);
                }
            }
        }

        // 最后返回的是消去的总行数
        return removwLineCount;
    }
}
