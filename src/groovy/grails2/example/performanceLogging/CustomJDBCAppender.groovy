package grails2.example.performanceLogging

import org.apache.log4j.AppenderSkeleton
import org.apache.log4j.spi.LoggingEvent
import org.hibernate.SessionFactory
import org.hibernate.SharedSessionContract
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware 

public class CustomJDBCAppender extends AppenderSkeleton implements ApplicationContextAware {

	private static SessionFactory sessionFactory;

	private String user;
	private String password;
	private String driver;
	private String url;

	@Override
	public void setApplicationContext( ApplicationContext applicationContext ) {
		// We have to do the below because log4j initializes before spring and because of this the grails supplied sessionFactory bean is out of scope
		if ( applicationContext.getAutowireCapableBeanFactory().getBean( "sessionFactory" ) != null ) {
			sessionFactory = ( SessionFactory ) applicationContext.getAutowireCapableBeanFactory().getBean( "sessionFactory" );
		}
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}
	private String hSql = 
			"INSERT INTO [dbo].[logging] ([version], [application], [class_name], [date], [duration_ms], [method], [note]) " + 
					"VALUES (:version, :application, :class_name, GETDATE(), :duration_ms, :method, :note)"
	@Override
	protected void append(LoggingEvent event) {

		def analyticsLoggingObject = event?.getMessage() as Map
		
		// Quick/Minimal Validation on the Object
		if (!analyticsLoggingObject || !analyticsLoggingObject.application || !analyticsLoggingObject.class_name || !analyticsLoggingObject.method) {
			return;
		}
		
		Boolean openedJdbcSession = false
		SharedSessionContract jdbcSession = tryGetCurrentSession(sessionFactory)
		
		// If we are running async in a separate thread than our grails/spring MVC application we have to maintain our own Hibernate session for that thread.
		if (!jdbcSession) {
			jdbcSession = sessionFactory.openStatelessSession()
			openedJdbcSession = true
		}
		
		try
		{
			jdbcSession.createSQLQuery( hSql )
			.setParameter( "version", "1" )
			.setParameter( "application", analyticsLoggingObject.application )
			.setParameter( "class_name", analyticsLoggingObject.class_name )
			.setParameter( "duration_ms", analyticsLoggingObject.duration_ms )
			.setParameter( "method", analyticsLoggingObject.method )
			.setParameter( "note", analyticsLoggingObject.note )
			.executeUpdate()
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (openedJdbcSession) {
				jdbcSession.close()
			}
		}
	}
	
	private SharedSessionContract tryGetCurrentSession(SessionFactory inSessionFactory) {
		try {
			return inSessionFactory.getCurrentSession()
		}
		catch (org.hibernate.HibernateException he) {
			return null
		}
	}
}
