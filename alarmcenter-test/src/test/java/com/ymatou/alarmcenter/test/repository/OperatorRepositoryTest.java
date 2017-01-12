package com.ymatou.alarmcenter.test.repository;

import com.ymatou.alarmcenter.domain.model.Operator;
import com.ymatou.alarmcenter.domain.repository.OperatorRepository;
import com.ymatou.alarmcenter.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhangxiaoming on 2016/12/21.
 */
public class OperatorRepositoryTest extends BaseTest {
    @Resource
    private OperatorRepository operatorRepository;

    @Test
    public void test1() {
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Operator operator = operatorRepository.getOperatorByLoginId("admin");

        if (operator != null) {
            operator.setPassword(encoder.encode("123456"));
            operator.setRoles(roles);
        } else {
            operator = new Operator();
            operator.setRoles(roles);
            operator.setId(UUID.randomUUID().toString().replace("-", ""));
            operator.setName("admin");
            operator.setLoginId("admin");
            operator.setPassword(encoder.encode("123456"));
        }
        operatorRepository.saveOperator(operator);
        Operator operator1 = operatorRepository.getOperatorByLoginId("admin");
        Assert.assertNotNull(operator1);
        boolean flag = encoder.matches("123456", operator1.getPassword());
        Assert.assertTrue(flag);
    }
}
