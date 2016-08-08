package com.postrowski;

import org.infinispan.Cache;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Created by postrowski on 2016-08-05.
 */
@Stateless
public class BooService
{
    @Inject
    private Cache<String, Boo> booCache;

    //@Resource
    //private   UserTransaction utx;

    public Boo find( long id )
    {
        return null;
    }

    public void persist( Boo boo )
    {
        //try
        //{
            //utx.begin();

            final String id = BooIdGenerator.generateId();
            boo.setId( id );

            booCache.put( boo.getId(), boo );

        /*
            utx.commit();

        }
        catch( Exception ex )
        {
            try
            {
                utx.rollback();
            }
            catch( SystemException e )
            {
                e.printStackTrace();
            }
        }
        */
    }

    public Boo update( Boo boo )
    {
        return null;
    }
}
