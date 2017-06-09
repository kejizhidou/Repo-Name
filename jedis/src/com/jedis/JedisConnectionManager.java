package com.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedisConnectionManager {
	
	    private static  JedisConnectionManager instance =null;
	    private static JedisPool pool;
	    private JedisConnectionManager() {
	    	connect();
	    }

	public final static JedisConnectionManager getInstance() {
		if (instance == null) {
			synchronized (JedisConnectionManager.class) {
				if (instance == null) {
					instance = new JedisConnectionManager();
				}
			}
		}
		return instance;
	}

	    private void connect() {
	    	
	        // Create and set a JedisPoolConfig
	        JedisPoolConfig poolConfig = new JedisPoolConfig();
	        poolConfig.setMaxWaitMillis(8000);//10000
	        poolConfig.setTestOnBorrow(true);
	        poolConfig.setTestOnReturn(true);
	        poolConfig.setTestWhileIdle(true);
	        poolConfig.setNumTestsPerEvictionRun(50);
	        poolConfig.setMinEvictableIdleTimeMillis(60000);
	        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
	        poolConfig.setMaxTotal(2000);
	        poolConfig.setMaxIdle(150);
	        poolConfig.setMinIdle(100);
	        pool = new JedisPool(poolConfig, "127.0.0.1", 6379, 0);
	        }
	    public void release() {
	        pool.destroy();
	    }
	    public Jedis getJedis() {
	    	StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	    	StackTraceElement se = stacktrace[2];//maybe this number needs to be corrected
//	    	System.err.println("Called from "+se.getClassName()+"."+se.getMethodName());
			Jedis jedis = null;
			int cnt = 1;
			do {
				try {
					cnt++;
					if(cnt>=1000) {
						System.out.println("Not getting Jedis even after "+cnt+" Attempts.");
						return null;
					}
					jedis = pool.getResource();
				} catch (Exception e) {
					System.err.println("("+cnt+") Got exception during getting jedis connection. "+e.getMessage()+" Called from "+se.getClassName()+"."+se.getMethodName());
					if(cnt>3) {
						e.printStackTrace();
					}
				}
			} while(jedis==null);
			if(cnt>2) {
				System.out.println("Got Jedis after "+cnt+" Attempts.");
			}
			return jedis;
	    }

	    public void returnJedis(Jedis jedis) {
	        pool.returnResource(jedis);
	    }

}