package br.com.manualdaprogramacao.crudexemplo;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

		dataSource.setUrl("jdbc:mysql://localhost:3306/manual?useSSL=true");
		dataSource.setUsername("manual");
		dataSource.setPassword("senhamanual123");
		
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorApapter() {
		HibernateJpaVendorAdapter jpaVendor = new HibernateJpaVendorAdapter();
		jpaVendor.setGenerateDdl(false);
		jpaVendor.setShowSql(false);
		jpaVendor.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return jpaVendor;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("br.com.manualdaprogramacao.aula08crud.modelo");
		emf.setJpaVendorAdapter(jpaVendorApapter());
		emf.setJpaProperties(jpaProperties());
		return emf;
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//		properties.put("hibernate.show_sql", "false");
//		properties.put("hibernate.hbm2ddl.auto", "validate");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager);
		return transactionManager;
	}

}
