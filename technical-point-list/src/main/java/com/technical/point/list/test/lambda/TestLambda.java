package com.technical.point.list.test.lambda;

/**
 * @author: Mr.Gao
 * @date: 2021/11/30 10:36
 * @description:
 */
public class TestLambda {

    /**
     * 静态内部类方式实现
     */
    static class Like1 implements ILike {
        @Override
        public void lambda() {
            System.out.println("静态内部类(static)方式实现!");
        }
    }

    public static void main(String[] args) {
        //1、传统方式(子类)实现
        ILike like = new Like();
        like.lambda();

        //2.静态内部类方式实现
        like = new Like1();
        like.lambda();

        //3、局部内部类方式实现
        class Like2 implements ILike {
            @Override
            public void lambda() {
                System.out.println("局部内部类方式实现!");
            }
        }
        like = new Like2();
        like.lambda();

        //4、匿名内部类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("匿名内部类方式实现!");
            }
        };
        like.lambda();

        //5、jdk lambda表达式
        like = () -> System.out.println("lambda表达式方式实现!");
        like.lambda();
    }

}

/**
 * 函数式接口:
 * 任何接口如果只包含唯一一个抽象方法,那么它就是一个函数式接口
 */
interface ILike {
    /**
     * print 表达式
     */
    void lambda();
}

/**
 * 1.传统方式(子类)实现
 */
class Like implements ILike {

    @Override
    public void lambda() {
        System.out.println("传统方式(子类) >> implements << 实现!");
    }
}