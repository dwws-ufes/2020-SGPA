# 2020-doeLivro

Assignment for the 2020/02 edition of the "Web Development and the Semantic Web" course. by Bruno Legora, Bruno Machado, Jordana Reis, Luan Tome.

A web-based information system that aggregates useful tools for people to share books for donation.

### How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. Install [Eclipse 2020-12](http://www.eclipse.org/);

2. Install [WildFly 22](http://wildfly.org) and create a Server configuration within Eclipse;

3. Install [MySQL 8.0](http://www.mysql.com/products/community/) and create a schema called `doeLivros` and a user called `dwws` with password `dwws` and full access to the homonymous database;

4. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

5. Configure the datasource in WildFly's `standalone.xml` file:

```XML
 <datasource jta="true" jndi-name="java:/jboss/datasources/doeLivros" pool-name="doeLivrosPool" enabled="true" use-java-context="true">
	<connection-url>jdbc:mysql://localhost:3306/doeLivros</connection-url>
	<driver>mysql</driver>
	<security>
	    <user-name>dwws</user-name>
	    <password>dwws</password>
	</security>
 </datasource>
```

6. In Eclipse, use _File_ > _Import_ > _Git_ > _Projects from Git_ to import the Eclipse project existing in this repository;

7. You might have to adjust the server settings in the imported project: right-click the doeLivros project and select _Properties_. In the _Server_ section, select the _WildFly 22 server. In the _Targeted Runtimes_ section, select the _WildFly 22 Runtime_;

## Tools

* JavaEE
* Eclipse
* Maven
* MySQL
* JButler
* Frameweb

## Authors

* **Bruno Legora**
* **Bruno Machado**
* **Jordana Reis**
* **Luan Tome**
