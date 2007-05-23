mkdir temp;cd temp
mvn archetype:create -DarchetypeGroupId=org.appfuse -DarchetypeArtifactId=appfuse-basic-jsf -DremoteRepositories=http://static.appfuse.org/repository -DarchetypeVersion=2.0-m5-SNAPSHOT -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-jsf

mvn archetype:create -DarchetypeGroupId=org.appfuse -DarchetypeArtifactId=appfuse-basic-spring -DremoteRepositories=http://static.appfuse.org/repository -DarchetypeVersion=2.0-m5-SNAPSHOT -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-spring

mvn archetype:create -DarchetypeGroupId=org.appfuse -DarchetypeArtifactId=appfuse-basic-struts -DremoteRepositories=http://static.appfuse.org/repository -DarchetypeVersion=2.0-m5-SNAPSHOT -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-struts2

mvn archetype:create -DarchetypeGroupId=org.appfuse -DarchetypeArtifactId=appfuse-basic-tapestry -DremoteRepositories=http://static.appfuse.org/repository -DarchetypeVersion=2.0-m5-SNAPSHOT -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-tapestry
