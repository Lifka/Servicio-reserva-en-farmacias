package com.red.lifka.sisfarmaapp.DB;

public class Crypt {

    public Crypt(){}

    public String encrypt(String str) {
        try {
            MCrypt keccak = new MCrypt();
            return keccak.bytesToHex(keccak.encrypt(str));
        } catch (Exception e){
            return str;
        }
    }
}
