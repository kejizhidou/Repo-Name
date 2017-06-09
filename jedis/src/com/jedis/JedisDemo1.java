package com.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisDemo1 {

	@Test
	public void testDemo1() {
		try {

			Jedis jedis = new Jedis("192.168.192.128", 6379);//192.168.192.128   59.110.232.150
			System.out.println("jedis.isConnected():" + jedis.isConnected());
			jedis.set("name", "cuiwjava");
			jedis.get("name");
			String value = jedis.get("name");
			System.out.println(" value :" + value);
			jedis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
