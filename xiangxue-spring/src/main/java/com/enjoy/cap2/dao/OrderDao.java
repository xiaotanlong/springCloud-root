package com.enjoy.cap2.dao;

/**
 * 在Spring2.0之前的版本中，@Repository注解可以标记在任何的类上，用来表明该类是用来执行与数据库相关的操作（即dao对象），并支持自动处理数据库操作产生的异常

 在Spring2.5版本中，引入了更多的Spring类注解：@Component,@Service,@Controller。@Component是一个通用的Spring容器管理的单例bean组件。而@Repository, @Service, @Controller就是针对不同的使用场景所采取的特定功能化的注解组件。

 因此，当你的一个类被@Component所注解，那么就意味着同样可以用@Repository, @Service, @Controller来替代它，同时这些注解会具备有更多的功能，而且功能各异。

 最后，如果你不知道要在项目的业务层采用@Service还是@Component注解。那么，@Service是一个更好的选择。

 就如上文所说的，@Repository早已被支持了在你的持久层作为一个标记可以去自动处理数据库操作产生的异常（译者注：因为原生的java操作数据库所产生的异常只定义了几种，但是产生数据库异常的原因却有很多种，
 这样对于数据库操作的报错排查造成了一定的影响；而Spring拓展了原生的持久层异常，
 针对不同的产生原因有了更多的异常进行描述。
 所以，在注解了@Repository的类上如果数据库操作中抛出了异常，就能对其进行处理，
 转而抛出的是翻译后的spring专属数据库异常，方便我们对异常进行排查处理）。
 */

import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {

}
