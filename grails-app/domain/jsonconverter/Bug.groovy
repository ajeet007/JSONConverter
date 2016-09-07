package jsonconverter
class Bug {
    Long bugID
    String summary
    String description
    String build
    String entityState
    String entityType
    String ownerName
    String priority
    String projectName
    String releaseName
    String severity
    String sprintName
    Story storyId
    String empID
    String parentId
    String assignTo

    static mapping = {
        bugID column: 'BugID'
        storyId column: 'StoryId'
        summary column: 'Summary'
        description column: 'Description'
        description type:'text'
        entityState column: 'EntityStatus'
        entityType column: 'EntityType'
        ownerName column: 'OwnerName'
        priority column: 'Priority'
        projectName column: 'ProjectName'
        releaseName column: 'ReleaseName'
        sprintName column: 'Sprint'
        severity column: 'Severity'
        build column: 'Build'
        empID column: 'EmpId'
        parentId column: 'ParentId'
        assignTo column: 'assignTo'
        version false
    }
    static constraints = {
        bugID nullable: false
        storyId nullable: true
        summary nullable: true
        description nullable: true
        build nullable: true
        entityType nullable: true
        entityState nullable: true
        ownerName nullable: true
        priority nullable: true
        projectName nullable: true
        releaseName nullable: true
        severity nullable: true
        sprintName nullable: true
        empID nullable: true
        parentId nullable: true
        assignTo nullable: true
    }
    static void updateParentId(jKey,tKey) {
        executeUpdate 'UPDATE Bug set parentId =:jiraKey where storyId=:tpKey', [jiraKey:jKey,tpKey:tKey]
    }
    static void updateEmpID(eKey,empKey) {
        executeUpdate 'UPDATE Bug set empID =:eID where ownerName=:empName', [eID:eKey,empName:empKey]
    }
    static void updateAssignment(jKey,tKey){
        executeUpdate 'UPDATE Bug set assignTo =:jiraKey where bugID=:tpKey', [jiraKey:jKey,tpKey:Long.parseLong(tKey)]
    }
}
