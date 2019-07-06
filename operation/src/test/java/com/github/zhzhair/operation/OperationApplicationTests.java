package com.github.zhzhair.operation;

import com.github.zhzhair.operation.jdbc.CodeGeneratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationApplicationTests {

    @Test
    public void contextLoads() {
        //		String codeStr = CodeGeneratorUtils.getEntity();//输出所有表的实体类的内容
//		String codeStr = CodeGeneratorUtils.getEntity("feedback");//输出指定表的实体类的内容
        String codeStr = CodeGeneratorUtils.getRowMapper();//输出所有表的RowMapper的内容
//		String codeStr = CodeGeneratorUtils.getRowMapper("feedback");//输出指定表的RowMapper的内容
//		String codeStr = CodeGeneratorUtils.getSqls();//输出所有表增删改查的sql语句的内容
//		String codeStr = CodeGeneratorUtils.getSqls("feedback_reply");//输出指定表增删改查的sql语句的内容
        System.err.println(codeStr);
    }

}
