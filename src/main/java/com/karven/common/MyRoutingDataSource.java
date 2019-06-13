package com.karven.common;

//import com.sun.istack.internal.Nullable;
import com.sun.istack.internal.Nullable;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.lang.Nullable;

public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }

}