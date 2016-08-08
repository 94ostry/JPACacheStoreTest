package com.postrowski.cache;

import com.postrowski.Boo;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.persistence.jpa.configuration.JpaStoreConfigurationBuilder;
import org.infinispan.transaction.TransactionMode;
import org.infinispan.transaction.lookup.GenericTransactionManagerLookup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Created by postrowski on 2016-07-27.
 */
@ApplicationScoped
public class CacheProvider
{
   private EmbeddedCacheManager cacheManager;

    @PostConstruct
    private void init()
    {
        final GlobalConfiguration globalConfig =
            new GlobalConfigurationBuilder().nonClusteredDefault().globalJmxStatistics()
                .allowDuplicateDomains( true ).build();

        /*
        final Configuration entityDefaultConfig =
            new ConfigurationBuilder().transaction().transactionMode( TransactionMode.TRANSACTIONAL )
                .eviction().strategy( EvictionStrategy.NONE ).build();
        */

        Configuration cacheConfig = new ConfigurationBuilder()
            .transaction()
                .transactionMode( TransactionMode.TRANSACTIONAL )
            .transactionManagerLookup(new GenericTransactionManagerLookup())
            //.loaders().passivation(false).preload(false).shared(false)
            .persistence()
                .addStore(JpaStoreConfigurationBuilder.class)
                .persistenceUnitName("postgresPU")
                .entityClass(Boo.class)
            .build();

        cacheManager = new DefaultCacheManager( globalConfig  );
        cacheManager.defineConfiguration( "Boos", cacheConfig );


    }


    @Produces
    public Cache<String, Boo> createEntityCache()
    {
        final Cache<String, Boo> entityCache = cacheManager.getCache( "Boos" );
        return entityCache;
    }


    //@Produces
    //public UniqueIndex getUniqueIndex()
    //{
        //final Cache<Long, Boolean> cache = cacheManager.getCache( "TEST_CACHE" );
        //return new UniqueIndex( cache );
    //}
}
