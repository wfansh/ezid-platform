
# Web application framework for Ezid.cn

A multi-tenancy web application development framework for www.ezid.cn, built upon Spring + myBatis. Fast, small and feature-rich for developers.

## Technology Stack

- Language & Building : Java 7, [Apache Maven](http://maven.apache.org/)
- Container & Persistence : [Spring 4.0.5](http://spring.io/), [myBatis 3.2.7](https://github.com/mybatis/)
- BPM & Scheduler : [Activiti 5.15](https://www.activiti.org/), [Quartz](http://www.quartz-scheduler.org/)
- UI : [BootStrap](http://getbootstrap.com/2.3.2/) + [Apache Tiles](http://tiles.apache.org/) + [jQuery](https://jquery.com/)
- Security & Roles : [Spring Security](http://projects.spring.io/spring-security/) + [Open LDAP](http://www.openldap.org/)
- Face Recognition : [OpenBR](http://openbiometrics.org/)

## About Ezid.cn

The multi-tenancy SaaS solution offered to Social Security Bureau, for identity authentication of retirees. It provides face recognition and manual review services from the internet, allowing retirees to upload identity resources from smart phones or browsers for annual pension qualifying.

- http://www.ezid.cn/
- http://js.ezid.cn/ezid-socialsec-cert-individual/
- http://ah.ezid.cn/ezid-socialsec-cert-individual/
- http://shebaoadmin.ezid.cn/

## Running Locally

Make sure you have Apache Maven installed locally. Download the source code and execute below Maven command in the root folder:

```
mvn package

```
