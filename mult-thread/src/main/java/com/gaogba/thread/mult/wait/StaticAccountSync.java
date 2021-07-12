package com.gaogba.thread.mult.wait;

/**
 * @author: Mr.Gao
 * @date: 2021/7/8 09:21
 * @description:
 */
public class StaticAccountSync {

    /**
     * 全局静态变量
     */
    private static final String msg = "i love java";

    // 创建静态类
    public static class staticInnerClass {
        private String userName = "userNameInput";
        private String pwd;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public void showMsg() {
            System.out.println("静态内部类展示信息:" + msg);
        }

    }

    // 创建非静态内部类
    public class InnerClass {
        public void disPlayMsg() {
            System.out.println("非静态内部类展示信息:" + msg);
        }
    }

    public static void main(String[] args) {
        // 创建静态内部类实例
        final InnerClass innerClass = new StaticAccountSync().new InnerClass();
        getStaticInstance();
    }


    public static void getStaticInstance() {
        String userName = new staticInnerClass().userName;
        System.out.println(userName);
    }
}
