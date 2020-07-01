package com.linzx.tinyid;

import com.linzx.WebAdminApplication;
import com.linzx.core.framework.tinyid.IdGeneratorFactory;
import com.xiaoju.uemc.tinyid.base.generator.IdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebAdminApplication.class)
public class IdGeneratorTest {

    @Autowired
    IdGeneratorFactory idGeneratorFactory;

    @Test
    public void testNextId() {
        IdGenerator idGenerator = idGeneratorFactory.getIdGenerator("test");
//        Long id = idGenerator.nextId();
        List<Long> list = idGenerator.nextId(3);
        System.out.println(list);
//        System.out.println("current id is: " + id);
    }

}
