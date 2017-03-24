##Numbric

Numberic 是一个数字处理工具，不仅可以进行int，byte和字符串相互转换，
还可以对一个int进行按位取数或者按位改数功能,一个整型int在java中占4个字节
32个bit位，按位取数是取整型int中从参数from位开始长度为参数len这段比特位代表
的数字，按位改数同理是将这段比特位数字修改为指定值.

1. 按位取数 Numbric.getValue(int srcValue,int from,int len)
````
   从原值提取新值
   
   @param srcValue 原值
   @param from  起始位置,  取值范围（0-31）
   @param len   长度,      取值范围（0-32）
   @return      提取后的值
   
   example: from = 4 ,len = 8
   srcValue 11111111 00000000 11001100 00000011, 
   mask:    00001111 11110000 00000000 00000000
   result:      1111 0000                      = 0xf0
 `````

2. 按位改数 Numbric.modifyValue(int srcValue,int modifyFrom,int modifyLen,int modify)

````
    此方法将 srcValue从第modifyFrom比特位开始，modifyLen长度这段部分修改为modify
    
    @param srcValue    待修改的值
    @param modifyFrom  起始位置 取值范围0-31
    @param modifyLen   长度     取值范围0-32
    @param modify      插入的值
    @return   修改后的值
    
    example: from = 8,len = 8, modify 11110000
    srcValue 10101010 10101010 10101010 10101010
    mask:    00000000 11111111 00000000 00000000
    src :    10101010 10101010 10101010 10101010
    modify:  00000000 11110000 00000000 00000000
    result:  10101010 11110000 10101010 10101010
````

Numbric使用的是java语言，只要稍作调整就可以替换到c，c++等其他语言环境


### Numbric 可用于通过蓝牙修改硬件寄存器值等数字处理情形。
