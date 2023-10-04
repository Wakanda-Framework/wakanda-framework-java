# wakanda-framework

Wakanda is an adaptive framework for services to spin-up functional CRUD operations and integrate configurations, plugins, and cater for java dependencies.

## Integration for wakanda in project POM.xml file:
/* TODO --> add config for gradle */


    <properties>
        <wakanda.version> 1.0.0-RELEASE </wakanda.version>
    </properties>
    
    <dependency>
      <groupId>org.wakanda.framework</groupId>
      <artifactId>wakanda-framework</artifactId>
      <version>{wakanda.version}</version>
    </dependency>

## Standard Adaptations:
1. Models/Entities --> extend BaseEntity.java
2. Services --> extend BaseService.java
3. All-purpose Controllers --> extend BaseController.java
4. Query specific Controllers --> extend BaseQueryControllers.java


>Note: Always build with the wrapper, ie ./mvnw to avoid version conflicts for dependencies while using maven for builds in services. Ex: ./mvnw clean install
