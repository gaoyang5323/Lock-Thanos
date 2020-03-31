package com.kakuiwong.lockthanos.config;

import com.kakuiwong.lockthanos.bean.LockThanosConfigProperties;
import com.kakuiwong.lockthanos.core.aop.LockThanosMethodInterception;
import com.kakuiwong.lockthanos.core.lock.ThanosLockFactory;
import com.kakuiwong.lockthanos.core.util.LockThanosParamUtil;
import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaoyang
 * @email 785175323@qq.com
 */
@ConditionalOnProperty(name = LockThanosConfigProperties.PREFIX + ".address")
@Configuration
@EnableConfigurationProperties(LockThanosConfigProperties.class)
public class LockThanosAutoConfiguration {

    @Autowired
    private LockThanosConfigProperties properties;

    @ConditionalOnMissingBean
    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() {
        Config config = new Config();
        String[] address = properties.getAddress();
        if (address.length == 1) {
            config.useSingleServer().setAddress(address[0])
                    .setDatabase(properties.getDatabase())
                    .setPassword(properties.getPassword());
        } else {
            config.useClusterServers().setPassword(properties.getPassword())
                    .addNodeAddress(address);
        }
        config.setCodec(new JsonJacksonCodec());
        config.setEventLoopGroup(new NioEventLoopGroup());
        return Redisson.create(config);
    }

    @Bean
    public LockThanosParamUtil paramUtil() {
        return new LockThanosParamUtil();
    }

    @Bean
    public ThanosLockFactory thanosLockFactory() {
        return new ThanosLockFactory();
    }

    @Bean
    public LockThanosMethodInterception lockThanosMethodInterception() {
        return new LockThanosMethodInterception();
    }
}
