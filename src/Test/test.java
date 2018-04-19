package Test;

import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;   //不允许类外方法，所以要引用类

public class test {
    public static void main(String[] args){
        son S = new son(4);
        daughter D = new daughter();

        father F = D;           //父类可以是子类的一个instance
        //D = F;                //子类不可以转为父类
        D = (daughter)F;
        //(son) D;              //子类间不能转换
        F = S;


        ((son) F).Print();      //父类调用子类需加括号
        out.println();
        F.Printf();
        out.println();
        S.Print();             //子类可调用父类的方法

        out.println(S.b);
        out.println(((son) F).b);
        out.println(F instanceof son);

        List<String> t = new LinkedList<>();
        for(String i : t){
            System.out.println(i + "have????");
        }

        String S1 = "112";
        Integer v = Integer.valueOf(S1);
        out.println(v);
    }

    public static class father {
        int a;

        public void Printf(){
            out.println("father method " + this.getClass());
        }

    }
}
