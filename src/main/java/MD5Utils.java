import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * md5加密解密类 Created by wangguibin on 14/12/18.
 */
public class MD5Utils {

    public static final String MD5_KEY = "nfxq_md5_key";

    /**
     * 加密
     *
     * @param param
     *            待加密的值
     * @param salt
     *            盐值
     * @return 加密后的数值
     */
    public static String encode(String param, String salt) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 采用”原文+盐值“的组合方式
            md.update((param + salt).getBytes());
            byte b[] = md.digest();
            int i;
            // 生成32位长的字符串
            StringBuilder builder = new StringBuilder(32);
            // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                // 如果为负值将其转换为正值
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    builder.append("0");
                }
                // Byte类并不提供直接转化为16进制数的方法，所以要通过Integer类的toHexString方法来实现，将i转换为16进制的数值。
                builder.append(Integer.toHexString(i));
            }
            result = builder.toString();
        } catch (NoSuchAlgorithmException e) {
//            Log.e("MD5Utils", "MD5Utils encode error param : " + param
//                    + " salt : " + salt, e);
        }
        return result;
    }

}
