package me.code41.seed.worker.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * worker服务加载器 
 * User: wenjun 
 * Date: 2015-03-10 
 * Time: 10:50
 */
public class WorkerLauncher {

    private static final Logger logger = LoggerFactory.getLogger(WorkerLauncher.class);
    private static final String bootPath = WorkerLauncher.class.getName();
     
	public static void main(String[] args) {
		try{
			new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
			logger.info("{}分布式调度服务启动成功...", new Object[]{ bootPath });
			logger.info("{}分布式调度服务启动成功...", new Object[]{ bootPath });
			logger.info("{}分布式调度服务启动成功...", new Object[]{ bootPath });
			logger.info("{}分布式调度服务启动成功...", new Object[]{ bootPath });
			logger.info("{}分布式调度服务启动成功...", new Object[]{ bootPath });
		}catch(Throwable ex){
			logger.error("分布式调度服务启动异常:{}", new Object[]{ ex.getLocalizedMessage(), ex });
		}
        // 启动本地服务，然后hold住本地服务
        synchronized (WorkerLauncher.class) {
            while (true) {
                try {
                	WorkerLauncher.class.wait();
                } catch (InterruptedException ex) {
                	logger.error("{}服务异常终止:{}" , new Object[]{ bootPath, ex.getMessage(), ex});
                }
            }
        }
    }
}
