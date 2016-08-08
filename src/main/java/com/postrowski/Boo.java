package com.postrowski;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Created by postrowski on 2016-08-02.
 */
@Entity
public class Boo implements Serializable
{
    @Id
    private String id;

    //@Column( unique = true )
    private long number;

    @Column
    private String subBooId;

    private String name;

    @Version
    private Long version;

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public long getNumber()
    {
        return number;
    }

    public void setNumber( long number )
    {
        this.number = number;
    }

    public String getSubBooId()
    {
        return subBooId;
    }

    public void setSubBooId( String subBooId )
    {
        this.subBooId = subBooId;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion( Long version )
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "Boo{" +
            "id='" + id + '\'' +
            ", subBooId='" + subBooId + '\'' +
            '}';
    }
}
