package com.example.shop.global.setting;

import com.example.shop.global.config.RedisTestConfig;
import com.example.shop.global.config.RedisTestContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(RedisTestConfig.class)
public abstract class RedisTest extends RedisTestContainer {
}
