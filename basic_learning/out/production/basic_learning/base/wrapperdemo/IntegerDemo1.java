// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IntegerDemo1.java

package base.wrapperdemo;

import java.io.PrintStream;

public class IntegerDemo1
{

	public IntegerDemo1()
	{
	}

	public static void main(String args[])
	{
		Integer i = Integer.valueOf(100);
		i = Integer.valueOf(i.intValue() + 200);
		System.out.println((new StringBuilder()).append("i=").append(i).toString());
	}
}
