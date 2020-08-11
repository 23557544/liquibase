package liquibase.harness.util

import liquibase.harness.config.TestConfig
import liquibase.harness.config.TestInput
import org.yaml.snakeyaml.Yaml

class FileUtils {
    static final String resourceBaseDir = "src/test/resources/harness/"

     static String getFileContent (TestInput testInput, String expectedFolder, String fileExtension){
        return new File(new StringBuilder(resourceBaseDir)
                .append(expectedFolder)
                .append("/")
                .append(testInput.getDatabaseName())
                .append("/")
                .append(testInput.getChangeObject())
                .append(fileExtension)
                .toString()
        ).getText("UTF-8")
    }

    static String getExpectedSqlFileContent(TestInput testInput) {
        return getFileContent(testInput,"expectedSql",".sql")
    }

    static String getExpectedSnapshotFileContent(TestInput testInput) {
        return getFileContent(testInput,"expectedSnapshot",".json")
    }

    static TestConfig readYamlConfig(String fileName) {
        Yaml configFileYml = new Yaml()
        return configFileYml.loadAs(new File(resourceBaseDir, fileName).newInputStream(), TestConfig.class)
    }

    static String buildPathToChangeLogFile(String changeObject){
        return "harness/changelogs/" + changeObject + ".xml"
        //TODO search files from directory based on name with any extension
        //TODO discuss to extend and include version to changeLog path
    }
}
