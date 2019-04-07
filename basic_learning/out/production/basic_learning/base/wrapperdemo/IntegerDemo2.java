// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IntegerDemo2.java

package base.wrapperdemo;

import java.io.PrintStream;

public class IntegerDemo2
{

	public IntegerDemo2()
	{
	}

	public static void main(String args[])
	{
		Integer i1 = new Integer(127);
		Integer i2 = new Integer(127);
		System.out.println(i1 == i2);
		System.out.println(i1.equals(i2));
		System.out.println("-------------------");
		Integer i3 = new Integer(128);
		Integer i4 = new Integer(128);
		System.out.println(i3 == i4);
		System.out.println(i3.equals(i4));
		System.out.println("-----------");
		Integer i5 = Integer.valueOf(128);
		Integer i6 = Integer.valueOf(128);
		System.out.println(i5 == i6);
		System.out.println(i5.equals(i6));
		System.out.println("-----------");
		Integer i7 = Integer.valueOf(127);
		Integer i8 = Integer.valueOf(127);
		System.out.println(i7 == i8);
		System.out.println(i7.equals(i8));
	}
}
