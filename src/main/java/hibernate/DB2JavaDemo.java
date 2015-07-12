package hibernate;

import java.io.File;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.reveng.dialect.MetaDataDialect;
import org.hibernate.tool.ant.Hbm2JavaExporterTask;

import hibernate.config.ConfigurationAwareToolTask;
import hibernate.config.ExtendedJDBCConfigurationTask;
import hibernate.config.ExtendedJDBCMetaDataConfigration;
import hibernate.exporter.DB2JavaExporterTaskHelper;

/**
 * Created by sunilp on 9/7/15.
 */
public class DB2JavaDemo {

    public static void main(String args[]) {

        try {

            File configFile = new File("/home/sunilp/IdeaProjects/Database2JavaCodeGeneration/src/main/resources/configuration/hibernate.cfg.xml");
            Configuration configuration = getJDBCConfiguration(configFile);
            ConfigurationAwareToolTask configurationAwareToolTask = new ConfigurationAwareToolTask(configuration);
            MetaDataDialect metaDataDialect = ((ExtendedJDBCMetaDataConfigration) configuration).getConfiguredMetaDataDialect();

            Hbm2JavaExporterTask hbm2javaExporter = new DB2JavaExporterTaskHelper(configurationAwareToolTask, metaDataDialect);
            hbm2javaExporter.setEjb3(true);
            hbm2javaExporter.setJdk5(true);
            hbm2javaExporter.setDestdir(new File("/home/sunilp/codeGeneration"));
            hbm2javaExporter.execute();

            System.out.println("Successfully code generated...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static Configuration getJDBCConfiguration(File configFile)
    {
        ExtendedJDBCConfigurationTask jdbcConfiguration = new ExtendedJDBCConfigurationTask(configFile);
        jdbcConfiguration.setPackageName("com.hrdb");
        jdbcConfiguration.setDetectOptimisticLock(false);
        jdbcConfiguration.setReverseStrategy("org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy");
        return jdbcConfiguration.getConfiguration();
    }


}
