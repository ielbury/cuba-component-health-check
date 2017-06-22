package de.diedavids.cuba.healthcheck.data

import com.haulmont.chile.core.model.MetaClass
import com.haulmont.cuba.core.entity.BaseUuidEntity
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.Metadata
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

import javax.inject.Inject

@CompileStatic
@Component(SimpleDataLoader.NAME)
class SimpleDataLoaderBean implements SimpleDataLoader {

    @Inject
    DataManager dataManager

    @Inject
    Metadata metadata


    @Override
    <E extends BaseUuidEntity> E load(Class<E> entityClass, UUID id, String view = '_local') {

        LoadContext<E> loadContext = getLoadContext(entityClass)
                .setId(id)
                .setView(view)

        dataManager.load(loadContext)
    }


    @Override
    <E extends BaseUuidEntity> E loadByProperty(Class<E> entityClass, String propertyPath, String propertyValue, String view = '_local') {

        LoadContext.Query query = createQueryByProperty(getMetaClassName(entityClass), propertyPath, propertyValue)
        def loadContext = getLoadContext(entityClass)
                .setQuery(query)
                .setView(view)

        dataManager.load(loadContext)
    }

    @Override
    <E extends BaseUuidEntity> E loadByReferenz(Class<E> entityClass, String propertyPath, BaseUuidEntity referenz, String view = '_local') {
        LoadContext.Query query = createQueryByReferenz(getMetaClassName(entityClass), propertyPath, referenz)
        def loadContext = getLoadContext(entityClass)
                .setQuery(query)
                .setView(view)

        dataManager.load(loadContext)
    }

    @Override
    <E extends BaseUuidEntity> Collection<E> loadAll(Class<E> entityClass, String view = '_local') {
        LoadContext.Query query = createQueryForSelectAll(getMetaClassName(entityClass))
        LoadContext loadContext = getLoadContext(entityClass)
                .setQuery(query)
                .setView(view)
        dataManager.loadList(loadContext)
    }

    protected LoadContext.Query createQueryForSelectAll(String metaClassName) {
        LoadContext.createQuery(getSelectPart(metaClassName))
    }

    protected LoadContext.Query createQueryByReferenz(String metaClassName, String propertyPath, BaseUuidEntity referenz) {
        def queryString = "${getSelectPart(metaClassName)} where e.${propertyPath}.id = :referenzId"
        LoadContext.createQuery(queryString).setParameter('referenzId', referenz.id)
    }

    protected LoadContext.Query createQueryByProperty(String metaClassName, String propertyPath, String propertyValue) {
        def queryString = "${getSelectPart(metaClassName)} where e.${propertyPath} = :propertyValue"
        LoadContext.createQuery(queryString).setParameter('propertyValue', propertyValue)
    }


    protected String getSelectPart(String metaClassName) {
        "SELECT e FROM ${metaClassName} e"
    }

    protected LoadContext getLoadContext(Class entityClass) {
        LoadContext.create(entityClass)
    }

    protected LoadContext getLoadContext(MetaClass entityClass) {
        new LoadContext(entityClass)
    }


    protected String getMetaClassName(Class entityClass) {
        metadata.getClass(entityClass).name
    }

}
