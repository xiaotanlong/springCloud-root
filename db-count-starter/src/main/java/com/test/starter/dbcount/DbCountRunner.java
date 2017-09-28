package com.test.starter.dbcount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * @ProjectName springcloudroot
 * @PackageName com.test.starter.dbcount
 * @Author tanjianglong
 * @CreatedTime 2017/9/21.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
public class DbCountRunner implements CommandLineRunner {

    protected final Logger logger = LoggerFactory.getLogger(DbCountRunner.class);
    private Collection<CrudRepository> repositories;

    public DbCountRunner(Collection<CrudRepository> repositories) {
        this.repositories = repositories;
    }
    @Override
    public void run(String... strings) throws Exception {
        repositories.forEach(crudRepository -> {
            logger.info(String.format("%s has %s entries",
                    getRepositoryName(crudRepository.getClass()),
                    crudRepository.count()));
        });
    }

    protected static String getRepositoryName(Class crudRepositoryClass) {
        for (Class repositoryInterface : crudRepositoryClass.getInterfaces()) {
            if (repositoryInterface.getName().startsWith("com.test.bookpub.repository")) {
                return repositoryInterface.getSimpleName();
            }
        }
        return "UnknownRepository";
    }
}
