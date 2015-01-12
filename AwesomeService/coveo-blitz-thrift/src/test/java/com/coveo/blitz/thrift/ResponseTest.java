package com.coveo.blitz.thrift;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ResponseTest {

	@Test
	public void test() {
		Response response = new Response();
		DataPoint data1 = new DataPoint();
		Map<String, String> dimensions1 = new HashMap<>();
		dimensions1.put("VERB", "GET");
		dimensions1.put("DATE", "2014/01/01");
		data1.setDimensions(dimensions1);
		Map<String, Long> metrics1 = new HashMap<>();
		metrics1.put("COUNT", 123l);
		data1.setMetrics(metrics1);

		DataPoint data2 = new DataPoint();
		Map<String, String> dimensions2 = new HashMap<>();
		dimensions2.put("VERB", "GET");
		dimensions2.put("DATE", "2014/01/02");
		data2.setDimensions(dimensions2);
		Map<String, Long> metrics2 = new HashMap<>();
		metrics2.put("COUNT", 443l);
		data2.setMetrics(metrics2);

		response.setData(Arrays.asList(data1, data2));

		assertEquals(
				response.getData().get(0).getDimensions()
						.get("DATE"), "2014/01/01");
	}
}
