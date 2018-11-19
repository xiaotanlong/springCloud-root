package learn.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: java 反射执行
 * @date
 */
/*  java中class.forName()和classLoader都可用来对类进行加载。
          class.forName()前者除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。
          而classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,
               只有在newInstance才会去执行static块。
          Class.forName(name, initialize, loader)带参函数也可控制是否加载static块。
               并且只有调用了newInstance()方法采用调用构造函数，创建类的对象*/
public class ReflectionUtil {

    /**
     * 反射执行
     * @param className 类名
     * @param methodName 方法名
     * @param parameterTypes 参数类行
     * @param parameterValues 参数值
     * @return
     */
    public static Object publicMethodInvoke(String className,String methodName,Class<?> parameterTypes[],Object parameterValues []){
        try {
            Class classObj = Class.forName(className);
            Object classObjIn = classObj.newInstance();//实例化对象
            Method method = classObj.getMethod(methodName,parameterTypes);
            Object result = method.invoke(classObjIn,parameterValues);
            return result;
        }catch (Exception e){
            return "error";
        }

    }
    /**
     * 反射执行
     * @param className 类名
     * @param methodName 方法名
     * @param parameterType 参数类行
     * @param parameterValue 参数值
     * @return
     */
    public static Object publicMethodInvoke(String className,String methodName,Class<?> parameterType,Object parameterValue){
        try {
            Class classObj = Class.forName(className);
            Object classObjIn = classObj.newInstance();//实例化对象
            Method method = classObj.getMethod(methodName,parameterType);
            Object result = method.invoke(classObjIn,parameterValue);
            return result;
        }catch (Exception e){
            return "error";
        }

    }

    /**
     * 通过获取spring 的bean执行反射方法
     * @param args
     * @throws Exception
     */


    public static void main(String[] args) throws Exception{
        System.out.println(publicMethodInvoke("learn.reflection.Student","say",String.class,"rrrrr"));
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class ss = loader.loadClass("learn.reflection.Student");
        System.out.println("name-----------" + ss.getName());
        System.out.println("CanonicalName-----------" + ss.getCanonicalName());
        System.out.println("package-----------" + ss.getPackage());

        System.out.println("========================================================");

        Class s2 = Class.forName("learn.reflection.Student");
        Object obj1 = s2.newInstance();//实例化对象
        Method msss = s2.getMethod("say",String.class);
        Object ossss = msss.invoke(obj1,"eqeweqeqwe");
        System.out.println("ss---" + ossss.toString() );

        Method[] ms = s2.getMethods();
        for (Method m : ms){
            System.out.println("MName---" + m.getName() );
            Parameter[] pp = m.getParameters();
            if(pp != null){
                for (Parameter p : pp){
                    System.out.println("    PName---" + p.getName() );
                    System.out.println("    PType---" + p.getType() );
                }
            }
        }
    }
}
