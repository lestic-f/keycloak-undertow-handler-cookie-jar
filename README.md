# keycloak-undertow-handler-cookie-jar

## Présentation
Le projet *keycloak-undertow-handler-cookie-jar* est le handler Undertow pour passer les Cookies à **SameSite:None**.

## Version

La branche main nécessite :
- [Undertow Core >= 2.1.0.Final](https://mvnrepository.com/artifact/io.undertow/undertow-core/2.1.0.Final)

La branche feat/old-version nécessite :
- [Undertow Core >= 2.0.0.Final](https://mvnrepository.com/artifact/io.undertow/undertow-core/2.0.0.Final)

## Version à utiliser dans Keycloak
Pour connaître la version du module Undertow Core utilisé par Keycloak :  
Voir le jar sur le server à l'emplacement => modules\system\layers\base\io\undertow\core\main\undertow-core-{version}.jar