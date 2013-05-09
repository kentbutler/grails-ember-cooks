dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            //dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            dbCreate = "update"
            driverClassName = "com.mysql.jdbc.Driver"
            url="jdbc:mysql://localhost/cook?createDatabaseIfNotExist=true&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true"
            username = "root"
            password = "dbpa55"
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            properties {
                minEvictableIdleTimeMillis = 180000
                timeBetweenEvictionRunsMillis = 180000
                testOnBorrow = true
                testWhileIdle = false
                testOnReturn = false
                validationQuery = "SELECT 1"
                maxActive=100
                initialSize = 10
                maxIdle=50
                minIdle=10
                removeAbandoned=false
            }

        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
