package com.example.new_starter.config;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Log
public class SystemAddressUtil {

    public static String getSystemAddress() {
        try {
            // Get the local host address (the IP address of the machine running the application)
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress(); // You can also use inetAddress.getHostName() for the hostname
        } catch (UnknownHostException e) {
            // If the host address can't be determined, return a default value or log the exception
            log.info("SYSTEM ADDRESS ERROR: ".concat(e.getMessage()));
            return "UnknownHost";
        }
    }
}
