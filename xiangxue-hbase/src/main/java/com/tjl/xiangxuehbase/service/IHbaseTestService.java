package com.tjl.xiangxuehbase.service;

import com.tjl.xiangxuehbase.hbase.bean.Emp;
import com.tjl.xiangxuehbase.service.base.IHbaseBaseService;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;

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
        Emp e = this.get(Emp.class,"erp_contact_test","1");
        //List<Emp> e3 = this.scan(Emp.class,"erp_contact_test");
        System.out.println(e.getName() + "==================");
        //this.get("emp","1");

        //System.out.println(e3.get(0) + "==================");
    }
}
