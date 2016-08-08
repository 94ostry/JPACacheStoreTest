package com.postrowski;

import java.util.UUID;

/**
 * Created by postrowski on 2016-08-05.
 */
public class BooIdGenerator
{
    public static String generateId()
    {
        return UUID.randomUUID().toString();
    }
}
