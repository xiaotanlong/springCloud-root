package concurrentProgramming.deadlock.dcl;

/**
 * 懒汉式-类初始化模式
 */
public class SingleInit {
    private SingleInit(){}

    //定义一个私有类，来持有当前类的实例
    //延迟占位模式。在单例类的内部由一个私有静态内部类来持有这个单例类的实例。
    private static class InstanceHolder{
        public static SingleInit instance = new SingleInit();
    }

    public static SingleInit getInstance(){
        return InstanceHolder.instance;
    }

}
