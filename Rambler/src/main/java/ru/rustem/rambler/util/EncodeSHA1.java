package ru.rustem.rambler.util;

import org.apache.commons.codec.digest.DigestUtils;


public class EncodeSHA1 implements EncoreMethod {
    @Override
    public String encode(String pwd) {
        return DigestUtils.sha1Hex(pwd);
    }
}
