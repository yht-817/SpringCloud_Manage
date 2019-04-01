package net.sunwukong.www.chat.server.aop;

import net.sunwukong.www.chat.server.config.db.DataSourceContextHolder;
import net.sunwukong.www.chat.server.config.db.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * 在service层觉得数据源
 * 
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行
 * 如果一旦开始切换到写库，则之后的读都会走写库
 * 
 * @author Jfei
 *
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
public class DataSourceAop implements PriorityOrdered {
	
	@Before("execution(* net.sunwukong.www.chat.server.service..*.find*(..)) ||  " +
			"execution(* net.sunwukong.www.chat.server.service..*.get*(..)) || " +
			"execution(* net.sunwukong.www.chat.server.service..*.red*(..)) || " +
			"execution(* net.sunwukong.www.chat.server.service..*.query*(..))")
    public void setReadDataSourceType() {
		//如果已经开启写事务了，那之后的所有读都从写库读
		if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getJdbcType())){
			DataSourceContextHolder.read();
		}
        
    }

    @Before("execution(* net.sunwukong.www.chat.server.service..*.insert*(..)) || " +
			"execution(* net.sunwukong.www.chat.server.service..*.update*(..)) || " +
			"execution(* net.sunwukong.www.chat.server.service..*.write*(..)) || " +
			"execution(* net.sunwukong.www.chat.server.service..*.add*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
    }
    
	@Override
	public int getOrder() {
		/**
		 * 值越小，越优先执行
		 * 要优于事务的执行
		 * 在启动类中加上了@EnableTransactionManagement(order = 10) 
		 */
		return 1;
	}

}
