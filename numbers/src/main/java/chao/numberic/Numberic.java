package chao.numberic;

import org.jetbrains.annotations.Contract;

public class Numberic {

    /**
     * 将字符串转成整型
     */
    public static int toInteger(String text) {
        int value;
        try {
            value = Integer.parseInt(text);
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }

    /**
     * 将整型转成十六进制字符串，一般用于log打印
     */
    public static String toHexString(int value) {
        return "0x" + Integer.toHexString(value);
    }

    /**
     *  将字节数组转成十六进制字符串，一般用于log打印
     */
    public static String toHexString(byte[] bytes) {
        return "0x" + Integer.toHexString(toInteger(bytes));
    }


    /**
     *
     * 字节转整型
     *
     * @param bytes 输入byte[]
     *
     * @return    输出int
     */
    public static int toInteger(byte[] bytes) {
        int value = 0;
        int len = bytes.length;
        byte[] newBytes = bytes;

        //1. 如果bytes长度大于4，默认抛异常
        if (len > 4) {
            throw new IllegalArgumentException("less than 4 bytes can convert to an integer.");
        }

        //2. 可以通过以下代码取bytes的后4个字节来转成int
//        if (len > 4) {
//            newBytes = new byte[4];
//            System.arraycopy(bytes,len - 4,newBytes,0,4);
//            len = 4;
//        }

        //3. 或者可以通过以下代码取bytes的前4个字节来转成int
//        if (len > 4) {
//            newBytes = new byte[4];
//            System.arraycopy(bytes,0,newBytes,0,4);
//            len = 4;
//        }

        for (int i=0;i < len; i++) {
            byte b = newBytes[i];
            value <<= 8;
            value = (b & 0xff) | value;
        }
        return value;
    }

    /**
     *
     *
     * 将radix进制的字符串转成byte[]
     *
     *
     * @param radix 进制，支持二进制，八进制，十进制等，进制范围2-36
     *
     * @return 返回一个长度为4的字节数组
     *
     */
    public static byte[] toBytes(String value,int radix) {
        value = value.replaceAll("0x","");
        return toBytes(Integer.parseInt(value,radix));
    }

    /**
     *
     * 将整型转成字节数组
     *
     * @return 返回一个长度为4的字节数组
     */
    @Contract(pure = true)
    public static byte[] toBytes(int value) {
        byte[] bytes = new byte[4];
        for (int i=bytes.length - 1;i >= 0;i--) {
            bytes[i] = (byte) (value & 0xff);
            value >>>= 8;//无符号移位
        }
        return bytes;
    }

    /**
     *
     * 此方法将 srcValue从第modifyFrom比特位开始，modifyLen长度这段部分修改为modify
     *
     * @param srcValue    待修改的值
     * @param modifyFrom  起始位置 取值范围0-31
     * @param modifyLen   长度     取值范围0-32
     * @param modify      插入的值
     * @return   修改后的值
     *
     * example:
     * srcValue 10101010 10101010 10101010 10101010,from 8,len 8, modify 11110000
     * mask:    00000000 11111111 00000000 00000000
     * src :    10101010 10101010 10101010 10101010
     * modify:  00000000 11110000 00000000 00000000
     * result:  10101010 11110000 10101010 10101010
     */
    public static int modifyValue(int srcValue,int modifyFrom,int modifyLen,int modify) {
        if (modifyFrom > 31 || modifyFrom < 0) {
            throw new IllegalArgumentException("error in modify index :" + modifyFrom);
        }
        if (modifyLen > 32 || modifyLen < 0) {
            throw new IllegalArgumentException("error in modify length : " + modifyLen);
        }
        if (modifyFrom + modifyLen > 32) {
            throw new IllegalArgumentException("modifyFrom + modifyLen should be equals or less than 32, but " + (modifyFrom + modifyLen));
        }
        if(modify > ((1 << modifyLen) - 1)) {
            throw new IllegalStateException("modify(" + modify + ") value should be less than ((1 << modifyLen) -1):" + ((1 << modifyLen) -  1));
        }

        final long mask;
        mask = ((1 << modifyLen) - 1) << (32 - modifyLen - modifyFrom);

        int valueInNew = modify << (32 - modifyLen - modifyFrom);

        return (int) ((srcValue & ~mask) | valueInNew);

    }

    /**
     * 从原值提取新值
     *
     * @param srcValue 原值
     * @param from  起始位置,  取值范围（0-31）
     * @param len   长度,      取值范围（0-32）
     * @return      提取后的值
     *
     * example:
     * srcValue 11111111 00000000 11001100 00000011, from 4 ,len 8
     * mask:    00001111 11110000 00000000 00000000
     * result:      1111 0000                      = 0xf0
     */
    public static int getValue(int srcValue,int from,int len) {

        if (from > 31 || from < 0) {
            throw new IllegalArgumentException("error in modify index :" + from);
        }
        if (len > 32 || len < 0) {
            throw new IllegalArgumentException("error in modify length : " + len);
        }
        if (from + len > 32) {
            throw new IllegalArgumentException("modifyFrom + modifyLen should be equals or less than 32, but " + (from + len));
        }

        int value = srcValue;

        value = value << from;

        value = value >>> (32 - len);

        return value;
    }


}
