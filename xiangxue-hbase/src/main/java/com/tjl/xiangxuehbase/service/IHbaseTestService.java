package com.tjl.xiangxuehbase.service;

import com.tjl.xiangxuehbase.hbase.bean.Emp;
import com.tjl.xiangxuehbase.service.base.IHbaseBaseService;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 测试业务类实现
 * @date 2019/2/21 14:53
 */
@Service
public class IHbaseTestService extends IHbaseBaseService<Emp> implements HbaseTestService{

    /*@Autowired
    private HbaseTemplate hbaseTemplate;*/

    @PostConstruct
    public void test() throws Exception{
        this.get(Emp.class,"emp","1");
        this.get("emp","1");
    }
}
