mkdir temp;cd temp
mvn archetype:create -DarchetypeGroupId=org.appfuse.archetypes -DarchetypeArtifactId=appfuse-basic-jsf  -DarchetypeVersion=2.0 -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-jsf

mvn archetype:create -DarchetypeGroupId=org.appfuse.archetypes -DarchetypeArtifactId=appfuse-basic-spring  -DarchetypeVersion=2.0 -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-spring

mvn archetype:create -DarchetypeGroupId=org.appfuse.archetypes -DarchetypeArtifactId=appfuse-basic-struts  -DarchetypeVersion=2.0 -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-struts2

mvn archetype:create -DarchetypeGroupId=org.appfuse.archetypes -DarchetypeArtifactId=appfuse-basic-tapestry  -DarchetypeVersion=2.0 -DgroupId=org.appfuse.tutorial -DartifactId=tutorial-tapestry
