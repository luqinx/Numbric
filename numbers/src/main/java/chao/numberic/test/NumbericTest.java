package chao.numberic.test;

import org.junit.Test;

import java.util.Arrays;

import chao.numberic.Numberic;

/**
 * @author chao.qin
 * @since 2017/3/2
 */


public class NumbericTest {


    @Test
    public void toInteger() {
        int value = 0;


//        print("" + (byte)(-0x7f));

//        value = Numberic.toInteger(new byte[]{-0x02, -0x02, -0x02});

//        value = Numberic.toInteger(new byte[]{0x7f, 0x7f, 0x7f, 0x7f, 0x7f});
//        assertEquals(value, 0x7f7f7f7f);

//        value = Numberic.toInteger(new byte[]{-0x7f, -0x7f, -0x7f});
//        assertEquals(value, 0x7f7f7f);


//        value = Numberic.byteArrayToInt(new byte[]{-0x7f, -0x7f, -0x7f,-0x7f});
//        print(Integer.toHexString(value));

        print(0100);
        print(Numberic.toInteger(Numberic.toBytes("0100",8)));

    }

    @Test
    public void toBytes() {
        int value = 0xfff;
        print(Arrays.toString(Numberic.toBytes(value)));

        print(Integer.toHexString(Numberic.toInteger(Numberic.toBytes(value))));
    }

    @Test
    public void modifyValue() {

        print(Integer.toHexString(Numberic.modifyValue(0xffffffff,3,32,0x85)));

        print(Integer.toHexString(Numberic.modifyValue(0xffffffff,3,8,0xcc)));
    }

    @Test
    public void getValue() {
        print(Integer.toHexString(Numberic.getValue(0xffffffff,8,14)));
    }

    @Test
    public void toHexBytes() {
        String text = "0xabcd";


        print(Numberic.toInteger(Numberic.toBytes(text,16)));

        print(Integer.parseInt(text,16));
    }

    private void print(int value) {
        print("" + value);
    }
    private void print(String s) {
        System.out.println(s);
    }


}
