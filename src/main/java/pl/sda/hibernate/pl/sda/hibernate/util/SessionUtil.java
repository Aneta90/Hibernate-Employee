package pl.sda.hibernate.pl.sda.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionUtil {

        private static final SessionUtil instance = new SessionUtil();
        private final SessionFactory factory;

        private SessionUtil() {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }

        public static Session getSession() {
            return getInstance().factory.openSession();
        }

        private static SessionUtil getInstance() {
            return instance;
        }
    }

