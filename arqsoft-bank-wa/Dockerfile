FROM glassfish

COPY app-server/domain.xml glassfish/domains/domain1/config/domain.xml
COPY app-server/admin-keyfile glassfish/domains/domain1/config/admin-keyfile

COPY target/bank.war glassfish/domains/domain1/autodeploy/bank.war