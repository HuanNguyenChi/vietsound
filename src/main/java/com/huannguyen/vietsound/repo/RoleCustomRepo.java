package com.huannguyen.vietsound.repo;

import com.huannguyen.vietsound.entity.Role;
import com.huannguyen.vietsound.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Transformer;
import java.util.List;

@Repository
public class RoleCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRole(User user){
        StringBuilder sql = new StringBuilder();
        sql.append("select r.name as name from user u join user_role ur on u.id = ur.user_id\n"+
                "join role r on r.id = ur.role_id ");
        sql.append("where 1 = 1");
        if(user.getUsername() != null){
            sql.append(" and username = :username");
        }
        NativeQuery<Role> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        if(user.getUsername() != null){
            query.setParameter("username",user.getUsername());
        }
        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }
}
