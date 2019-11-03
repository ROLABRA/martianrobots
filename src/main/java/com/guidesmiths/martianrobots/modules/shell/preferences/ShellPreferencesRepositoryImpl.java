package com.guidesmiths.martianrobots.modules.shell.preferences;

import com.guidesmiths.martianrobots.model.hibernate.UserPreference;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
class ShellPreferencesRepositoryImpl implements ShellPreferencesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public String getPreference(String preferenceKey){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserPreference> q = cb.createQuery(UserPreference.class);

        Root<UserPreference> root = q.from(UserPreference.class);

        q.select(root);

        q.where(cb.and(cb.equal(root.get("key"), preferenceKey)));

        UserPreference result = entityManager.createQuery(q).getSingleResult();

        return result.getValue();
    }

    public void setPreference(String preferenceKey, String preferenceValue){
        UserPreference p = new UserPreference(preferenceKey, preferenceValue);

        entityManager.merge(p);
    }
}
