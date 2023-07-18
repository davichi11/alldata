package cn.datax.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步方法配置类
 *
 * @author huchunliang
 * @date 2023/07/18
 */
@Configuration
public class SpringAsyncConfig {

    @Bean(name = "taskExecutors")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //当前系统的核心线程数
        int processors = Runtime.getRuntime().availableProcessors();
        taskExecutor.setCorePoolSize(processors);
        //最大线程数为核心线程数的1倍
        taskExecutor.setMaxPoolSize(processors << 1);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("taskExecutors-thread-");
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        taskExecutor.setAwaitTerminationSeconds(20);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
