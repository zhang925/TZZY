package com.zzy.util.path;

import org.apache.commons.codec.binary.BinaryCodec;

public class util_jarpath {

    public static void main(String a[]){
        Object ss = BinaryCodec.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(ss);
    }


}
