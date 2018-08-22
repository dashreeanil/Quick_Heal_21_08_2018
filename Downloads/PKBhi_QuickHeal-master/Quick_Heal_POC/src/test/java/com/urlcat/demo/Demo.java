package com.urlcat.demo;

import org.testng.annotations.Test;

import Com.Lib.GenericLib;

public class Demo {

	@Test
	public void demo() throws Exception
	{
		GenericLib.validateDomain();
	}
}
