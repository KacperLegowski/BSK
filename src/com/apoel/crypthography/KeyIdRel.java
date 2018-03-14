package com.apoel.crypthography;

import java.io.Serializable;

public class KeyIdRel implements Serializable{
    private int id;
    private byte[] key;

    public KeyIdRel(int id, byte[] key) {
        this.id = id;
        this.key = key;
    }
}
