package cz.cvut.iTracker.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import cz.cvut.iTracker.domain.ITEntity;
/**
 * 
 * @author vavat
 *
 * 
 */
public class ITJpaBaseDao<T extends ITEntity> implements ITBaseDao<T> {
    @PersistenceContext
    private EntityManager em;

    /**
     * Class of persisted entity.
     */
    private final Class<T> entity;

    /**
     * Constructor setting persisted entity class.
     * @param entity
     */
    public ITJpaBaseDao(Class<T> entity) {
        this.entity = entity;
    }

    /**
     * {@inheritDoc}
     */
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * {@inheritDoc}
     */
    public T findById(Long id) {
        if (id == null) {
            return null;
        }
        return em.find(entity, id);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> loadAll() {
        CriteriaQuery<T> q = em.getCriteriaBuilder().createQuery(entity);
        Root<T> root = q.from(entity);
        q.select(root);
        return em.createQuery(q).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public Long persist(T entity) {
        em.persist(entity);
        return entity.getEntityId();
    }

    /**
     * {@inheritDoc}
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T entity) {
        em.remove(entity);
    }

    /**
     * {@inheritDoc}
     */
    public Long getCount() {
        CriteriaBuilder cBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);
        Root<T> root = cQuery.from(entity);
        cQuery.select(cBuilder.count(root));
        return getEntityManager().createQuery(cQuery).getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String queryString, Map<String, Object> values) {
        Query query = getEntityManager().createNamedQuery(queryString);
        if (values != null) {
            for (Map.Entry<String, Object> e : values.entrySet()) {
                query.setParameter(e.getKey(), e.getValue());
            }
        }
        return query.getResultList();
    }
}
