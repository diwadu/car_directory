package pl.dc4b.cardirectory.dao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Mark property which you want to ignore in SQL queries in current DAO implementation
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreInSql {
}
