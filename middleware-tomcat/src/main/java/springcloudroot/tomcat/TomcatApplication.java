package springcloudroot.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import springcloudroot.tomcat.servlet.TestServlet;

public class TomcatApplication {
	//启动嵌入式tomcat
	public static class startTomcat{
		public void init() throws Exception{
			//把目录的绝对的路径获取到
			String classpath = System.getProperty("user.dir");
			System.out.println(classpath);
			//new一个Tomcat
			Tomcat tomcat = new Tomcat();
			//设置Tomcat的端口
			//tomcat.setPort(9090);
			//连接器
			Connector connector = tomcat.getConnector();
			connector.setPort(9091);
			connector.setMaxParameterCount(2);

			//connector.setMaxThread();
			//设置Host
			Host host = tomcat.getHost();
			//我们会根据xml配置文件来
			host.setName("localhost");
			host.setAppBase("webapps");
			//前面的那个步骤只是把Tomcat起起来了，但是没啥东西
			//要把class加载进来,把启动的工程加入进来了
			Context context =tomcat.addContext(host, "/", classpath);

			if(context instanceof StandardContext){
				StandardContext standardContext = (StandardContext)context;
				standardContext.setDefaultContextXml("../../main/resources/web.xml");
				//把Servlet设置进去
				Wrapper wrapper =  tomcat.addServlet("/", "DemoServlet", new TestServlet());
				wrapper.addMapping("/test");
				/*//spring 的 TestController 不能直接解析
				Wrapper wrapper2 =  tomcat.addServlet("/", "TestController", "springcloudroot.tomcat.servlet.TestController");
				wrapper2.addMapping("/testCon");*/
			}
			//Tomcat跑起来
			tomcat.start();
			//强制Tomcat server等待，避免main线程执行结束后关闭
			tomcat.getServer().await();
		}

	}

	public static void main(String[] args) throws Exception
	{
		//SpringApplication.run(TomcatApplication.class, args);
		new startTomcat().init();
	}
}


