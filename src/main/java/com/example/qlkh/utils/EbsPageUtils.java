package com.example.qlkh.utils;

import com.example.qlkh.error.status.CommonStatus;
import com.example.qlkh.exception.DataRuntimeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Component
public class EbsPageUtils {
    private static EntityManager manager;

    public EbsPageUtils(EntityManager entityManager) {
        manager = entityManager;
    }

    public static  <T> Page<T> getPage(@NotNull Class<T> resultType, StringBuilder sql, @NotNull Map<String, Object> parameters, @NotNull Pageable pageable) {
        List<T> elements = getElementsByJpa(resultType, sql, parameters, pageable);
        long numberOfElements = getNumberOfElements(sql, parameters);
        return new PageImpl<>(elements, pageable, numberOfElements);
    }

    public static  <T> List<T> getElementsByJpa(@NotNull Class<T> resultType, @NotNull StringBuilder sql, @NotNull Map<String, Object> parameters, Pageable pageable) {
        TypedQuery<T> query = createQuery(resultType, sql, parameters);
        if (pageable != null) {
            int startPosition = pageable.getPageNumber() * pageable.getPageSize();
            query.setFirstResult(startPosition);
            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

    public static  <T> List<T> getElementsJpa(@NotNull Class<T> resultType, @NotNull StringBuilder sql, @NotNull Map<String, Object> parameters) {
        return getElementsByJpa(resultType, sql, parameters, null);
    }

    public static  <T> List<T> getElementsByNative(@NotNull Class<T> resultType, @NotNull StringBuilder sql, @NotNull Map<String, Object> parameters, Pageable pageable) {
        Query query = createNativeQuery(resultType, sql, parameters);
        if (pageable != null) {
            int startPosition = pageable.getPageNumber() * pageable.getPageSize();
            query.setFirstResult(startPosition);
            query.setMaxResults(pageable.getPageSize());
        }
        return query.getResultList();
    }

    public static long getNumberOfElements(@NotNull StringBuilder sql, @NotNull Map<String, Object> parameters) {
        StringBuilder localSql = new StringBuilder(sql);
        String lowerCaseSql = localSql.toString().toLowerCase();

        int index = lowerCaseSql.indexOf("from");
        if (index == -1) {
            throw new DataRuntimeException(CommonStatus.SQL_WRONG_FORMAT);
        }

        localSql.replace(0, index, "select count(*) ");
        index = lowerCaseSql.indexOf("order");
        if (index != -1) {
            localSql.replace(index - 5, localSql.length(), "");
        }

        TypedQuery<Long> countQuery = createQuery(Long.class, localSql, parameters);
        return countQuery.getSingleResult();
    }

    public static  <T> TypedQuery<T> createQuery(@NotNull Class<T> resultType, @NotNull StringBuilder sql, @NotNull Map<String, Object> parameters) {
        TypedQuery<T> query = manager.createQuery(sql.toString(), resultType);
        if (!CollectionUtils.isEmpty(parameters)) {
            parameters.forEach(query::setParameter);
        }
        return query;
    }

    public static <T> Query createNativeQuery(@NotNull Class<T> resultType, @NotNull StringBuilder sql, @NotNull Map<String, Object> parameters) {
        Query query = manager.createNativeQuery(sql.toString(), resultType);
        if (!CollectionUtils.isEmpty(parameters)) {
            parameters.forEach(query::setParameter);
        }
        return query;
    }
}
