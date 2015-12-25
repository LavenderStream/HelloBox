package org.hellobox.utils;

import android.util.SparseArray;

import org.hellobox.application.Utils;


/**
 * 创建并添加图形的属性信息
 */
public class ShapeInfo
{
	public SparseArray<ShapeAttribute> list = new SparseArray<ShapeAttribute>();

	public SparseArray<ShapeAttribute> getList()
	{
		return list;
	}


	public void setList(SparseArray<ShapeAttribute> list)
	{
		this.list = list;
	}
	
	public SparseArray<ShapeAttribute> ShapeInfoInit()
	{
		ShapeAttribute matts = new ShapeAttribute();
		int[][] numMatts = {
				{1,1},
				{1,1}};
		matts.setMatrix(numMatts);
		matts.setmLimiteLastLine(1);
		matts.setmLimiteLastRow(1);
		
		ShapeAttribute squareLU = new ShapeAttribute();
		int[][] numsquareLU = {
				{0,1},
				{1,1}};
		squareLU.setMatrix(numsquareLU);
		squareLU.setmLimiteLastLine(1);
		squareLU.setmLimiteLastRow(1);
		
		ShapeAttribute squareRU = new ShapeAttribute();
		int[][] numsquareRU = {
				{1,0},
				{1,1}};
		squareRU.setMatrix(numsquareRU);
		squareRU.setmLimiteLastLine(1);
		squareRU.setmLimiteLastRow(1);
		
		ShapeAttribute Plane = new ShapeAttribute();
		int[][] numsPlane = {
				{1,1,1,1}};
		Plane.setMatrix(numsPlane);
		Plane.setmLimiteLastLine(3);
		Plane.setmLimiteLastRow(0);
		
		ShapeAttribute Linear = new ShapeAttribute();
		int[][] numsLinear = {
				{1},
				{1},
				{1},
				{1}};
		Linear.setMatrix(numsLinear);
		Linear.setmLimiteLastLine(0);
		Linear.setmLimiteLastRow(3);
		
		ShapeAttribute LinearLong = new ShapeAttribute();
		int[][] numsLinearLong = {
				{1},
				{1},
				{1},
				{1},
				{1}};
		LinearLong.setMatrix(numsLinearLong);
		LinearLong.setmLimiteLastLine(0);
		LinearLong.setmLimiteLastRow(4);
		
		ShapeAttribute PlaneLong = new ShapeAttribute();
		int[][] numsPlaneLong = {
				{1,1,1,1,1}};
		PlaneLong.setMatrix(numsPlaneLong);
		PlaneLong.setmLimiteLastLine(4);
		PlaneLong.setmLimiteLastRow(0);
		
		ShapeAttribute PlaneShort = new ShapeAttribute();
		int[][] numsPlaneShort = {
				{1,1}};
		PlaneShort.setMatrix(numsPlaneShort);
		PlaneShort.setmLimiteLastLine(1);
		PlaneShort.setmLimiteLastRow(0);
		
		ShapeAttribute LinearShort = new ShapeAttribute();
		int[][] numsLinearShort = {
				{1},
				{1}};
		LinearShort.setMatrix(numsLinearShort);
		LinearShort.setmLimiteLastLine(0);
		LinearShort.setmLimiteLastRow(1);

        ShapeAttribute Point = new ShapeAttribute();
        int[][] numsPoint = {
                {1}};
        Point.setMatrix(numsPoint);
        Point.setmLimiteLastLine(0);
        Point.setmLimiteLastRow(0);


        ShapeAttribute BigMatts = new ShapeAttribute();
        int[][] numsBigMatts= {
                {1,1,1},
                {1,1,1},
                {1,1,1}};
        BigMatts.setMatrix(numsBigMatts);
        BigMatts.setmLimiteLastLine(2);
        BigMatts.setmLimiteLastRow(2);
		
		list.put(Utils.Point_Sign, Point);
		list.put(Utils.Matts_Sign, matts);
		list.put(Utils.Linear_Sign, Linear);
		list.put(Utils.LinearLong_Sign, LinearLong);
		list.put(Utils.SquareRU_Sign, squareRU);
		list.put(Utils.SquareLU_Sign, squareLU);
		list.put(Utils.Plane_Sign, Plane);
		list.put(Utils.PlaneLong, PlaneLong);
		list.put(Utils.PlaneShort_Sign, PlaneShort);
		list.put(Utils.LinearShort_Sign, LinearShort);
        list.put(Utils.Big_Matts_Sign, BigMatts);
		
		return this.list;
	}
	
}