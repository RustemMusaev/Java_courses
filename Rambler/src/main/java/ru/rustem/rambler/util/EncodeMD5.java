package ru.rustem.rambler.util;

import org.apache.commons.codec.digest.DigestUtils;


public class EncodeMD5 implements EncoreMethod {
    @Override
    public String encode(String pwd) {
        return DigestUtils.md5Hex(pwd);
    }
}
