package stream.test;

import org.junit.Test;

import java.util.Arrays;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/2/11 10:47 AM
 */
public class StreamTest {

    @Test
    public void test1() {
        String[] profiles = new String[]{"aaa", "bbb",null, "prod"};

//        Boolean isProd = Arrays.stream(profiles).anyMatch((env) -> {
////            if (env.equals("prod")) {
////                return true;
////            }
//            if ("prod".equals(env)) {
//                return true;
//            }
//            return false;
//        });
//
//        System.out.println(isProd);
//
//        Boolean www = null;
//        List<String> list = Arrays.asList(profiles);
//        if (list.contains("prod")) {
//            www = true;
//        } else {
//            www = false;
//        }
//        System.out.println(www);

        Arrays.asList(profiles).stream().filter(t -> t != null).filter(t -> t.equals("prod")).forEach(System.out::println);
    }
}
