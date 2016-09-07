package jsonconverter

class TestCase {

    Story storyId
    Long testCaseId
    String summary
    String description
    String entityType
    String ownerName
    String parentId
    String empID
    static mapping = {
        storyId column: 'StoryId'
        testCaseId column: 'TestCaseId'
        summary column: 'Summary'
        description column: 'Description'
        description type: 'text'
        entityType column: 'EntityType'
        ownerName column: 'Owner'
        parentId column: 'ParentId_JIRA'
        empID column: 'EmpID'
        version false
    }
    static constraints = {
        storyId nullable: false
        testCaseId nullable: false
        summary nullable: false
        description nullable: true
        entityType nullable: true
        ownerName nullable: true
        parentId nullable: true
        empID nullable: true
    }
    static void updateParentId(jKey,tKey) {
        executeUpdate 'UPDATE TestCase set parentId =:jiraKey where storyId=:tpKey', [jiraKey:jKey,tpKey:tKey]
    }
    static void updateEmpID(eKey,empKey) {
        executeUpdate 'UPDATE TestCase set empID =:eID where ownerName=:empName', [eID:eKey,empName:empKey]
    }
}
