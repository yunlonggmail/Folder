package com.yunlong.algorithm.chapter1.spread;
/**
 * 最大公约数
 * 针对获取两个数的最大公约数
 * @author shiyunlong
 */
public class GreatestCommonDivisor {
		
	/**
	 * 主函数
	 * @param args
	 */
	 public static void main(String[] args){
		 int num1 = 98;
		 int num2 = 63;
		 int gcd = euclidAlgorithm(num1,num2);
		 int gcd2 = equivalentAlgorithm(num1, num2);
		 System.out.println(String.format("数：%d和数：%d的最大公约数是：%d。", num1,num2,gcd));
		 System.out.println(String.format("数：%d和数：%d的最大公约数是：%d。", num1,num2,gcd2));
	 }
	 /**
	  * 欧几里得算法
	  * 辗转相除法：辗转相除法是求两个自然数的最大公约数的一种方法，也叫欧几里德算法。
	  * @param num1：数1
	  * @param num2：数2
	  * @return
	  */
	  public static int euclidAlgorithm(int num1,int num2){
		  if(num1<0||num2<0)
			  return -1;
		  if(num2==0)
			  return num1;
		  int remainder=num1%num2;
		  return euclidAlgorithm(num2, remainder);
	  } 
	  /**
	   * 更相减损法：也叫更相减损术，是出自《九章算术》的一种求最大公约数的算法，它原本是为约分而设计的，但它适用于任何需要求最大公约数的场合。 
	   * @param num1
	   * @param num2
	   * @return
	   */
	  public static int equivalentAlgorithm(int num1,int num2){
		  if(num1<0||num2<0)
			  return -1;
		  int count = getDivisible2Count(num1,num2);
		  if(count==0)
			  return get2NumDiffer(num1,num2);
		  else{
			  int totalNum = count*2;
			  return get2NumDiffer(num1/totalNum,num2/totalNum)*totalNum;
		  }
	  }
	  
	  /**
	   * 获得两个数被2整除的次数
	   * @param num1
	   * @param num2
	   * @return
	   */
	  public static int getDivisible2Count(int num1,int num2){
		  if(num1%2==0&&num2%2==0){
			  return getDivisible2Count(num1/2,num2/2)+1;
		  }else{
			  return 0;
		  }
	  }
	  /**
	   * 得到两个数的最终差，就是两个数的小数和大数相减，差依旧和小数相减，直到差等于小数
	   * @param num1
	   * @param num2
	   * @return
	   */
	  public static int get2NumDiffer(int num1,int num2){
		  int biggerNum = num1>num2?num1:num2;
		  int smallerNum = biggerNum==num1?num2:num1;
		  int differ = biggerNum - smallerNum;
		  if(differ==smallerNum)
			  return differ;
		  else
			  return get2NumDiffer(differ,smallerNum);
	  }
	 
}
