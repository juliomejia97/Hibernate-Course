package com.infiniteskills.data;

import com.infiniteskills.data.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try{
            Configuration configuration = new Configuration();
            configuration.configure();
            return configuration
                    .buildSessionFactory(new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build());
        }catch(Exception e){
            e.printStackTrace();
            throw  new RuntimeException("There was an error builing the factory");
        }

    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
