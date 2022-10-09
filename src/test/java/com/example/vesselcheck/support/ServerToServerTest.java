package com.example.vesselcheck.support;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ServerToServerTest {


    @Test
    @DisplayName("server To server")
    public void serverToServer() throws Exception{



        new ServerToServer().hello();
    }
}