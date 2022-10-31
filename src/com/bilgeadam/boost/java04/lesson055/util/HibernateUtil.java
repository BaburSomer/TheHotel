package com.bilgeadam.boost.java04.lesson055.util;


import java.io.File;
import java.lang.annotation.Annotation;
import java.util.Arrays;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.Entity;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (HibernateUtil.sessionFactory == null) {
			HibernateUtil.sessionFactory = createSessionFactory();
		}
		return HibernateUtil.sessionFactory;
	}

	private static SessionFactory createSessionFactory() {
		Configuration config = new Configuration();
		
		config = registerEntityFiles(config);
		
		SessionFactory factory = config.configure(CommonData.getInstance().getHibernateConfigFileName()).buildSessionFactory();
		
		return factory;
	}

	@SuppressWarnings("rawtypes")
	private static Configuration registerEntityFiles(Configuration config) {
		String rootDirectoryName = CommonData.getInstance().getEntityFilesLocation();
		
		File rootDir = new File(rootDirectoryName);
		if (rootDir.isDirectory()) {
			String[] files = rootDir.list();
			for (String fileName : files) {
				Class entityClass;
				try {
					int pos = fileName.indexOf(".");
					fileName = fileName.substring(0, pos);
					entityClass = Class.forName(CommonData.getInstance().getEntityPackageName() + "." + fileName);
					if (isHibernateEntity(entityClass.getAnnotations())) {
						config.addAnnotatedClass(entityClass);
					}
				} catch (ClassNotFoundException e) {
					CommonData.getInstance().error("Error while reflecting " + fileName + " class: " + e.getMessage());
				}
			}
		}
		else {
			CommonData.getInstance().error(rootDirectoryName + " is not a directory");
			System.exit(100);
		}
		return config;
	}

	private static boolean isHibernateEntity(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if (annotation instanceof Entity) return true;
		}
		return false;
	}
}