package learn.beanshell;

import bsh.EvalError;
import bsh.Interpreter;

import java.io.IOException;
import java.util.Date;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: beanshell test(用一句话描述该文件做什么)
 * @date
 */
public class test {
    public static void main(String[] args) {
        Interpreter i = new Interpreter();  // Construct an interpreter
        try {
            // Set variables
            i.set("var1", 5);
            i.set("date", new Date() );

            Date date = (Date)i.get("date");
            System.out.println("CurrentDate:" + date );
            // Eval a statement and get the result
            i.eval("bar = var1*10 > 50 ? 100 : 10000");

            // retrieve a variable
            System.out.println( i.get("bar") );

            // Source an external script file
            /*try {
               // Object obj = i.source("demo.bsh");
                System.out.println( "source result:" + obj);
            }
            catch ( IOException e) {
                e.printStackTrace();
            }*/
        }
        catch (EvalError e) {
            e.printStackTrace();
        }
    }
}
