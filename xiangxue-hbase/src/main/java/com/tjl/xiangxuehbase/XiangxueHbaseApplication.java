package com.tjl.xiangxuehbase;

import com.tjl.xiangxuehbase.hbase.HbaseTemplate;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.tjl.xiangxuehbase.service")
public class XiangxueHbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiangxueHbaseApplication.class, args);
	}

	@Bean(destroyMethod = "closeConnection")
	public HbaseTemplate hbaseTemplate(@Value("${hbase.zookeeper.quorum}") String quorum,
									   @Value("${hbase.zookeeper.port}") String port) {
		HbaseTemplate hbaseTemplate = new HbaseTemplate();
		org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", quorum);
		conf.set("hbase.zookeeper.port", port);
		conf.set("hbase.client.ipc.pool.type","RoundRobinPool");
		conf.set("hbase.client.ipc.pool.size","1");
		hbaseTemplate.setConfiguration(conf);
		//hbaseTemplate.getConfiguration();
		return hbaseTemplate;
	}
}
